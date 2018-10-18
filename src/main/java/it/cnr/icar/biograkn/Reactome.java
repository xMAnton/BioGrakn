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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import ai.grakn.client.Grakn;
import ai.grakn.graql.InsertQuery;

import static ai.grakn.graql.Graql.*;

public class Reactome extends Importer {

	static public void importer(Grakn.Session session, String fileName) throws IOException, InterruptedException, ExecutionException {
        String line;
		int entryCounter = 0;

        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        // skip first line
        reader.readLine();

        System.out.print("Importing Reactome ");

        while ((line = reader.readLine()) != null) {        	
        	String datavalue[] = line.split("\t");
        	
        	String id = datavalue[0];
        	String name = datavalue[1];
        	String summation = datavalue[2];
        	
        	InsertQuery pathway = insert(
        			var("p")
        			.isa("pathway")
        			.has("pathwayId", id)
        			.has("name", name)
        			.has("summation", summation)
        			);

        	add(pathway);
        	
            entryCounter++;
            if (entryCounter % 100 == 0) {
            	exec(session);
            	System.out.print(".");
            }
        }

        exec(session);
        System.out.println(" done");

        reader.close();
    }
}
