/*
 * BioGrakn - A Knowledge Graph-based Semantic Database for Biomedical Sciences
 * Copyright (C) 2017 - Antonio Messina (xMAnton) <antonio.messina@icar.cnr.it>
 *
 * BioGrakn is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * BioGrakn is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with BioGrakn. If not, see <http://www.gnu.org/licenses/gpl.txt>.
 */

package it.cnr.icar.biograkn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.biojava.bio.BioException;
import org.biojava.bio.seq.Feature;
import org.biojavax.Namespace;
import org.biojavax.RichObjectFactory;
import org.biojavax.bio.seq.RichSequence;
import org.biojavax.bio.seq.RichSequenceIterator;

import ai.grakn.Grakn;
import ai.grakn.GraknGraph;
import ai.grakn.GraknSession;
import ai.grakn.GraknTxType;
import ai.grakn.concept.Concept;
import ai.grakn.concept.Entity;
import ai.grakn.concept.RelationType;
import ai.grakn.concept.Role;
import ai.grakn.graql.MatchQuery;
import ai.grakn.graql.QueryBuilder;

import static ai.grakn.graql.Graql.*;

public class _07a_Precursor2Matures {

    private static String timeConversion(long seconds) {

        final int MINUTES_IN_AN_HOUR = 60;
        final int SECONDS_IN_A_MINUTE = 60;

        long minutes = seconds / SECONDS_IN_A_MINUTE;
        seconds -= minutes * SECONDS_IN_A_MINUTE;

        long hours = minutes / MINUTES_IN_AN_HOUR;
        minutes -= hours * MINUTES_IN_AN_HOUR;

        return hours + " hours " + minutes + " minutes " + seconds + " seconds";
    }

    public static void main(String[] args) throws IOException, NoSuchElementException, BioException{
        disableInternalLogs();

        String homeDir = System.getProperty("user.home");
        int relCounter = 0;
        long startTime = System.currentTimeMillis();

    	GraknSession session = Grakn.session(Grakn.DEFAULT_URI, "biograkn");
    	
        String fileName = homeDir + "/biodb/miRNA.dat";
        
        System.out.print("\nImporting precursors-matures relations from " + fileName + " ");

        BufferedReader br = new BufferedReader(new FileReader(fileName)); 
		Namespace ns = RichObjectFactory.getDefaultNamespace();
		RichSequenceIterator seqs = RichSequence.IOTools.readEMBLRNA(br, ns);

		while (seqs.hasNext()) {
            GraknGraph graph = session.open(GraknTxType.BATCH);
            QueryBuilder qb = graph.graql();

            //ArrayList<Var> vars = new ArrayList<Var>();
			
			RichSequence entry = seqs.nextRichSequence();

			String accession = entry.getAccession();
			
        	MatchQuery findMirna = qb.match(var("m1").isa("mirna").has("accession", accession)).limit(1);        	
        	Iterator<Concept> concepts = findMirna.get("m1").iterator();

        	if (concepts.hasNext()) {
        		
        		Entity mirna = concepts.next().asEntity();
        		
    			Iterator<Feature> itf = entry.getFeatureSet().iterator();
    			while (itf.hasNext()) {
    				Feature f = itf.next();
    				
    				String matAccession = null;

    				@SuppressWarnings("unchecked")
    				Map<Object, ?> map = f.getAnnotation().asMap();
    				Set<Object> keys = map.keySet();
    				for (Object key : keys) {
    					String keyString = key.toString();
    					String value = (String) map.get(key);

    					if (keyString.substring(keyString.lastIndexOf(":")+1).equals("accession"))
    						matAccession = value;
     				}
    						
    				if (matAccession != null) {
    					
    	            	MatchQuery findMature = qb.match(var("m2").isa("mirnaMature").has("accession", matAccession)).limit(1);        	
    	            	concepts = findMature.get("m2").iterator();

    	            	if (concepts.hasNext()) {
    	            		
    	            		Entity mirnaMature = concepts.next().asEntity();
    	            		
    	            		RelationType precursorOf = graph.getRelationType("precursorOf");
    	            		Role precursor = graph.getRole("precursor");
    	            		Role mature = graph.getRole("mature");

    	            		precursorOf.addRelation()
	            				.addRolePlayer(precursor, mirna)
	            				.addRolePlayer(mature, mirnaMature);
    	            		
    	            		relCounter++;
    	            		
    	                    if (relCounter % 1000 == 0) {
    	                    	System.out.print("."); System.out.flush();
    	                    }
    	                    
    	            	}
    				}
    			}
        	}
        	
        	graph.commit();
		}

        session.close();

        long stopTime = (System.currentTimeMillis()-startTime)/1000;
        System.out.println("\n\nCreated " + relCounter + " relations in " + timeConversion(stopTime));

        br.close();
    }
    
    public static void disableInternalLogs(){
    	org.apache.log4j.Logger logger4j = org.apache.log4j.Logger.getRootLogger();
		logger4j.setLevel(org.apache.log4j.Level.toLevel("INFO"));
    }
    
}
