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
import ai.grakn.graql.Query;

import static ai.grakn.graql.Graql.*;

public class MiRCancer extends Importer {

	static public void importer(BatchExecutorClient loader, Keyspace keyspace, String fileName) throws IOException {
        String line;
		int entryCounter = 0;

        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        System.out.print("Importing miRCancer ");

        while ((line = reader.readLine()) != null) {
            String datavalue[] = line.split("\t");

        	String mirId = datavalue[0];
        	String cancerName = datavalue[1];
        	String cancerProfile = datavalue[2];
        	
        	String regulation = cancerProfile + "Regulation";
        	String regulator  = cancerProfile + "Regulator";
        	String regulated  = cancerProfile + "Regulated";

            InsertQuery cancer = insert(
            			var("c")
            			.isa("cancer")
            			.has("name", cancerName)
            			);

            loader.add(cancer, keyspace);
        	
            entryCounter++;

            if (cancerProfile.equals("up") || cancerProfile.equals("down")) {
	        	Query<?> reg = match(var("m").isa("mirna").has("name", mirId), var("c").isa("cancer").has("name", cancerName)).insert(var("r").isa(regulation).rel(regulator, "m").rel(regulated, "c"));
	
	            loader.add(reg, keyspace);
            }
    			
            if (entryCounter % 250 == 0) {
        		System.out.print(".");
            }
        }
        System.out.println(" done");
        
        reader.close();
    }
}
