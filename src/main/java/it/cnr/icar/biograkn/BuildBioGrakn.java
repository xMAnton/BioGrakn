package it.cnr.icar.biograkn;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import ai.grakn.Grakn;
import ai.grakn.Keyspace;
import ai.grakn.client.BatchExecutorClient;

public class BuildBioGrakn {

    public static final String defaultKeyspaceName = "biograkn";
    public static final String defaultDataDourcePath = System.getProperty("user.home") + "/datasources" + "/";

	public static void main(String[] args) throws Exception {
		String keyspaceName = defaultKeyspaceName;
		String dataSourcesPath = defaultDataDourcePath;		
		LinkedHashMap<String, String> sourcesModules = new LinkedHashMap<String, String>();

    	Options options = new Options();
    	options.addOption("k", true, "keyspace");
    	options.addOption("d", true, "data source path");
    	options.addOption("h", false, "print this help");

        HelpFormatter formatter = new HelpFormatter();
    	CommandLineParser parser = new DefaultParser();

    	try {
            CommandLine line = parser.parse( options, args );
            
            if (line.hasOption( "h" )) {
                formatter.printHelp("BuildBioGrakn", options);
                System.out.println();
                return;            	
            }

            if (line.hasOption( "k" ))
            	keyspaceName = line.getOptionValue("k");

            if (line.hasOption( "d" ))
            	dataSourcesPath = line.getOptionValue("d");

        } catch(ParseException exp) {
            System.out.println("\n" + exp.getMessage() + "\n");
            formatter.printHelp("BuildBioGrakn", options);
            System.out.println();
            return;
        }
    	
    	File dataSource = new File(dataSourcesPath);
		if (!dataSource.exists()) {
			System.err.println("\nDatasource path " + dataSourcesPath + " does not exist!");
			System.exit(1);
		}

		// disable internal logs
		org.apache.log4j.Logger logger4j = org.apache.log4j.Logger.getRootLogger();
		logger4j.setLevel(org.apache.log4j.Level.toLevel("INFO"));

		// init modules and sources map
		String line;
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("modules.tsv");
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		while ((line = reader.readLine()) != null) {
			if (line.startsWith("#") || line.startsWith("//"))
				continue;
			
			String datavalue[] = line.split("\t");
			String key = datavalue[0];
			String filename = dataSourcesPath + datavalue[1];
			
			File sourcefile = new File(filename);
			if (!sourcefile.exists()) {
				System.err.println("\nDatasource file " + filename + " is missing!");
				System.exit(1);
			}
			
			sourcesModules.put(key, filename);
		}
		reader.close();
		is.close();
		
		System.out.println("\nBuilding BioGrakn ...\n");
		
		long startTime = System.currentTimeMillis();
		
		BatchExecutorClient loader = BatchExecutorClient.newBuilderforURI(Grakn.DEFAULT_URI).build();
		Keyspace keyspace = Keyspace.of(keyspaceName);

		// run import modules
		for (String moduleName : sourcesModules.keySet()) {
			Class<?> c = Class.forName("it.cnr.icar.biograkn." + moduleName);
			Method m = c.getDeclaredMethod("importer", BatchExecutorClient.class, Keyspace.class, String.class);
			m.invoke(null, loader, keyspace, sourcesModules.get(moduleName));
		}
		
		loader.close();
		
		long deltaTime = (System.currentTimeMillis()-startTime)/1000;
        System.out.println("\nBioGrakn built in " + timeConversion(deltaTime) + "\n");
	}
	
	private static String timeConversion(long seconds) {
	    final int MINUTES_IN_AN_HOUR = 60;
	    final int SECONDS_IN_A_MINUTE = 60;

	    long minutes = seconds / SECONDS_IN_A_MINUTE;
	    seconds -= minutes * SECONDS_IN_A_MINUTE;

	    long hours = minutes / MINUTES_IN_AN_HOUR;
	    minutes -= hours * MINUTES_IN_AN_HOUR;

	    return hours + " hours " + minutes + " minutes " + seconds + " seconds";
	}
}
