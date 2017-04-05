package it.cnr.icar.biograkn;

import static ai.grakn.graql.Graql.var;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import ai.grakn.Grakn;
import ai.grakn.client.LoaderClient;
import ai.grakn.engine.util.ConfigProperties;
import ai.grakn.exception.GraknValidationException;
import ai.grakn.graql.Graql;
import ai.grakn.graql.Var;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

public class _08_miRCancer {

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
        
        // cat miRCancerSeptember2015.txt | awk -F '\t' '{ print $2; }' | sort | uniq > miRCancers.txt
        String fileName = homeDir + "/biodb/miRCancers.txt";
        
        String line;
        int entryCounter = 0;
        long startTime = System.currentTimeMillis();

        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        // skip first line
        reader.readLine();

        // for grakn 0.11.0
    	System.setProperty(ConfigProperties.CONFIG_FILE_SYSTEM_PROPERTY, "./conf-0.11.0/grakn-engine.properties");
    	System.setProperty(ConfigProperties.LOG_FILE_CONFIG_SYSTEM_PROPERTY, "./conf-0.11.0/logback.xml");

    	LoaderClient loader = new LoaderClient("biograph", Grakn.DEFAULT_URI);
        
        System.out.print("\nImporting cancers from " + fileName + " ");

        while ((line = reader.readLine()) != null) {
            String datavalue[] = line.split("\t");

            Var cancer = var("c")
            			.isa("cancer")
            			.has("name", datavalue[0])
            			;

            loader.add(Graql.insert(cancer));
            
            entryCounter++;

            if (entryCounter % 5000 == 0) {
            	System.out.print("."); System.out.flush();
            }
        }

        loader.flush();
        loader.waitToFinish();

        long stopTime = (System.currentTimeMillis()-startTime)/1000;
        System.out.println("\n\nCreated " + entryCounter + " entities and " + entryCounter + " resources in " + timeConversion(stopTime));

        reader.close();
    }
    
    public static void disableInternalLogs(){
        Logger logger = (Logger) org.slf4j.LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
        logger.setLevel(Level.OFF);
    }
    
}
