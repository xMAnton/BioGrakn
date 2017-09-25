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

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import ai.grakn.Grakn;
import ai.grakn.GraknGraph;
import ai.grakn.GraknSession;
import ai.grakn.GraknTxType;
import ai.grakn.graql.MatchQuery;
import ai.grakn.graql.QueryBuilder;
import ai.grakn.concept.Concept;
import ai.grakn.concept.Entity;
import ai.grakn.concept.RelationType;
import ai.grakn.concept.Role;
import ai.grakn.exception.InvalidGraphException;

import static ai.grakn.graql.Graql.*;

import it.cnr.icar.biograkn.uniprot.Entry;
import it.cnr.icar.biograkn.uniprot.GeneType;
import it.cnr.icar.biograkn.uniprot.OrganismType;

public class _03b_Protein2Genes {

	private static String timeConversion(long seconds) {

	    final int MINUTES_IN_AN_HOUR = 60;
	    final int SECONDS_IN_A_MINUTE = 60;

	    long minutes = seconds / SECONDS_IN_A_MINUTE;
	    seconds -= minutes * SECONDS_IN_A_MINUTE;

	    long hours = minutes / MINUTES_IN_AN_HOUR;
	    minutes -= hours * MINUTES_IN_AN_HOUR;

	    return hours + " hours " + minutes + " minutes " + seconds + " seconds";
	}
	
	public static void main(String[] args) throws IOException, XMLStreamException, JAXBException {
        disableInternalLogs();

        String homeDir = System.getProperty("user.home");
		
        int relCounter = 0;
        
        long startTime = System.currentTimeMillis();
        String fileName = homeDir + "/biodb/uniprot_sprot.xml";

        // for grakn 0.11.0
    	//System.setProperty(ConfigProperties.CONFIG_FILE_SYSTEM_PROPERTY, "./conf/grakn-engine.properties");
    	//System.setProperty(ConfigProperties.LOG_FILE_CONFIG_SYSTEM_PROPERTY, "./conf/logback.xml");

    	//BatchMutatorClient loader = new BatchMutatorClient("biograkn", Grakn.DEFAULT_URI);
        GraknSession session = Grakn.session(Grakn.DEFAULT_URI, "biograkn");

        System.out.print("\nReading genes-proteins encoding relations from " + fileName + " ");

        XMLInputFactory xif = XMLInputFactory.newInstance();
        XMLStreamReader xsr = xif.createXMLStreamReader(new FileReader(fileName));
        xsr.nextTag(); // Advance to statements element
        
        JAXBContext jc = JAXBContext.newInstance(Entry.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        
        while (xsr.nextTag() == XMLStreamConstants.START_ELEMENT) {
            Entry entry = (Entry) unmarshaller.unmarshal(xsr);
            
            OrganismType organism = entry.getOrganism();
            String organismTaxonomyId = ((organism != null) && (!organism.getDbReference().isEmpty())) ? organism.getDbReference().get(0).getId() : "";
            
            if (organismTaxonomyId.equals("9606")) {
            	
            	if (entry.getAccession().isEmpty())
            		continue;
            	
            	String name = entry.getName().get(0);

            	if (!entry.getGene().isEmpty()) {
            		GeneType geneType = entry.getGene().get(0);
            		if (!geneType.getName().isEmpty()) {
            			String geneName = geneType.getName().get(0).getValue();
            			
                        GraknGraph graph = session.open(GraknTxType.BATCH);
                        QueryBuilder qb = graph.graql();

                    	MatchQuery findProtein = qb.match(var("p").isa("protein").has("name", name)).limit(1);        	
                    	Iterator<Concept> concepts = findProtein.get("p").iterator();

                    	if (concepts.hasNext()) {
                    		
                    		Entity protein = concepts.next().asEntity();
                    		
                        	MatchQuery findGene = qb.match(var("g").isa("gene").has("symbol", geneName)).limit(1);        	
                        	concepts = findGene.get("g").iterator();

                        	if (concepts.hasNext()) {
                        		
                        		Entity gene = concepts.next().asEntity();
                        		
        	            		RelationType encodingType = graph.getRelationType("encoding");
        	            		Role encoder = graph.getRole("encoder");
        	            		Role encoded = graph.getRole("encoded");

        	            		encodingType.addRelation()
        		    				.addRolePlayer(encoder, gene)
        		    				.addRolePlayer(encoded, protein);
        	            		
        	            		relCounter++;

        	            		try {
        	        				graph.commit();

        	        				if (relCounter % 2000 == 0) {
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
            	}
        	}
        }

        session.close();

        long stopTime = (System.currentTimeMillis()-startTime)/1000;
        System.out.println("\n\nCreated " + relCounter + " relations in " + timeConversion(stopTime));
	}
	
    public static void disableInternalLogs(){
    	org.apache.log4j.Logger logger4j = org.apache.log4j.Logger.getRootLogger();
		logger4j.setLevel(org.apache.log4j.Level.toLevel("INFO"));
    }
}
