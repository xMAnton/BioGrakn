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

public class _10_Pathway2Mirna {

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
		
		String line;
		int edgeCounter = 0;
        long startTime = System.currentTimeMillis();

		String fileName = homeDir + "/biodb/reactome/miRBase2Reactome_All_Levels.txt";

		System.out.print("\nImporting mirna2pathway associations from " + fileName + " ");

	    BufferedReader reader = new BufferedReader(new FileReader(fileName));

        GraknGraph graph = Grakn.factory(Grakn.DEFAULT_URI, "biograph").getGraph();
        QueryBuilder qb = graph.graql();
        		                
        while ((line = reader.readLine()) != null) {
        	String datavalue[] = line.split("\t");
        	
        	String accessionId = datavalue[0];
        	String pathwayId = datavalue[1];

        	if (accessionId.startsWith("miR"))
        		accessionId = "MI" + accessionId.substring(3);

        	qb.match(
        			var("p").isa("pathway").has("pathwayId", pathwayId),
        			var("m").isa("mirna").has("accession", accessionId)
        	).insert(
    				var().isa("containing")
    				.rel("container", "p")
    				.rel("contained", "m")
    		).execute();

    		edgeCounter++;
    		graph.commit();

            if (edgeCounter % 100 == 0) {
            	System.out.print("."); System.out.flush();
            }
        }
        
        graph.close();
        
        long stopTime = (System.currentTimeMillis()-startTime)/1000;
        System.out.println("\n\nCreated " + edgeCounter + " relations in " + timeConversion(stopTime));
        
        reader.close();

	}
	
    public static void disableInternalLogs(){
        Logger logger = (Logger) org.slf4j.LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
        logger.setLevel(Level.OFF);
    }
}
