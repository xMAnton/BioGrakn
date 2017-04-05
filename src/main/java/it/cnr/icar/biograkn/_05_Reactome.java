package it.cnr.icar.biograkn;

import static ai.grakn.graql.Graql.var;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import ai.grakn.Grakn;
import ai.grakn.client.LoaderClient;
import ai.grakn.engine.util.ConfigProperties;
import ai.grakn.exception.GraknValidationException;
import ai.grakn.graql.Graql;
import ai.grakn.graql.Var;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

public class _05_Reactome {

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
        String fileName = homeDir + "/biodb/reactome/pathways.txt";        
        String line;
        int entryCounter = 0;
        //int edgeCounter = 0;
        long startTime = System.currentTimeMillis();

        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        // skip first line
        reader.readLine();

        // for grakn 0.11.0
    	System.setProperty(ConfigProperties.CONFIG_FILE_SYSTEM_PROPERTY, "./conf-0.11.0/grakn-engine.properties");
    	System.setProperty(ConfigProperties.LOG_FILE_CONFIG_SYSTEM_PROPERTY, "./conf-0.11.0/logback.xml");

    	LoaderClient loader = new LoaderClient("biograph", Grakn.DEFAULT_URI);
        
        System.out.println("\nReading homo sapiens Reactome entries from " + fileName + " ");

        HashMap<String, String> pathwayName = new HashMap<String, String>();
        
        while ((line = reader.readLine()) != null) {        	
        	String datavalue[] = line.split("\t");
        	
        	String id = datavalue[0];
        	String name = datavalue[1];
        	
        	pathwayName.put(id, name);
        }
        reader.close();
        
        fileName = homeDir + "/biodb/reactome/pathwayDisease.txt";
        System.out.println("Reading pathways diseases from " + fileName);
        HashMap<String, String> pathwayDisease = new HashMap<String, String>();

        reader = new BufferedReader(new FileReader(fileName));
        line = reader.readLine(); //skip header line
        
        while ((line = reader.readLine()) != null) {        	
        	String datavalue[] = line.split("\t");
        	
        	if (datavalue.length <= 1)
        		continue;
        	
        	String id = datavalue[0];
        	String disease = datavalue[1];
        	
        	pathwayDisease.put(id, disease);
        }
        reader.close();
        
		fileName = homeDir + "/biodb/reactome/pathwaySummation.txt";
        System.out.println("Reading pathways summations from " + fileName);
        HashMap<String, String> pathwaySummation = new HashMap<String, String>();

        reader = new BufferedReader(new FileReader(fileName));
        line = reader.readLine(); //skip header line
        
        while ((line = reader.readLine()) != null) {        	
        	String datavalue[] = line.split("\t");
        	
        	if (datavalue.length <= 1)
        		continue;
        	
        	String id = datavalue[0];
        	String summation = datavalue[1];
        	
        	pathwaySummation.put(id, summation);
        }
        reader.close();       
 
        System.out.print("\nCreating pathways nodes ");
		
        for (String id : pathwayName.keySet()) {
        	String name = pathwayName.get(id);
        	String disease = (pathwayDisease.get(id) == null) ? "" : pathwayDisease.get(id);
        	String summation = (pathwaySummation.get(id) == null) ? "" : pathwaySummation.get(id);
        	
            Var pathway = var("p")
        			.isa("pathway")
        			.has("pathwayId", id)
        			.has("name", name)
        			.has("disease", disease)
        			.has("summation", summation)
        			;

	        loader.add(Graql.insert(pathway));
        
        	entryCounter++;
        	
            if (entryCounter % 100 == 0) {
            	System.out.print("."); System.out.flush();
            }
        }
        
        loader.flush();
        loader.waitToFinish();

        long stopTime = (System.currentTimeMillis()-startTime)/1000;
        System.out.println("\n\nCreated " + entryCounter + " entities and " + (entryCounter*4) + " resources in " + timeConversion(stopTime));

        reader.close();
    }
    
    public static void disableInternalLogs(){
        Logger logger = (Logger) org.slf4j.LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
        logger.setLevel(Level.OFF);
    }
}
