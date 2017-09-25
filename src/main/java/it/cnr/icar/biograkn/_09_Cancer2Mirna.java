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

import ai.grakn.Grakn;
import ai.grakn.GraknGraph;
import ai.grakn.GraknSession;
import ai.grakn.GraknTxType;
import ai.grakn.concept.Concept;
import ai.grakn.concept.Entity;
import ai.grakn.concept.RelationType;
import ai.grakn.concept.Role;
import ai.grakn.exception.InvalidGraphException;
import ai.grakn.graql.MatchQuery;
import ai.grakn.graql.QueryBuilder;

import static ai.grakn.graql.Graql.*;

public class _09_Cancer2Mirna {

	private static String timeConversion(long seconds) {

	    final int MINUTES_IN_AN_HOUR = 60;
	    final int SECONDS_IN_A_MINUTE = 60;

	    long minutes = seconds / SECONDS_IN_A_MINUTE;
	    seconds -= minutes * SECONDS_IN_A_MINUTE;

	    long hours = minutes / MINUTES_IN_AN_HOUR;
	    minutes -= hours * MINUTES_IN_AN_HOUR;

	    return hours + " hours " + minutes + " minutes " + seconds + " seconds";
	}
	
	public static void main(String[] args) throws IOException {
        disableInternalLogs();

        String homeDir = System.getProperty("user.home");
		
        // cat miRCancerSeptember2015.txt | awk -F '\t' '{ print $1 "\t" $2 "\t" $3; }' | sort | uniq > cancer2mirna.txt
        String fileName = homeDir + "/biodb/cancer2mirna.txt";
		String line;
		int relCounter = 0;
        long startTime = System.currentTimeMillis();

    	GraknSession session = Grakn.session(Grakn.DEFAULT_URI, "biograkn");
        		
	    BufferedReader reader = new BufferedReader(new FileReader(fileName));

        System.out.print("\nImporting cancer2mirna associations from " + fileName + " ");
                
        while ((line = reader.readLine()) != null) {
            GraknGraph graph = session.open(GraknTxType.BATCH);
            QueryBuilder qb = graph.graql();

            String datavalue[] = line.split("\t");
        	
        	String mirId = datavalue[0];
        	String cancerName = datavalue[1];
        	String cancerProfile = datavalue[2];
        	
        	String regulation = cancerProfile + "Regulation";
        	String regulator  = cancerProfile + "Regulator";
        	String regulated  = cancerProfile + "Regulated";

        	MatchQuery findMirna = qb.match(var("m").isa("mirna").has("name", mirId)).limit(1);        	
        	Iterator<Concept> concepts = findMirna.get("m").iterator();
        	
        	if (concepts.hasNext()) {
        		
        		Entity mirna = concepts.next().asEntity();
        		
            	MatchQuery findCancer = qb.match(var("c").isa("cancer").has("name", cancerName)).limit(1);        	
            	concepts = findCancer.get("c").iterator();

            	if (concepts.hasNext()) {
            		
            		Entity cancer = concepts.next().asEntity();
            		
            		RelationType regulationType = graph.getRelationType(regulation);
            		Role regulatorRole = graph.getRole(regulator);
            		Role regulatedRole = graph.getRole(regulated);

            		regulationType.addRelation()
        				.addRolePlayer(regulatorRole, mirna)
        				.addRolePlayer(regulatedRole, cancer);

            		relCounter++;

            		try {
        				graph.commit();

        				if (relCounter % 200 == 0) {
                        	System.out.print("."); System.out.flush();
                        }
        			} catch (InvalidGraphException e) {
        				graph.abort();
        				relCounter--;
        			}                           	

            	} else
            		graph.close();
        	} else
        		graph.close();

        }
        
        session.close();
        
        long stopTime = (System.currentTimeMillis()-startTime)/1000;
        System.out.println("\n\nCreated " + relCounter + " relations in " + timeConversion(stopTime));
        
        reader.close();
	}
	
    public static void disableInternalLogs(){
    	org.apache.log4j.Logger logger4j = org.apache.log4j.Logger.getRootLogger();
		logger4j.setLevel(org.apache.log4j.Level.toLevel("INFO"));
    }
}
