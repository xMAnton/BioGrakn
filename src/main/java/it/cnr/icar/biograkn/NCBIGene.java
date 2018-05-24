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

import ai.grakn.Keyspace;
import ai.grakn.client.BatchExecutorClient;
import ai.grakn.graql.InsertQuery;

import static ai.grakn.graql.Graql.*;

public class NCBIGene extends Importer {

	static public void importer(BatchExecutorClient loader, Keyspace keyspace, String fileName) throws IOException {        
        int entryCounter = 0;
        String line;
        
        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        // skip first line
        reader.readLine();

        System.out.print("Importing NCBI Gene ");

        while ((line = reader.readLine()) != null) {
            String datavalue[] = line.split("\t");

            if (!datavalue[0].equals("9606"))
            		continue;
            
            String symbol = datavalue[10].equals("-") ? datavalue[2] : datavalue[10];

            InsertQuery gene = insert(
            		var("g")
            			.isa("gene")
            			.has("geneId", datavalue[1])
            			.has("locusTag", datavalue[3])
            			.has("chromosome", datavalue[6])
            			.has("location", datavalue[7])
            			.has("description", datavalue[8])
            			.has("type", datavalue[9])
            			.has("symbol", symbol)
            			.has("fullName", datavalue[11])
            			);

            loader.add(gene, keyspace);
            
            entryCounter++;

            if (entryCounter % 2500 == 0) {
            	System.out.print(".");
            }
        }
        System.out.println(" done");

        reader.close();
    }    
}