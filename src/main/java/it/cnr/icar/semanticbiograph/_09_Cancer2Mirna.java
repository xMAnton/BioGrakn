package it.cnr.icar.semanticbiograph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import ai.grakn.Grakn;
import ai.grakn.GraknGraph;
import ai.grakn.exception.GraknValidationException;
import ai.grakn.graql.QueryBuilder;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

import static ai.grakn.graql.Graql.*;

public class _09_Cancer2Mirna {

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
		
        // cat miRCancerSeptember2015.txt | awk -F '\t' '{ print $1 "\t" $2 "\t" $3; }' | sort | uniq > cancer2mirna.txt
        String fileName = homeDir + "/biodb/cancer2mirna.txt";
		String line;
		int edgeCounter = 0;
        long startTime = System.currentTimeMillis();

        GraknGraph graph = Grakn.factory(Grakn.DEFAULT_URI, "biograph").getGraph();
        QueryBuilder qb = graph.graql();
        		
	    BufferedReader reader = new BufferedReader(new FileReader(fileName));

        System.out.print("\nImporting cancer2mirna associations from " + fileName + " ");
                
        while ((line = reader.readLine()) != null) {
        	String datavalue[] = line.split("\t");
        	
        	String mirId = datavalue[0];
        	String cancerName = datavalue[1];
        	String cancerProfile = datavalue[2];
        	
        	String regulation = cancerProfile + "Regulation";
        	String regulator  = cancerProfile + "Regulator";
        	String regulated  = cancerProfile + "Regulated";

        	qb.match(
        			var("m").isa("mirna").has("name", mirId),
        			var("c").isa("cancer").has("name", cancerName)
        	).insert(
    				var().isa(regulation)
    				.rel(regulator, "m")
    				.rel(regulated, "c")
    		).execute();

    		try {
				graph.commit();
	    		edgeCounter++;
			} catch (GraknValidationException e) {
				graph.rollback();
				edgeCounter--;
			}

            if (edgeCounter % 200 == 0) {
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
