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

import ai.grakn.Grakn;
import ai.grakn.exception.GraknException;
import ai.grakn.graql.InsertQuery;
import ai.grakn.client.BatchMutatorClient;

import static ai.grakn.graql.Graql.*;

public class _01_NCBIGene {

    private static String timeConversion(long seconds) {

        final int MINUTES_IN_AN_HOUR = 60;
        final int SECONDS_IN_A_MINUTE = 60;

        long minutes = seconds / SECONDS_IN_A_MINUTE;
        seconds -= minutes * SECONDS_IN_A_MINUTE;

        long hours = minutes / MINUTES_IN_AN_HOUR;
        minutes -= hours * MINUTES_IN_AN_HOUR;

        return hours + " hours " + minutes + " minutes " + seconds + " seconds";
    }

    public static void main(String[] args) throws IOException, GraknException{
        disableInternalLogs();

        String homeDir = System.getProperty("user.home");
        String fileName = homeDir + "/biodb/NCBI_gene/gene_info";
        String line;
        int entryCounter = 0;
        //int edgeCounter = 0;
        long startTime = System.currentTimeMillis();

        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        // skip first line
        reader.readLine();

        // for grakn 0.11.0
    	//System.setProperty(ConfigProperties.CONFIG_FILE_SYSTEM_PROPERTY, "./conf/grakn-engine.properties");
    	//System.setProperty(ConfigProperties.LOG_FILE_CONFIG_SYSTEM_PROPERTY, "./conf/logback.xml");

    	BatchMutatorClient loader = new BatchMutatorClient("biograkn", Grakn.DEFAULT_URI);
    	
        System.out.print("\nImporting NCBI gene info entries from " + fileName + " ");

        while ((line = reader.readLine()) != null) {
            String datavalue[] = line.split("\t");

            String taxId = datavalue[0];
            if (!taxId.equals("9606"))
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
            			.has("nomenclatureStatus", datavalue[12])
            			.has("otherDesignations", datavalue[13])
            			);

            //graph.commit();
            
            loader.add(gene);
            
            entryCounter++;

            if (entryCounter % 5000 == 0) {
            	System.out.print("."); System.out.flush();
            }
        }

        loader.flush();
        loader.waitToFinish();

        long stopTime = (System.currentTimeMillis()-startTime)/1000;
        System.out.println("\n\nCreated " + entryCounter + " entities and " + (entryCounter*10) + " resources in " + timeConversion(stopTime));

        reader.close();
    }
    
    public static void disableInternalLogs(){
    	org.apache.log4j.Logger logger4j = org.apache.log4j.Logger.getRootLogger();
		logger4j.setLevel(org.apache.log4j.Level.toLevel("INFO"));
    }
}