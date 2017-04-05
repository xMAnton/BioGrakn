package it.cnr.icar.semanticbiograph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import ai.grakn.Grakn;
import ai.grakn.client.LoaderClient;
import ai.grakn.engine.util.ConfigProperties;
import ai.grakn.graql.Graql;
import ai.grakn.graql.Var;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

import ai.grakn.exception.GraknValidationException;

import static ai.grakn.graql.Graql.var;

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

    public static void main(String[] args) throws IOException, GraknValidationException{
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
    	System.setProperty(ConfigProperties.CONFIG_FILE_SYSTEM_PROPERTY, "./conf-0.11.0/grakn-engine.properties");
    	System.setProperty(ConfigProperties.LOG_FILE_CONFIG_SYSTEM_PROPERTY, "./conf-0.11.0/logback.xml");

    	LoaderClient loader = new LoaderClient("biograph", Grakn.DEFAULT_URI);
    	
    	//GraknGraph graph = Grakn.factory(Grakn.DEFAULT_URI, "biograph").getGraphBatchLoading();
        //QueryBuilder qb = graph.graql();
       
        System.out.print("\nImporting NCBI gene info entries from " + fileName + " ");

        while ((line = reader.readLine()) != null) {
            String datavalue[] = line.split("\t");

            String taxId = datavalue[0];
            if (!taxId.equals("9606"))
                continue;

            String symbol = datavalue[10].equals("-") ? datavalue[2] : datavalue[10];

            Var gene =
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
            			;

            //graph.commit();
            
            loader.add(Graql.insert(gene));
            
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
        Logger logger = (Logger) org.slf4j.LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
        logger.setLevel(Level.INFO);
    }
}