package it.cnr.icar.biograkn;

import static ai.grakn.graql.Graql.var;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Vector;

import org.biojava.bio.BioException;
import org.biojava.bio.seq.Feature;
import org.biojavax.Comment;
import org.biojavax.Namespace;
import org.biojavax.RichObjectFactory;
import org.biojavax.bio.seq.RichSequence;
import org.biojavax.bio.seq.RichSequenceIterator;

import ai.grakn.Grakn;
import ai.grakn.client.LoaderClient;
import ai.grakn.engine.util.ConfigProperties;
import ai.grakn.exception.GraknValidationException;
import ai.grakn.graql.Graql;
import ai.grakn.graql.Var;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

public class _07_MiRBase {

    private static String timeConversion(long seconds) {

        final int MINUTES_IN_AN_HOUR = 60;
        final int SECONDS_IN_A_MINUTE = 60;

        long minutes = seconds / SECONDS_IN_A_MINUTE;
        seconds -= minutes * SECONDS_IN_A_MINUTE;

        long hours = minutes / MINUTES_IN_AN_HOUR;
        minutes -= hours * MINUTES_IN_AN_HOUR;

        return hours + " hours " + minutes + " minutes " + seconds + " seconds";
    }

    public static void main(String[] args) throws IOException, GraknValidationException, NoSuchElementException, BioException{
        disableInternalLogs();

        String homeDir = System.getProperty("user.home");
        int entryCounter = 0;
        int resCounter = 0;
        int relCounter = 0;
        long startTime = System.currentTimeMillis();

        // for grakn 0.11.0
    	System.setProperty(ConfigProperties.CONFIG_FILE_SYSTEM_PROPERTY, "./conf-0.11.0/grakn-engine.properties");
    	System.setProperty(ConfigProperties.LOG_FILE_CONFIG_SYSTEM_PROPERTY, "./conf-0.11.0/logback.xml");

    	LoaderClient loader = new LoaderClient("biograph", Grakn.DEFAULT_URI);

        String fileName = homeDir + "/biodb/miRNA.dat";
        
        System.out.print("\nImporting mirnas from " + fileName + " ");

        BufferedReader br = new BufferedReader(new FileReader(fileName)); 
		Namespace ns = RichObjectFactory.getDefaultNamespace();
		RichSequenceIterator seqs = RichSequence.IOTools.readEMBLRNA(br, ns);

		while (seqs.hasNext()) {
			ArrayList<Var> vars = new ArrayList<Var>();
			
			RichSequence entry = seqs.nextRichSequence();

			String accession = entry.getAccession();
			String name = entry.getName();
			String description = entry.getDescription();
			Vector<String> comments = new Vector<String>();
			
			for (Comment comment : entry.getComments()) {
				String cmt = comment.getComment().replaceAll("\n", " ");
				comments.add(cmt);
			}
			String comment = "";
			if (comments.size() > 0)
				comment = comments.get(0);

			String sequence = entry.getInternalSymbolList().seqString();
			
			Var mirna =
					var("m")
					.isa("mirna")
        			.has("accession", accession)
        			.has("name", name)
        			.has("description", description)
        			.has("comment", comment)
        			.has("sequence", sequence)
        			;
        	
            entryCounter++;
            resCounter += 5;
			
			vars.add(mirna);
			
			Iterator<Feature> itf = entry.getFeatureSet().iterator();
			
			int cnt = 1;
			while (itf.hasNext()) {
				Feature f = itf.next();
				
				String location = f.getLocation().toString();
				String subSequence = sequence.substring(f.getLocation().getMin()-1, f.getLocation().getMax());
				String matAccession = "";
				String matProduct = "";

				@SuppressWarnings("unchecked")
				Map<Object, ?> map = f.getAnnotation().asMap();
				Set<Object> keys = map.keySet();
				for (Object key : keys) {
					String keyString = key.toString();
					String value = (String) map.get(key);
					
					if (keyString.substring(keyString.lastIndexOf(":")+1).equals("accession"))
						matAccession = value;
					
					if (keyString.substring(keyString.lastIndexOf(":")+1).equals("product"))
						matProduct = value;
				}

				Var mature = 
						var("mat" + cnt)
	        			.isa("mirnaMature")
	        			.has("accession", matAccession)
	        			.has("product", matProduct)
	        			.has("sequence", subSequence)
	        			.has("location", location)
	        			;
				
				entryCounter++;
				resCounter += 4;

				vars.add(mature);
				
				Var rel = 
	        			var("rel" + cnt)
						.isa("precursorOf")
						.rel("precursor", "m")
						.rel("mature", "mat" + cnt)
						;

				cnt++;
				relCounter++;

				vars.add(rel);				
			}

			loader.add(Graql.insert(vars));
			
            if (entryCounter % 1000 == 0) {
            	System.out.print("."); System.out.flush();
            }
		}

        loader.flush();
        loader.waitToFinish();

        long stopTime = (System.currentTimeMillis()-startTime)/1000;
        System.out.println("\n\nCreated " + entryCounter + " entities, " + resCounter + " resources and " + relCounter + " relations in " + timeConversion(stopTime));

        br.close();
    }
    
    public static void disableInternalLogs(){
        Logger logger = (Logger) org.slf4j.LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
        logger.setLevel(Level.OFF);
    }
    
}
