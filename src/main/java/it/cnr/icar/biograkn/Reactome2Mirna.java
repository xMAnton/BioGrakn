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

import ai.grakn.GraknTxType;
import ai.grakn.client.Grakn;
import ai.grakn.graql.Query;

public class Reactome2Mirna extends Importer {

	static public void importer(Grakn.Session session, String fileName) throws IOException {
		String line;
		int entryCounter = 0;

		Grakn.Transaction graknTx = session.transaction(GraknTxType.WRITE);
	    BufferedReader reader = new BufferedReader(new FileReader(fileName));

        System.out.print("Importing Reactome2miRNA ");

        while ((line = reader.readLine()) != null) {
            String datavalue[] = line.split("\t");
        	
        	String accessionId = datavalue[0];
        	String pathwayId = datavalue[1];

        	if (accessionId.startsWith("miR"))
        		accessionId = "MI" + accessionId.substring(3);

        	Query<?> annotation = match(var("p").isa("pathway").has("pathwayId", pathwayId), var("m").isa("mirna").has("accession", accessionId)).insert(var("c").isa("containing").rel("container", "p").rel("contained", "m"));

        	annotation.withTx(graknTx).execute();
        	
        	entryCounter++;
            		
            if (entryCounter % 10 == 0) {
            	graknTx.commit();
            	graknTx.close();
            	
            	graknTx = session.transaction(GraknTxType.WRITE);

            	System.out.print(".");
            }
        }
        System.out.println(" done");
            
        graknTx.commit();
    	graknTx.close();
    	
        reader.close();
    }
}