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
import ai.grakn.concept.EntityType;
import ai.grakn.concept.RelationType;
import ai.grakn.concept.Resource;
import ai.grakn.concept.ResourceType;
import ai.grakn.concept.Role;

import static ai.grakn.graql.Graql.*;

import it.cnr.icar.biograkn.uniprot.Entry;
import it.cnr.icar.biograkn.uniprot.OrganismType;

public class _03a_UniprotAccessions {

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
		
        int entryCounter = 0;
        int resCounter = 0;
        int relCounter = 0;
        
        long startTime = System.currentTimeMillis();
        String fileName = homeDir + "/biodb/uniprot_sprot.xml";

        // for grakn 0.11.0
    	//System.setProperty(ConfigProperties.CONFIG_FILE_SYSTEM_PROPERTY, "./conf/grakn-engine.properties");
    	//System.setProperty(ConfigProperties.LOG_FILE_CONFIG_SYSTEM_PROPERTY, "./conf/logback.xml");

    	//BatchMutatorClient loader = new BatchMutatorClient("biograkn", Grakn.DEFAULT_URI);
        GraknSession session = Grakn.session(Grakn.DEFAULT_URI, "biograkn");

        System.out.print("\nReading proteins accessions from " + fileName + " ");

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
            	//ArrayList<InsertQuery> vars = new ArrayList<InsertQuery>();
            	
            	if (entry.getAccession().isEmpty())
            		continue;
            	
            	String name = entry.getName().get(0);

                GraknGraph graph = session.open(GraknTxType.BATCH);
                QueryBuilder qb = graph.graql();

            	MatchQuery findProtein = qb.match(var("p").isa("protein").has("name", name)).limit(1);        	
            	Iterator<Concept> concepts = findProtein.get("p").iterator();

            	if (concepts.hasNext()) {
            		
            		Entity protein = concepts.next().asEntity();
            		
            		for (String accessionName : entry.getAccession()) {
            			
            			EntityType accessionEntityType = graph.getEntityType("proteinAccession");
            			ResourceType<Object> accessionResourceType = graph.getResourceType("accession");
            			
            			Entity accessionEntity = accessionEntityType.addEntity();
            			Resource<Object> accessionResource = accessionResourceType.putResource(accessionName);
            			
            			Entity accession = accessionEntity.resource(accessionResource);

                		entryCounter++;
                		resCounter++;

                		RelationType refType = graph.getRelationType("entityReference");
                		Role identifier = graph.getRole("identifier");
                		Role identified = graph.getRole("identified");

                		refType.addRelation()
	        				.addRolePlayer(identifier, accession)
	        				.addRolePlayer(identified, protein);
        		
                		relCounter++;
                		
                        if (entryCounter % 2000 == 0) {
                        	System.out.print("."); System.out.flush();
                        }
            		}
            	}
            	
            	graph.commit();
        	}
        }

        session.close();

        long stopTime = (System.currentTimeMillis()-startTime)/1000;
        System.out.println("\n\nCreated " + entryCounter + " entities, " + resCounter + " resources and " + relCounter + " relations in " + timeConversion(stopTime));
	}
	
    public static void disableInternalLogs(){
    	org.apache.log4j.Logger logger4j = org.apache.log4j.Logger.getRootLogger();
		logger4j.setLevel(org.apache.log4j.Level.toLevel("INFO"));
    }
}
