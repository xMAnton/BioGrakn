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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ai.grakn.GraknTxType;
import ai.grakn.client.Grakn;
import ai.grakn.graql.InsertQuery;
import ai.grakn.graql.VarPattern;

import static ai.grakn.graql.Graql.*;

public class NCBIGene extends Importer {

	static public void importer(Grakn.Session session, String fileName) throws IOException, InterruptedException, ExecutionException {        
        int entryCounter = 0;
        String line;

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        ArrayList<CompletableFuture<Void>> listOfFutures = new ArrayList<>();
        
        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        // skip first line
        reader.readLine();

        System.out.print("Importing NCBI Gene ");

        while ((line = reader.readLine()) != null) {
            String datavalue[] = line.split("\t");

            if (!datavalue[0].equals("9606"))
            		continue;
            
            String slists[] = datavalue[4].split("|");
            HashSet<String> symbols = new HashSet<String>(slists.length + 2);
            
            symbols.add(datavalue[2]);
            
            for (int i=0; i<slists.length; i++) {
            	String s = slists[i];
            	if (s.startsWith("HGNC:") || s.startsWith("Vega:"))
            		s = s.substring(5);
            	if (s.startsWith("Ensembl:"))
            		s = s.substring(8);
            	symbols.add(s);
            }
            
            if (!datavalue[10].equals("-"))
            	symbols.add(datavalue[10]);
            
            VarPattern g = var("g")
        			.isa("gene")
        			.has("geneId", datavalue[1])
        			.has("chromosome", datavalue[6])
        			.has("location", datavalue[7])
        			.has("description", datavalue[8])
        			.has("type", datavalue[9])
        			.has("fullName", datavalue[11])
        			;
            symbols.forEach(s -> g.has("symbol", s));
            
            InsertQuery gene = insert(g);

            entryCounter++;
            final int cnt = entryCounter;
            
            listOfFutures.add(CompletableFuture.runAsync(() -> {
                Grakn.Transaction graknTx = session.transaction(GraknTxType.BATCH);
                gene.withTx(graknTx).execute();
                graknTx.commit();
                
                if (cnt % 2500 == 0) {
                	System.out.print(".");
                }
        	}));
        }
        
        CompletableFuture<Void> allFutures =
        CompletableFuture.
        	allOf(listOfFutures.toArray(new CompletableFuture[listOfFutures.size()])).
        	whenComplete((r, ex)-> executorService.shutdown());
        
        allFutures.get();
        System.out.println(" done");
        
        reader.close();
    }    
}