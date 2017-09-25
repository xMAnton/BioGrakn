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
import java.util.Iterator;

import ai.grakn.Grakn;
import ai.grakn.GraknGraph;
import ai.grakn.GraknSession;
import ai.grakn.GraknTxType;
import ai.grakn.concept.Concept;
import ai.grakn.concept.Entity;
import ai.grakn.concept.RelationType;
import ai.grakn.concept.Role;
import ai.grakn.graql.MatchQuery;
import ai.grakn.graql.QueryBuilder;

public class _06_Pathway2GO {

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
		
		String line;
		int edgeCounter = 0;
        long startTime = System.currentTimeMillis();

		String fileName = homeDir + "/biodb/reactome/pathway2go.txt";

		System.out.print("\nImporting pathway2go associations from " + fileName + " ");

	    BufferedReader reader = new BufferedReader(new FileReader(fileName));
        line = reader.readLine(); //skip header line

        GraknSession session = Grakn.session(Grakn.DEFAULT_URI, "biograkn");
        
        while ((line = reader.readLine()) != null) {
            GraknGraph graph = session.open(GraknTxType.BATCH);
            QueryBuilder qb = graph.graql();
            
        	String datavalue[] = line.split("\t");
        	
        	String pathwayId = datavalue[0];
        	String goId = datavalue[1];

        	MatchQuery findPathway = qb.match(var("p").isa("pathway").has("pathwayId", pathwayId)).limit(1);        	
        	Iterator<Concept> concepts = findPathway.get("p").iterator();

        	if (concepts.hasNext()) {
        		
        		Entity pathway = concepts.next().asEntity();
        		
            	MatchQuery findGo = qb.match(var("g").isa("go").has("goId", goId)).limit(1);        	
            	concepts = findGo.get("g").iterator();
            	
            	if (concepts.hasNext()) {
            		
            		Entity go = concepts.next().asEntity();
            		
            		RelationType annotationType = graph.getRelationType("annotation");
            		Role functionalAnnotation = graph.getRole("functionalAnnotation");
            		Role annotatedEntity = graph.getRole("annotatedEntity");
            		
            		annotationType.addRelation()
        				.addRolePlayer(functionalAnnotation, go)
        				.addRolePlayer(annotatedEntity, pathway);
            		
            		edgeCounter++;
            		
                    if (edgeCounter % 10000 == 0) {
                    	System.out.print("."); System.out.flush();
                    }
            	}
        	}
        	
            graph.commit();
        }

        session.close();

        long stopTime = (System.currentTimeMillis()-startTime)/1000;
        System.out.println("\n\nCreated " + edgeCounter + " relations in " + timeConversion(stopTime));
        
        reader.close();
	}
	
    public static void disableInternalLogs(){
    	org.apache.log4j.Logger logger4j = org.apache.log4j.Logger.getRootLogger();
		logger4j.setLevel(org.apache.log4j.Level.toLevel("INFO"));
    }
}
