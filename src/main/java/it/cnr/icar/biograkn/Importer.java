package it.cnr.icar.biograkn;

import java.util.ArrayList;

import ai.grakn.GraknTxType;
import ai.grakn.client.Grakn;
import ai.grakn.graql.Query;

public class Importer {
    static ArrayList<Object> queries = new ArrayList<>();
    static ArrayList<Query<?>> mqueries = new ArrayList<>();
    
	public static void importer(Grakn.Session session, String fileName) throws Exception {
		throw new Exception("You must implement the method importer");
	}

	protected static void addM(Query<?> query) {
		mqueries.add(query);
	}
	
	protected static void endM() {
		queries.add(mqueries);
		mqueries.clear();
	}
	
	protected static void add(Query<?> query) {
		queries.add(query);
	}
	
	@SuppressWarnings("unchecked")
	protected static void exec(Grakn.Session session) {
		Grakn.Transaction graknTx = session.transaction(GraknTxType.BATCH);
		
		queries.forEach(q -> {
			if (q instanceof Query<?>)
				((Query<?>)q).withTx(graknTx).execute();
			else
				((ArrayList<Query<?>>)q).forEach(mq -> mq.withTx(graknTx).execute());
		});
		
		graknTx.commit();
		queries.clear();
	}
}
