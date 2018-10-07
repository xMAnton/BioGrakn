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

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import ai.grakn.GraknTxType;
import ai.grakn.client.Grakn;
import ai.grakn.graql.InsertQuery;

import static ai.grakn.graql.Graql.*;

import it.cnr.icar.biograkn.go.Header;
import it.cnr.icar.biograkn.go.Source;
import it.cnr.icar.biograkn.go.Term;
import it.cnr.icar.biograkn.go.Typedef;

public class GeneOntology extends Importer {
	
	static public void importer(Grakn.Session session, String fileName) throws FileNotFoundException, XMLStreamException, JAXBException {
        /*
		HashMap<String, Node> idVertexMap = new HashMap<String, Node>();
	    	HashMap<String, List<String>> termParentsMap = new HashMap<String, List<String>>();
	    	HashMap<String, List<String>> regulatesMap = new HashMap<String, List<String>>();
	    	HashMap<String, List<String>> negativelyRegulatesMap = new HashMap<String, List<String>>();
	    	HashMap<String, List<String>> positivelyRegulatesMap = new HashMap<String, List<String>>();
	    	HashMap<String, List<String>> partOfMap = new HashMap<String, List<String>>();
	    	HashMap<String, List<String>> hasPartMap = new HashMap<String, List<String>>();
         */
        int entryCounter = 0;
        
        Grakn.Transaction graknTx = session.transaction(GraknTxType.WRITE);
        
        XMLInputFactory xif = XMLInputFactory.newInstance();
        XMLStreamReader xsr = xif.createXMLStreamReader(new FileReader(fileName));
        xsr.nextTag(); // Advance to statements element

        JAXBContext jc = JAXBContext.newInstance(Header.class, Source.class, Term.class, Typedef.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        
        System.out.print("Importing Gene Ontology ");

        while (xsr.nextTag() == XMLStreamConstants.START_ELEMENT) {
            Object entry = unmarshaller.unmarshal(xsr);
           
            if (entry instanceof Term) {
            	Term term = (Term)entry;
            	
            	String goId = term.getId();
            	String goName = (term.getName() != null) ? term.getName() : "";
            	String goDefinition = ((term.getDef() != null) && (term.getDef().getDefstr() != null)) ? term.getDef().getDefstr() : "";
            	String goComment = (term.getComment() != null) ? term.getComment() : "";
            	String goNamespace = (term.getNamespace() != null) ? term.getNamespace() : "";
            	
	            InsertQuery go = insert(
	            		var("t")
	            			.isa("go")
	            			.has("goId", goId)
	            			.has("name", goName)
	            			.has("definition", goDefinition)
	            			.has("comment", goComment)
	            			.has("namespace", goNamespace)
	            		);
	
	            go.withTx(graknTx).execute();

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

            entryCounter++;
        	if (entryCounter % 2000 == 0) {
            	graknTx.commit();
            	graknTx.close();
            	
            	graknTx = session.transaction(GraknTxType.WRITE);

            	System.out.print(".");
            }
        }
        System.out.println(" done");

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
        
        xsr.close();
        
        graknTx.commit();
    	graknTx.close();
	}
}
