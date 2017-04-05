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

public class _11_miRNASNP {

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

        // for grakn 0.11.0
    	System.setProperty(ConfigProperties.CONFIG_FILE_SYSTEM_PROPERTY, "./conf-0.11.0/grakn-engine.properties");
    	System.setProperty(ConfigProperties.LOG_FILE_CONFIG_SYSTEM_PROPERTY, "./conf-0.11.0/logback.xml");

    	LoaderClient loader = new LoaderClient("biograph", Grakn.DEFAULT_URI);
    	
        System.out.print("\nImporting SNPs in human miRNA seed region from " + fileName + " ");

        while ((line = reader.readLine()) != null) {
            String datavalue[] = line.split("\t");

        	//String mirna = datavalue[0];
        	String chr = datavalue[1];
        	int mirStart = Integer.valueOf(datavalue[2]);
        	int mirEnd = Integer.valueOf(datavalue[3]);
        	String SNPid = datavalue[4];
        	int lostNum = Integer.valueOf(datavalue[5]);
        	int gainNum = Integer.valueOf(datavalue[6]);

            Var snp =
            		var("s")
            			.isa("mirnaSNP")
            			.has("snpId", SNPid)
            			.has("chr", chr)
            			.has("mirStart", mirStart)
            			.has("mirEnd", mirEnd)
            			.has("lostNum", lostNum)
            			.has("gainNum", gainNum)
            			;

            //graph.commit();
            
            loader.add(Graql.insert(snp));
            
            entryCounter++;

            if (entryCounter % 1000 == 0) {
            	System.out.print("."); System.out.flush();
            }
        }

        loader.flush();
        loader.waitToFinish();

        long stopTime = (System.currentTimeMillis()-startTime)/1000;
        System.out.println("\n\nCreated " + entryCounter + " entities and " + (entryCounter*6) + " resources in " + timeConversion(stopTime));

        reader.close();
    }
    
    public static void disableInternalLogs(){
        Logger logger = (Logger) org.slf4j.LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
        logger.setLevel(Level.INFO);
    }

}
