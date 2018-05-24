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

import static ai.grakn.graql.Graql.match;
import static ai.grakn.graql.Graql.var;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import ai.grakn.Keyspace;
import ai.grakn.client.BatchExecutorClient;
import ai.grakn.graql.Query;

public class HGNC extends Importer {

	static public void importer(BatchExecutorClient loader, Keyspace keyspace, String fileName) throws IOException {
        String line;
		int entryCounter = 0;

	    BufferedReader reader = new BufferedReader(new FileReader(fileName));

        System.out.print("Importing HGNC ");

        // skip first line
        reader.readLine();

        while ((line = reader.readLine()) != null) {
            String datavalue[] = line.split("\t");

        	if (datavalue.length < 2)
        		continue;

            String hgncId = datavalue[0];
        	String entrezId = datavalue[1];

        	String ensemblGeneId = (datavalue.length < 3) ? "" : datavalue[2];
        	String refseqAccession = (datavalue.length < 4) ? "" : datavalue[3];

			Query<?> q = match(
	        				var("g").isa("gene").has("geneId", entrezId)
						).
						insert(
							var("syn").isa("geneName").has("name", hgncId),
		    				var().isa("entityReference").rel("identifier", "syn").rel("identified", "g")
		        		);
			
			loader.add(q, keyspace);            		

			entryCounter++;
    	
    		if (!ensemblGeneId.equals("")) {
    			q = match(
        				var("g").isa("gene").has("geneId", entrezId)
					).
					insert(
						var("syn").isa("geneName").has("name", ensemblGeneId),
        				var().isa("entityReference").rel("identifier", "syn").rel("identified", "g")
            		);
    			
    			loader.add(q, keyspace);

    			entryCounter++;
    		}
    		
    		if (!refseqAccession.equals("")) {
    			if (refseqAccession.contains("|")) {
    				String refseqId[] = refseqAccession.split("|");
    				for (int i=0; i<refseqId.length; i++) {
	        			q = match(
                    			var("g").isa("gene").has("geneId", entrezId)
 	        				).
        					insert(
        						var("syn").isa("geneName").has("name", refseqId[i]),
        						var().isa("entityReference").rel("identifier", "syn").rel("identified", "g")
                    		);
	        			
	        			loader.add(q, keyspace);

	        			entryCounter++;
    				}
    			} else {
        			q = match(
                			var("g").isa("gene").has("geneId", entrezId)
        				).
    					insert(
    						var("syn").isa("geneName").has("name", refseqAccession),
                			var().isa("entityReference").rel("identifier", "syn").rel("identified", "g")
                		);
        			
        			loader.add(q, keyspace);
    				
        			entryCounter++;
    			}
    		
	            if (entryCounter % 2000 == 0) {
            		System.out.print(".");
	            }
        	}
        }
        System.out.println(" done");

        reader.close();
    }
}