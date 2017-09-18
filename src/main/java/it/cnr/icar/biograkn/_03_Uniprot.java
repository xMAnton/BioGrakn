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

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import ai.grakn.Grakn;
import ai.grakn.graql.InsertQuery;
import ai.grakn.client.BatchMutatorClient;

import static ai.grakn.graql.Graql.*;

import it.cnr.icar.biograkn.uniprot.CommentType;
import it.cnr.icar.biograkn.uniprot.Entry;
import it.cnr.icar.biograkn.uniprot.GeneType;
import it.cnr.icar.biograkn.uniprot.OrganismType;
import it.cnr.icar.biograkn.uniprot.ProteinType;
import it.cnr.icar.biograkn.uniprot.SequenceType;

public class _03_Uniprot {

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

    	BatchMutatorClient loader = new BatchMutatorClient("biograkn", Grakn.DEFAULT_URI);

        System.out.print("\nReading proteins entries from " + fileName + " ");

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
            	
            	ProteinType prot = entry.getProtein();
            	
            	//String accession = entry.getAccession().get(0);
            	String name = entry.getName().get(0);
            	String fullName = ((prot.getRecommendedName() != null) && (prot.getRecommendedName().getFullName() != null)) ? prot.getRecommendedName().getFullName().getValue() : "";
            	String alternativeName = ((!prot.getAlternativeName().isEmpty()) && (prot.getAlternativeName().get(0).getFullName() != null)) ? prot.getAlternativeName().get(0).getFullName().getValue() : "";
            	
            	String gene = "";
            	if (!entry.getGene().isEmpty()) {
            		GeneType geneType = entry.getGene().get(0);
            		if (!geneType.getName().isEmpty()) {
            			gene = geneType.getName().get(0).getValue();
            		}
            	}
            	
            	SequenceType seq = entry.getSequence();
            	String sequence = seq.getValue();
            	int sequenceLength = seq.getLength();
            	int sequenceMass = seq.getMass();

            	String function = "";
            	String pathway = "";
            	String subunit = "";
            	String tissue = "";
            	String ptm = "";
            	String similarity = "";
            	
            	for (CommentType comment : entry.getComment()) {
            		if (comment.getText().isEmpty())
            			continue;
            		
            		String s = comment.getText().get(0).getValue();
            		if (comment.getType().equals("function")) {
            			function = s;
            		} else if (comment.getType().equals("pathway")) {
            			pathway = s;
            		} else if (comment.getType().equals("subunit")) {
            			subunit = s;
            		} else if (comment.getType().equals("tissue specificity")) {
            			tissue = s;
            		} else if (comment.getType().equals("PTM")) {
            			ptm = s;
            		} else if (comment.getType().equals("similarity")) {
            			similarity = s;
            		}
            		/*
            		else
            		if (!comment.getIsoform().isEmpty()) {
            			for (IsoformType isoform : comment.getIsoform()) {
            				for (String isoId : isoform.getId()) {
            					Node accession = graphDb.createNode( PROTEIN_NAME );
            					accession.setProperty("name", isoId);

            					accession.createRelationshipTo(protein, RelationTypes.REFERS_TO);

                                entryCounter++;
                                edgeCounter++;
            				}
            			}
            		}
           		 	*/
            	}

            	InsertQuery protein = insert(
            			var("p")
            			.isa("protein")
            			.has("name", name)
            			.has("fullName", fullName)
            			.has("alternativeName", alternativeName)
            			.has("proteinGene", gene)
            			.has("function", function)
            			.has("proteinPathway", pathway)
            			.has("subunit", subunit)
            			.has("tissue", tissue)
            			.has("ptm", ptm)
            			.has("similarity", similarity)
            			.has("sequence", sequence)
            			.has("sequenceLength", sequenceLength)
            			.has("sequenceMass", sequenceMass)
            			);
            	
                entryCounter++;
                resCounter += 13;
                
                loader.add(protein);

                int cnt = 1;
            	for (String accessionName : entry.getAccession()) {
            		InsertQuery accession = insert(
            				var("acc" + cnt)
            				.isa("proteinAccession")
            				.has("accession", accessionName)
            				);
            		
            		loader.add(accession);
            		
            		entryCounter++;
            		resCounter++;
            		            		
            		InsertQuery rel = insert(
    	        			var("rel" + cnt)
    						.isa("entityReference")
    						.rel("identifier", "acc" + cnt)
    						.rel("identified", "p")
    						);

    				loader.add(rel);	
    				
    				relCounter++;

    				cnt++;
            	}
            	
            	/*
            	for (ReferenceType refType: entry.getReference()) {
            		CitationType citation = refType.getCitation();
            		if ((citation != null) && (citation.getType().equals("journal article"))) {
            			
            			if (citation.getTitle() != null)			
                	        for (DbReferenceType dbref: citation.getDbReference()) {
                	        	if (dbref.getType().equals("PubMed")) {
                	        			String pubmedId = dbref.getId();
                	        			
                        				if (!pubmedId.equals("")) {
                        	            	ResourceIterator<Node> it = graphDb.findNodes(PUBMED, "pubmedId", pubmedId);

                        	            	Node cit = null;
                        	            	if (it.hasNext())
                        	            		cit = it.next();
                        	            	else {
                        	            		cit = graphDb.createNode( PUBMED );
                        	            		cit.setProperty("pubmedId", pubmedId);
                        	            		entryCounter++;
                        	            	}
                    	            		protein.createRelationshipTo(cit, RelationTypes.CITED_IN);
                        	        		edgeCounter++;
                        				}
                	        	}
                	        }                	        
            		}
            	}
            	*/

            	
                if (entryCounter % 2000 == 0) {
                	System.out.print("."); System.out.flush();
                }
        	}
        }

        loader.flush();
        loader.waitToFinish();
        	
        long stopTime = (System.currentTimeMillis()-startTime)/1000;
        System.out.println("\n\nCreated " + entryCounter + " entities, " + resCounter + " resources and " + relCounter + " relations in " + timeConversion(stopTime));
	}
	
    public static void disableInternalLogs(){
    	org.apache.log4j.Logger logger4j = org.apache.log4j.Logger.getRootLogger();
		logger4j.setLevel(org.apache.log4j.Level.toLevel("INFO"));
    }
}
