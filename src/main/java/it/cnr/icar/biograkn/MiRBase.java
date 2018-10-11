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
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.biojava.bio.BioException;
import org.biojava.bio.seq.Feature;
import org.biojavax.Comment;
import org.biojavax.Namespace;
import org.biojavax.RichObjectFactory;
import org.biojavax.bio.seq.RichSequence;
import org.biojavax.bio.seq.RichSequenceIterator;

import ai.grakn.GraknTxType;
import ai.grakn.client.Grakn;
import ai.grakn.graql.InsertQuery;
import ai.grakn.graql.Query;

import static ai.grakn.graql.Graql.*;

public class MiRBase extends Importer {

	static public void importer(Grakn.Session session, String fileName) throws IOException, NoSuchElementException, BioException, InterruptedException, ExecutionException {
		int entryCounter = 0;

        ExecutorService executorService = Executors.newFixedThreadPool(8);
        ArrayList<CompletableFuture<Void>> listOfFutures = new ArrayList<>();
		
        BufferedReader br = new BufferedReader(new FileReader(fileName)); 
		Namespace ns = RichObjectFactory.getDefaultNamespace();
		RichSequenceIterator seqs = RichSequence.IOTools.readEMBLRNA(br, ns);

        System.out.print("Importing miRBase ");

        while (seqs.hasNext()) {
			RichSequence entry = seqs.nextRichSequence();

			String accession = entry.getAccession();
			String name = entry.getName();
			String description = entry.getDescription();
			Vector<String> comments = new Vector<String>();
			
			for (Comment comment : entry.getComments()) {
				String cmt = comment.getComment().replaceAll("\n", " ");
				comments.add(cmt);
			}
			String comment = "";
			if (comments.size() > 0)
				comment = comments.get(0);

			String sequence = entry.getInternalSymbolList().seqString();
			
            entryCounter++;
            final int counter = entryCounter;

            InsertQuery mirna = insert(
					var("m")
					.isa("mirna")
	        			.has("accession", accession)
	        			.has("name", name)
	        			.has("description", description)
	        			.has("comment", comment)
	        			.has("sequence", sequence));

			listOfFutures.add(CompletableFuture.runAsync(() -> {

				Grakn.Transaction graknTx = session.transaction(GraknTxType.BATCH);				
                mirna.withTx(graknTx).execute();
                graknTx.commit();

    			Iterator<Feature> itf = entry.getFeatureSet().iterator();
    			
    			int cnt = 1;
    			while (itf.hasNext()) {
    				Feature f = itf.next();
    				
    				String location = f.getLocation().toString();
    				String subSequence = sequence.substring(f.getLocation().getMin()-1, f.getLocation().getMax());
    				String matAccession = "";
    				String matProduct = "";

    				@SuppressWarnings("unchecked")
    				Map<Object, ?> map = f.getAnnotation().asMap();
    				Set<Object> keys = map.keySet();
    				for (Object key : keys) {
    					String keyString = key.toString();
    					String value = (String) map.get(key);
    					
    					if (keyString.substring(keyString.lastIndexOf(":")+1).equals("accession"))
    						matAccession = value;
    					
    					if (keyString.substring(keyString.lastIndexOf(":")+1).equals("product"))
    						matProduct = value;
    				}

    				InsertQuery mature = insert(
    						var("mat" + cnt)
    		        			.isa("mirnaMature")
    		        			.has("accession", matAccession)
    		        			.has("product", matProduct)
    		        			.has("sequence", subSequence)
    		        			.has("location", location));
    				
    				graknTx = session.transaction(GraknTxType.BATCH);
    				
    				mature.withTx(graknTx).execute();

    				Query<?> rel = match(var("m1").isa("mirna").has("accession", accession), var("m2").isa("mirnaMature").has("accession", matAccession)).insert(var("p"+cnt).isa("precursorOf").rel("precursor", "m1").rel("mature", "m2"));
    				rel.withTx(graknTx).execute();

    				graknTx.commit();
    				
    				cnt++;
    			}

                if (counter % 1000 == 0) {
                	System.out.print(".");
                }
                
        	}));

			entryCounter += entry.getFeatureSet().size();
		}

        CompletableFuture<Void> allFutures =
        CompletableFuture.
        	allOf(listOfFutures.toArray(new CompletableFuture[listOfFutures.size()])).
        	whenComplete((r, ex)-> executorService.shutdown());
        
        allFutures.get();
        System.out.println(" done");

        br.close();
    }
}
