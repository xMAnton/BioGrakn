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
import java.util.NoSuchElementException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.uniprot.uniprot.OrganismType;
import org.uniprot.uniprot.ProteinType;
import org.uniprot.uniprot.SequenceType;
import org.uniprot.uniprot.CommentType;
import org.uniprot.uniprot.Entry;
import org.uniprot.uniprot.GeneType;

import ai.grakn.GraknTxType;
import ai.grakn.client.Grakn;
import ai.grakn.graql.Query;
import ai.grakn.graql.VarPattern;

import static ai.grakn.graql.Graql.*;

public class Uniprot extends Importer {

	static public void importer(Grakn.Session session, String fileName) throws IOException, XMLStreamException, JAXBException, NoSuchElementException {
		int entryCounter = 0;

        Grakn.Transaction graknTx = session.transaction(GraknTxType.WRITE);

        XMLInputFactory xif = XMLInputFactory.newInstance();
        XMLStreamReader xsr = xif.createXMLStreamReader(new FileReader(fileName));
    	xsr.next(); // Advance to statements element
        
        JAXBContext jc = JAXBContext.newInstance(Entry.class.getPackage().getName());
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        
        System.out.print("Importing Uniprot SwissProt ");
        
        while (xsr.nextTag() == XMLStreamConstants.START_ELEMENT) {
            Entry entry = (Entry) unmarshaller.unmarshal(xsr);
            
            OrganismType organism = entry.getOrganism();
            String organismTaxonomyId = ((organism != null) && (!organism.getDbReference().isEmpty())) ? organism.getDbReference().get(0).getId() : "";
            
            if (organismTaxonomyId.equals("9606")) {
	            	
            	if (entry.getAccession().isEmpty())
            		continue;
            	
            	ProteinType prot = entry.getProtein();
            	
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
            	}
	            
            	VarPattern p = var("p")
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
        				;
            	
            	for (String accessionName : entry.getAccession())
            		p.has("accession", accessionName);
            	
            	Query<?> protein = null;
            	
            	if (gene.equals(""))
            		protein = insert(p);
            	else {
            		protein = 
            			match(
            				var("g").isa("gene").has("symbol", gene)
            			).
            			insert(
	            			p,
	            			var().isa("encoding").rel("encoder", "g").rel("encoded", "p")
            			);
            	}
            	
            	protein.withTx(graknTx).execute();
            	
                entryCounter++;
            	
                if (entryCounter % 1000 == 0) {
                	graknTx.commit();
                	graknTx.close();
                	
                	graknTx = session.transaction(GraknTxType.WRITE);

                	System.out.print(".");
                }
            }
        }
        System.out.println(" done");

        graknTx.commit();
    	graknTx.close();

        //xsr.close();
 	}
}
