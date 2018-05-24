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

public class MiRTarBase extends Importer {

	static public void importer(BatchExecutorClient loader, Keyspace keyspace, String fileName) throws IOException {
		String line;
		int entryCounter = 0;

	    BufferedReader reader = new BufferedReader(new FileReader(fileName));

        System.out.print("Importing miRTarBase ");

        // skip first line
        reader.readLine();

        while ((line = reader.readLine()) != null) {
    		String datavalue[] = line.split("\t");

        	//String mirTarBaseId = datavalue[0];
        	String mirna = datavalue[1];
        	//String speciesTarget = datavalue[2];
        	String targetGene = datavalue[3];
        	//String geneId = datavalue[4];
	        //String speciesTargetGene = datavalue[5];
        	String experiments = datavalue[6];
        	String supportType = datavalue[7];
        	//String pmid = datavalue[8];

        	Query<?> rel = match(
	        				var("m").isa("mirnaMature").has("product", mirna),
	        				var("g").isa("gene").has("symbol", targetGene)
	        			).
	        			insert(
	        				var("i").isa("interaction")
	        				.has("database", "miRTarBase")
	        				.has("experiments", experiments)
	        				.has("supportType", supportType),
	        				var().isa("interactionMiRNA").rel("interactingMiRNA", "m").rel("interacting", "i"),
	        				var().isa("interactionGene").rel("interacting", "i").rel("interactingGene", "g")
	        			);

        	loader.add(rel, keyspace);
        	
            entryCounter++;
    		
            if (entryCounter % 20000 == 0) {
        		System.out.print(".");
            }
        }
        System.out.println(" done");
        
        reader.close();
	}
}
