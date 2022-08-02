package sema_jena;

import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.riot.RDFDataMgr;


// https://www.programcreek.com/java-api-examples/?class=org.apache.jena.riot.RDFDataMgr&method=loadModel
public class Read4_7 {

	public static void main(String[] args) {
		Model schema = RDFDataMgr.loadModel("assets/practice/4.7-schema.rdf");
		Model data = RDFDataMgr.loadModel("assets/practice/4.7-data.ttl");
		Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
		reasoner.bindSchema(schema);
		InfModel infModel =  ModelFactory.createInfModel(reasoner, data);
		infModel.getResource("http://www.hust.edu.vn/thangnd#");
		
		final StmtIterator iter = infModel.listStatements();
	    while (iter.hasNext()) {
	    	Statement stmt = iter.nextStatement();

	        String sub = stmt.getSubject().toString();
	    	String pred = stmt.getPredicate().toString();
	        String obj = stmt.getObject().toString();
	        
	        System.out.println(sub);
	        System.out.println(pred);
	        System.out.println(obj);
	    }
		
	}

}
