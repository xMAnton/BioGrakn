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
import java.util.concurrent.ExecutionException;

import ai.grakn.client.Grakn;
import ai.grakn.graql.Query;

import static ai.grakn.graql.Graql.*;

public class Gene2Go extends Importer {

	static public void importer(Grakn.Session session, String fileName) throws IOException, InterruptedException, ExecutionException {
		String line;
		int entryCounter = 0;
		
        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        System.out.print("Importing Gene2GO ");
        
        while ((line = reader.readLine()) != null) {
        	String datavalue[] = line.split(" ");
        	
        	String geneId = datavalue[0];
        	String goId = datavalue[1];
        	
            Query<?> annotation = 
        			match(
    					var("g1").isa("gene").has("geneId", geneId), 
    					var("g2").isa("go").has("goId", goId)
        			).
        			insert(
        				var().isa("annotation").rel("annotatedEntity", "g1").rel("functionalAnnotation", "g2")
        			);

            add(annotation);
            
            entryCounter++;

            if (entryCounter % 10000 == 0) {
            	exec(session);
                System.out.print(".");
            }
        }

        exec(session);
        System.out.println(" done");
        
        reader.close();
	}
}
