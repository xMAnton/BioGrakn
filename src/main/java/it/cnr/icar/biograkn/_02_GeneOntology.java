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

import static ai.grakn.graql.Graql.var;

import java.io.FileReader;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import ai.grakn.Grakn;
import ai.grakn.client.LoaderClient;
import ai.grakn.engine.util.ConfigProperties;
import ai.grakn.graql.Graql;
import ai.grakn.graql.Var;
import it.cnr.icar.biograkn.go.Header;
import it.cnr.icar.biograkn.go.Source;
import it.cnr.icar.biograkn.go.Term;
import it.cnr.icar.biograkn.go.Typedef;

public class _02_GeneOntology {
	
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
		String homeDir = System.getProperty("user.home");
		
		String fileName = homeDir + "/biodb/go_daily-termdb.obo-xml";
		int entryCounter = 0;
        //int relCounter = 0;
        long startTime = System.currentTimeMillis();
        
        /*
		HashMap<String, Node> idVertexMap = new HashMap<String, Node>();
		
    	HashMap<String, List<String>> termParentsMap = new HashMap<String, List<String>>();
    	HashMap<String, List<String>> regulatesMap = new HashMap<String, List<String>>();
    	HashMap<String, List<String>> negativelyRegulatesMap = new HashMap<String, List<String>>();
    	HashMap<String, List<String>> positivelyRegulatesMap = new HashMap<String, List<String>>();
    	HashMap<String, List<String>> partOfMap = new HashMap<String, List<String>>();
    	HashMap<String, List<String>> hasPartMap = new HashMap<String, List<String>>();
    	*/
        
        // for grakn 0.11.0
    	System.setProperty(ConfigProperties.CONFIG_FILE_SYSTEM_PROPERTY, "./conf/grakn-engine.properties");
    	System.setProperty(ConfigProperties.LOG_FILE_CONFIG_SYSTEM_PROPERTY, "./conf/logback.xml");

    	LoaderClient loader = new LoaderClient("biograkn", Grakn.DEFAULT_URI);

        XMLInputFactory xif = XMLInputFactory.newInstance();
        XMLStreamReader xsr = xif.createXMLStreamReader(new FileReader(fileName));
        xsr.nextTag(); // Advance to statements element

        JAXBContext jc = JAXBContext.newInstance(Header.class, Source.class, Term.class, Typedef.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        
        System.out.println("\nReading GO entries from " + fileName + "\n");
        System.out.print("inserting term nodes ");
        
        while (xsr.nextTag() == XMLStreamConstants.START_ELEMENT) {
            Object entry = unmarshaller.unmarshal(xsr);
            
            if (entry instanceof Term) {
            	Term term = (Term)entry;
            	
            	String goId = term.getId();
            	String goName = (term.getName() != null) ? term.getName() : "";
            	String goDefinition = ((term.getDef() != null) && (term.getDef().getDefstr() != null)) ? term.getDef().getDefstr() : "";
            	String goComment = (term.getComment() != null) ? term.getComment() : "";
            	/*
            	String goIsObsolete = "";
            	if (term.getIsObsolete() != null) {
            		goIsObsolete = (term.getIsObsolete() == 1) ? "true" : "false";
            	}
            	*/
            	String goNamespace = (term.getNamespace() != null) ? term.getNamespace() : "";
            	            	
                entryCounter++;
                
                Var goTerm = var("t")
                			.isa("go")
                			.has("goId", goId)
                			.has("name", goName)
                			.has("definition", goDefinition)
                			.has("comment", goComment)
                			.has("namespace", goNamespace);

                loader.add(Graql.insert(goTerm));
	
                /*
                idVertexMap.put(goId, t);
                
            	termParentsMap.put(goId, term.getIsA());
            	
        		for (Term.Relationship rel : term.getRelationship()) {
            		String goRelationshipType = rel.getType();
            		String goRelationshipTo = rel.getTo();
            		
            		List<String> tempArray = null;
            		
            		switch (goRelationshipType) {
            			case "regulates":
            				tempArray = regulatesMap.get(goId);
            				if (tempArray == null) {
					          tempArray = new ArrayList<String>();
					          regulatesMap.put(goId, tempArray);
            			    }
            			    tempArray.add(goRelationshipTo);
            				break;
            			case "positively_regulates":
            				tempArray = positivelyRegulatesMap.get(goId);
            				if (tempArray == null) {
        			          tempArray = new ArrayList<String>();
        			          positivelyRegulatesMap.put(goId, tempArray);
            			    }
            			    tempArray.add(goRelationshipTo);
            				break;
            			case "negatively_regulates":
            				tempArray = negativelyRegulatesMap.get(goId);
            				if (tempArray == null) {
            					tempArray = new ArrayList<String>();
            					negativelyRegulatesMap.put(goId, tempArray);
            				}
            				tempArray.add(goRelationshipTo);
            				break;
            			case "part_of":
            				tempArray = partOfMap.get(goId);
            				if (tempArray == null) {
            					tempArray = new ArrayList<String>();
            					partOfMap.put(goId, tempArray);
            				}
            				tempArray.add(goRelationshipTo);
            				break;
            			case "has_part":
            				tempArray = hasPartMap.get(goId);
            				if (tempArray == null) {
            					tempArray = new ArrayList<String>();
            					hasPartMap.put(goId, tempArray);
            				}
            				tempArray.add(goRelationshipTo);
            				break;
            		}
        		}
        		*/
            }

            if (entryCounter % 1000 == 0) {
            	System.out.print("."); System.out.flush();
            }
        }

        /*
        System.out.println("\n\ncreating relationships:");

        List<String> tempArray = null;
        Set<String> keys = null;

        System.out.print("\n  'is_a' relationships ");
        keys = termParentsMap.keySet();
        for (String key : keys) {
        	//Vertex tempGoTerm = graph.getVertices("go_Term.id", key).iterator().next();
        	Node tempGoTerm = idVertexMap.get(key);
        	tempArray = termParentsMap.get(key);
        	for (String string : tempArray) {
        		Node tempGoTerm2 = idVertexMap.get(string);
        		
        		tempGoTerm.createRelationshipTo( tempGoTerm2, RelationTypes.IS_A );
        		
        		relCounter++;
                if (relCounter % 1000 == 0) {
                	System.out.print("."); System.out.flush();
                }
        	}
        }
        
        System.out.print("\n\n  'regulates' relationships ");
        keys = regulatesMap.keySet();
        for (String key : keys) {
        	//Vertex tempGoTerm = graph.getVertices("go_Term.id", key).iterator().next();
        	Node tempGoTerm = idVertexMap.get(key);
        	tempArray = regulatesMap.get(key);
        	for (String string : tempArray) {
        		Node tempGoTerm2 = idVertexMap.get(string);
        		tempGoTerm.createRelationshipTo( tempGoTerm2, RelationTypes.REGULATES );
        		relCounter++;
                if (relCounter % 100 == 0) {
                	System.out.print("."); System.out.flush();
                }
        	}
        }
        
        System.out.print("\n\n  'negatively_regulates' relationships ");
        keys = negativelyRegulatesMap.keySet();
        for (String key : keys) {
        	//Vertex tempGoTerm = graph.getVertices("go_Term.id", key).iterator().next();
        	Node tempGoTerm = idVertexMap.get(key);
        	tempArray = negativelyRegulatesMap.get(key);
        	for (String string : tempArray) {
        		Node tempGoTerm2 = idVertexMap.get(string);
        		tempGoTerm.createRelationshipTo( tempGoTerm2, RelationTypes.NEGATIVELY_REGULATES );
        		relCounter++;
                if (relCounter % 100 == 0) {
                	System.out.print("."); System.out.flush();
                }
        	}
        }
        
        System.out.print("\n\n  'positively_regulates' relationships ");
        keys = positivelyRegulatesMap.keySet();
        for (String key : keys) {
        	//Vertex tempGoTerm = graph.getVertices("go_Term.id", key).iterator().next();
        	Node tempGoTerm = idVertexMap.get(key);
        	tempArray = positivelyRegulatesMap.get(key);
        	for (String string : tempArray) {
        		Node tempGoTerm2 = idVertexMap.get(string);
        		tempGoTerm.createRelationshipTo( tempGoTerm2, RelationTypes.POSITIVELY_REGULATES );
        		relCounter++;
                if (relCounter % 100 == 0) {
                	System.out.print("."); System.out.flush();
                }
        	}
        }
        
        System.out.print("\n\n  'part_of' relationships ");
        keys = partOfMap.keySet();
        for (String key : keys) {
        	//Vertex tempGoTerm = graph.getVertices("go_Term.id", key).iterator().next();
        	Node tempGoTerm = idVertexMap.get(key);
        	tempArray = partOfMap.get(key);
        	for (String string : tempArray) {
        		Node tempGoTerm2 = idVertexMap.get(string);
        		tempGoTerm.createRelationshipTo( tempGoTerm2, RelationTypes.PART_OF );
        		relCounter++;
                if (relCounter % 100 == 0) {
                	System.out.print("."); System.out.flush();
                }
        	}
        }
        
        System.out.print("\n\n  'has_part_of' relationships ");
        keys = hasPartMap.keySet();
        for (String key : keys) {
        	//Vertex tempGoTerm = graph.getVertices("go_Term.id", key).iterator().next();
        	Node tempGoTerm = idVertexMap.get(key);
        	tempArray = hasPartMap.get(key);
        	for (String string : tempArray) {
        		Node tempGoTerm2 = idVertexMap.get(string);
        		tempGoTerm.createRelationshipTo( tempGoTerm2, RelationTypes.HAS_PART );
        		relCounter++;
                if (relCounter % 100 == 0) {
                	System.out.print("."); System.out.flush();
                }
        	}
        }
        */
        
        loader.flush();
        loader.waitToFinish();

        long stopTime = (System.currentTimeMillis()-startTime)/1000;
        System.out.println("\n\nImported " + entryCounter + " GO terms and " + (entryCounter*5) + " resources in " + timeConversion(stopTime));
	}
}
