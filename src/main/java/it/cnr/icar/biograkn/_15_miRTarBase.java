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

import ai.grakn.Grakn;
import ai.grakn.GraknGraph;
import ai.grakn.exception.GraknValidationException;
import ai.grakn.graql.QueryBuilder;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

public class _15_miRTarBase {

	private static String timeConversion(long seconds) {

	    final int MINUTES_IN_AN_HOUR = 60;
	    final int SECONDS_IN_A_MINUTE = 60;

	    long minutes = seconds / SECONDS_IN_A_MINUTE;
	    seconds -= minutes * SECONDS_IN_A_MINUTE;

	    long hours = minutes / MINUTES_IN_AN_HOUR;
	    minutes -= hours * MINUTES_IN_AN_HOUR;

	    return hours + " hours " + minutes + " minutes " + seconds + " seconds";
	}
	
	public static void main(String[] args) throws IOException, GraknValidationException {
        disableInternalLogs();

        String homeDir = System.getProperty("user.home");
		
		String fileName = homeDir + "/biodb/hsa_MTI.txt";
		String line;
		int entryCounter = 0;
        long startTime = System.currentTimeMillis();

        GraknGraph graph = Grakn.factory(Grakn.DEFAULT_URI, "biograph").getGraph();
        QueryBuilder qb = graph.graql();
        		
	    BufferedReader reader = new BufferedReader(new FileReader(fileName));

        System.out.print("\nImporting miRTarBase interactions from " + fileName + " ");

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
        	
        	qb.match(
        			var("m").isa("mirnaMature").has("product", mirna),
        			var("g").isa("gene").has("symbol", targetGene)
        	).insert(
    				var("i").isa("interaction")
    				.has("database", "miRTarBase")
    				.has("experiments", experiments)
    				.has("supportType", supportType),
    				var().isa("interactionMiRNA").rel("interactingMiRNA", "m").rel("interacting", "i"),
    				var().isa("interactionGene").rel("interacting", "i").rel("interactingGene", "g")
    		).execute();

        	entryCounter++;

        	graph.commit();
    		
            if (entryCounter % 10000 == 0) {
            	System.out.print("."); System.out.flush();
            }
        }
        
        graph.close();
        
        long stopTime = (System.currentTimeMillis()-startTime)/1000;
        System.out.println("\n\nCreated " + entryCounter + " entities, " + (entryCounter*3) + " resources and " + (entryCounter*2) + " relations in " + timeConversion(stopTime));
        
        reader.close();

	}
	
    public static void disableInternalLogs(){
        Logger logger = (Logger) org.slf4j.LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
        logger.setLevel(Level.OFF);
    }
    
}
