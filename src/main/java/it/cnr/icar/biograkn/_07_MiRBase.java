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
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Vector;

import org.biojava.bio.BioException;
import org.biojava.bio.seq.Feature;
import org.biojavax.Comment;
import org.biojavax.Namespace;
import org.biojavax.RichObjectFactory;
import org.biojavax.bio.seq.RichSequence;
import org.biojavax.bio.seq.RichSequenceIterator;

import ai.grakn.Grakn;
import ai.grakn.client.BatchMutatorClient;
import ai.grakn.graql.InsertQuery;

import static ai.grakn.graql.Graql.*;

public class _07_MiRBase {

    private static String timeConversion(long seconds) {

        final int MINUTES_IN_AN_HOUR = 60;
        final int SECONDS_IN_A_MINUTE = 60;

        long minutes = seconds / SECONDS_IN_A_MINUTE;
        seconds -= minutes * SECONDS_IN_A_MINUTE;

        long hours = minutes / MINUTES_IN_AN_HOUR;
        minutes -= hours * MINUTES_IN_AN_HOUR;

        return hours + " hours " + minutes + " minutes " + seconds + " seconds";
    }

    public static void main(String[] args) throws IOException, NoSuchElementException, BioException{
        disableInternalLogs();

        String homeDir = System.getProperty("user.home");
        int entryCounter = 0;
        int resCounter = 0;
        int relCounter = 0;
        long startTime = System.currentTimeMillis();

        // for grakn 0.11.0
    	//System.setProperty(ConfigProperties.CONFIG_FILE_SYSTEM_PROPERTY, "./conf/grakn-engine.properties");
    	//System.setProperty(ConfigProperties.LOG_FILE_CONFIG_SYSTEM_PROPERTY, "./conf/logback.xml");

    	//LoaderClient loader = new LoaderClient("biograkn", Grakn.DEFAULT_URI);
    	BatchMutatorClient loader = new BatchMutatorClient("biograkn", Grakn.DEFAULT_URI);

        String fileName = homeDir + "/biodb/miRNA.dat";
        
        System.out.print("\nImporting mirnas from " + fileName + " ");

        BufferedReader br = new BufferedReader(new FileReader(fileName)); 
		Namespace ns = RichObjectFactory.getDefaultNamespace();
		RichSequenceIterator seqs = RichSequence.IOTools.readEMBLRNA(br, ns);

		while (seqs.hasNext()) {
			//ArrayList<Var> vars = new ArrayList<Var>();
			
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
			
			InsertQuery mirna = insert(
					var("m")
					.isa("mirna")
        			.has("accession", accession)
        			.has("name", name)
        			.has("description", description)
        			.has("comment", comment)
        			.has("sequence", sequence)
        			);
        	
            entryCounter++;
            resCounter += 5;
			
            loader.add(mirna);
            
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
	        			.has("location", location)
	        			);
				
				entryCounter++;
				resCounter += 4;

				loader.add(mature);

				/*
				Var rel = 
	        			var("rel" + cnt)
						.isa("precursorOf")
						.rel("precursor", "m")
						.rel("mature", "mat" + cnt)
						;

				cnt++;
				relCounter++;

				vars.add(rel);
				*/			
			}

            if (entryCounter % 1000 == 0) {
            	System.out.print("."); System.out.flush();
            }
		}

        loader.flush();
        loader.waitToFinish();

        long stopTime = (System.currentTimeMillis()-startTime)/1000;
        System.out.println("\n\nCreated " + entryCounter + " entities, " + resCounter + " resources and " + relCounter + " relations in " + timeConversion(stopTime));

        br.close();
    }
    
    public static void disableInternalLogs(){
    	org.apache.log4j.Logger logger4j = org.apache.log4j.Logger.getRootLogger();
		logger4j.setLevel(org.apache.log4j.Level.toLevel("INFO"));
    }
    
}
