/*
 * BioGrakn - A Knowledge Graph-based Semantic Database for Biomedical Sciences
 * Copyright (C) 2017 - Antonio Messina (xMAnton) <antonio.messina@icar.cnr.it>
 *
 * BioGrakn is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * BioGrakn is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with BioGrakn. If not, see <http://www.gnu.org/licenses/gpl.txt>.
 */

package it.cnr.icar.biograkn;

import static ai.grakn.graql.Graql.var;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import ai.grakn.Grakn;
import ai.grakn.GraknGraph;
import ai.grakn.GraknSession;
import ai.grakn.GraknTxType;
import ai.grakn.graql.InsertQuery;
import ai.grakn.graql.QueryBuilder;

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

    public static void main(String[] args) throws IOException {
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

        GraknSession session = Grakn.session(Grakn.DEFAULT_URI, "biograkn");
        		                    	
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
                GraknGraph graph = session.open(GraknTxType.BATCH);
                QueryBuilder qb = graph.graql();

            	if ((hgncId != null) && (!hgncId.equals(""))) {	
            		InsertQuery iq = qb.insert(var().isa("geneName").has("name", hgncId));           		
            		iq.execute();
            		
            		qb.match(
            				var("g").isa("gene").has("geneId", entrezId),
            				var("syn").isa("geneName").has("name", hgncId)
            			).insert(
            				var().isa("entityReference").rel("identifier", "syn").rel("identified", "g")
            			);
            		
            		entryCounter++;
            	}
            	
        		if ((ensemblGeneId != null) && (!ensemblGeneId.equals(""))) {
            		InsertQuery iq = qb.insert(var().isa("geneName").has("name", ensemblGeneId));           		
            		iq.execute();

            		qb.match(
            				var("g").isa("gene").has("geneId", entrezId),
            				var("syn").isa("geneName").has("name", ensemblGeneId)
            			).insert(
            				var().isa("entityReference").rel("identifier", "syn").rel("identified", "g")
            			);
            		
            		entryCounter++;
        		}
        		
        		if ((refseqAccession != null) && (!refseqAccession.equals(""))) {
        			if (refseqAccession.contains("|")) {
        				String refseqId[] = refseqAccession.split("|");
        				for (int i=0; i<refseqId.length; i++) {
                    		InsertQuery iq = qb.insert(var().isa("geneName").has("name", refseqId[i]));           		
                    		iq.execute();

                    		qb.match(
                    				var("g").isa("gene").has("geneId", entrezId),
                    				var("syn").isa("geneName").has("name", refseqId[i])
                    			).insert(
                    				var().isa("entityReference").rel("identifier", "syn").rel("identified", "g")
                    			);

                			entryCounter++;
        				}
        			} else {
                		InsertQuery iq = qb.insert(var().isa("geneName").has("name", refseqAccession));           		
                		iq.execute();

                		qb.match(
                				var("g").isa("gene").has("geneId", entrezId),
                				var("syn").isa("geneName").has("name", refseqAccession)
                			).insert(
                				var().isa("entityReference").rel("identifier", "syn").rel("identified", "g")
                			);

            			entryCounter++;
        			}
        		}
        		
                graph.commit();

                if (entryCounter % 1000 == 0) {
                	System.out.print("."); System.out.flush();
                }
        	}
        }

        session.close();

        long stopTime = (System.currentTimeMillis()-startTime)/1000;
        System.out.println("\n\nCreated " + entryCounter + " entities and " + entryCounter + " relations in " + timeConversion(stopTime));

        reader.close();
    }
    
    public static void disableInternalLogs(){
    	org.apache.log4j.Logger logger4j = org.apache.log4j.Logger.getRootLogger();
		logger4j.setLevel(org.apache.log4j.Level.toLevel("INFO"));
    }

}
