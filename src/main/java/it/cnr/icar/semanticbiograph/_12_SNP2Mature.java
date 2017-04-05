package it.cnr.icar.semanticbiograph;

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

public class _12_SNP2Mature {

    private static String timeConversion(long seconds) {

        final int MINUTES_IN_AN_HOUR = 60;
        final int SECONDS_IN_A_MINUTE = 60;

        long minutes = seconds / SECONDS_IN_A_MINUTE;
        seconds -= minutes * SECONDS_IN_A_MINUTE;

        long hours = minutes / MINUTES_IN_AN_HOUR;
        minutes -= hours * MINUTES_IN_AN_HOUR;

        return hours + " hours " + minutes + " minutes " + seconds + " seconds";
    }

    public static void main(String[] args) throws IOException, GraknValidationException{
        disableInternalLogs();

        String homeDir = System.getProperty("user.home");
		String fileName = homeDir + "/biodb/miRNASNP/snp_in_human_miRNA_seed_region.txt";
        String line;
        int entryCounter = 0;
        //int edgeCounter = 0;
        long startTime = System.currentTimeMillis();

        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        // skip first line
        reader.readLine();

        GraknGraph graph = Grakn.factory(Grakn.DEFAULT_URI, "biograph").getGraph();
        QueryBuilder qb = graph.graql();
        		                    	
        System.out.print("\nLinking SNPs to matures reading from " + fileName + " ");

        while ((line = reader.readLine()) != null) {
            String datavalue[] = line.split("\t");

        	String mirna = datavalue[0];
        	String snpId = datavalue[4];

        	qb.match(
        			var("m").isa("mirnaMature").has("product", mirna),
        			var("s").isa("mirnaSNP").has("snpId", snpId)
        	).insert(
    				var().isa("snpMutation")
    				.rel("mutated", "m")
    				.rel("snp", "s")
    		).execute();

            graph.commit();
            
            entryCounter++;

            if (entryCounter % 1000 == 0) {
            	System.out.print("."); System.out.flush();
            }
        }

        graph.close();

        long stopTime = (System.currentTimeMillis()-startTime)/1000;
        System.out.println("\n\nCreated " + entryCounter + " relations in " + timeConversion(stopTime));

        reader.close();
    }
    
    public static void disableInternalLogs(){
        Logger logger = (Logger) org.slf4j.LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
        logger.setLevel(Level.OFF);
    }

}
