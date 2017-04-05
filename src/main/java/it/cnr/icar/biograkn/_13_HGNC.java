package it.cnr.icar.biograkn;

import static ai.grakn.graql.Graql.var;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import ai.grakn.Grakn;
import ai.grakn.GraknGraph;
import ai.grakn.exception.GraknValidationException;
import ai.grakn.graql.QueryBuilder;
import ai.grakn.graql.Var;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

public class _13_HGNC {

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
		String fileName = homeDir + "/biodb/protein-coding_gene.txt";
        String line;
        int entryCounter = 0;
        //int edgeCounter = 0;
        long startTime = System.currentTimeMillis();

        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        // skip first line
        reader.readLine();

        GraknGraph graph = Grakn.factory(Grakn.DEFAULT_URI, "biograph").getGraph();
        QueryBuilder qb = graph.graql();
        		                    	
        System.out.print("\nImporting genes names from " + fileName + " ");

        while ((line = reader.readLine()) != null) {
            String datavalue[] = line.split("\t");

            String hgncId = datavalue[0];
        	/*
        	String name = datavalue[2];
        	String locusGroup = datavalue[3];
        	String locusType = datavalue[4];
        	String status = datavalue[5];
        	String location = datavalue[6];
        	String locationSortable = datavalue[7];
        	String aliasSymbol = datavalue[8];
        	String aliasName = datavalue[9];
        	String prevSymbol = datavalue[10];
        	String prevName = datavalue[11];
        	String geneFamily = datavalue[12];
        	String geneFamilyId = datavalue[13];
        	String dateApprovedReserved = datavalue[14];
        	String dateSymbolChanged = datavalue[15];
        	String dateNameChanged = datavalue[16];
        	String dateModified = datavalue[17];
        	*/
        	String entrezId = datavalue[18];
        	String ensemblGeneId = datavalue[19];
        	/*
        	String vegaId = datavalue[20];
        	String ucscId = datavalue[21];
        	String ena = datavalue[22];
        	 */
        	String refseqAccession = datavalue[23];
        	if (datavalue.length < 25)
        		continue;
        	//String ccdsId = datavalue[24];
        	if (datavalue.length < 26)
        		continue;
        	//String uniprotIds = datavalue[25];
        	
        	/*
        	if (datavalue.length < 27)
        		continue;
        	String pubmedId = datavalue[26];
        	if (datavalue.length < 28)
        		continue;
        	String mgdId = datavalue[27];
        	if (datavalue.length < 29)
        		continue;
        	String rgdId = datavalue[28];
        	if (datavalue.length < 30)
        		continue;
        	String lsdb = datavalue[29];
        	if (datavalue.length < 31)
        		continue;
        	String cosmic = datavalue[30];
        	if (datavalue.length < 32)
        		continue;
        	String omimId = datavalue[31];
        	if (datavalue.length < 33)
        		continue;
        	String mirbase = datavalue[32];
        	String homeodb = datavalue[33];
        	if (datavalue.length < 35)
        		continue;
        	String snornabase = datavalue[34];
        	String bioparadigmsSlc = datavalue[35];
        	if (datavalue.length < 37)
        		continue;
        	String orphanet = datavalue[36];
        	if (datavalue.length < 38)
        		continue;
        	String pseudogene = datavalue[37];
        	if (datavalue.length < 39)
        		continue;
        	String hordeId = datavalue[38];
        	if (datavalue.length < 40)
        		continue;
        	String merops = datavalue[39];
        	if (datavalue.length < 41)
        		continue;
        	String imgt = datavalue[40];
        	String iuphar = datavalue[41];
        	if (datavalue.length < 43)
        		continue;
        	String kznfGeneCatalog = datavalue[42];
        	if (datavalue.length < 44)
        		continue;
        	String mamitTrnadb = datavalue[43];
        	String cd = datavalue[44];
        	if (datavalue.length < 46)
        		continue;
        	String lncrnadb = datavalue[45];
        	String enzymeId = datavalue[46];
        	if (datavalue.length < 48)
        		continue;
        	String intermediateFilamentDb = datavalue[47];
        	*/

        	if (!entrezId.equals("")) {
        		Var gene = var("g").isa("gene").has("geneId", entrezId);
        		
        		int cnt = 1;
        		ArrayList<Var> vars = new ArrayList<Var>();
        		
        		if ((hgncId != null) && (!hgncId.equals(""))) {
        			Var syn = var("syn" + cnt).isa("geneName").has("name", hgncId);
        			Var rel = var("rel" + cnt).isa("entityReference").rel("identifier", "syn" + cnt).rel("identified", "g");
        			
        			vars.add(syn);
        			vars.add(rel);
        			
        			entryCounter++;
        			cnt++;
        			
        			//System.out.println(syn);
        			//System.out.println(rel);
        		}
        		
        		if ((ensemblGeneId != null) && (!ensemblGeneId.equals(""))) {
        			Var syn = var("syn" + cnt).isa("geneName").has("name", ensemblGeneId);
        			Var rel = var("rel" + cnt).isa("entityReference").rel("identifier", "syn" + cnt).rel("identified", "g");
        			
        			vars.add(syn);
        			vars.add(rel);
        			
        			entryCounter++;
        			cnt++;
        			
        			//System.out.println(syn);
        			//System.out.println(rel);
        		}
        		
        		if ((refseqAccession != null) && (!refseqAccession.equals(""))) {
        			if (refseqAccession.contains("|")) {
        				String refseqId[] = refseqAccession.split("|");
        				for (int i=0; i<refseqId.length; i++) {
        					Var syn = var("syn" + cnt).isa("geneName").has("name", refseqId[i]);
        					Var rel = var("rel" + cnt).isa("entityReference").rel("identifier", "syn" + cnt).rel("identified", "g");
        					
                			vars.add(syn);
                			vars.add(rel);
                			
                			entryCounter++;
                			cnt++;
                			
                			//System.out.println(syn);
                			//System.out.println(rel);
        				}
        			} else {
        				Var syn = var("syn" + cnt).isa("geneName").has("name", refseqAccession);
        				Var rel = var("rel" + cnt).isa("entityReference").rel("identifier", "syn" + cnt).rel("identified", "g");
        				
            			vars.add(syn);
            			vars.add(rel);
            			
            			entryCounter++;
            			cnt++;
            			
            			//System.out.println(syn);
            			//System.out.println(rel);
        			}
        		}
        		
        		if (cnt > 1) {
                	qb.match(
                			gene
                	).insert(
            				vars
            		).execute();

                	graph.commit();
        		}

        	}
        	
            if (entryCounter % 1000 == 0) {
            	System.out.print("."); System.out.flush();
            }
        }

        graph.close();

        long stopTime = (System.currentTimeMillis()-startTime)/1000;
        System.out.println("\n\nCreated " + entryCounter + " entities and " + entryCounter + " relations in " + timeConversion(stopTime));

        reader.close();
    }
    
    public static void disableInternalLogs(){
        Logger logger = (Logger) org.slf4j.LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
        logger.setLevel(Level.OFF);
    }

}
