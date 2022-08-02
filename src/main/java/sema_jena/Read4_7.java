package sema_jena;

import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.riot.RDFDataMgr;

public class Read4_7 {

	public static void main(String[] args) {
		// Data
		// : a :Pro
		
		Model schema = RDFDataMgr.loadModel("assets/4.7-schema.rdf");
		Model data = RDFDataMgr.loadModel("assets/4.7-data.rdf");
		Reasoner reasoner = null;
		reasoner.bindSchema(schema);
		InfModel infModel =  ModelFactory.createInfModel(reasoner, data);
		infModel.getResource("");
		//printSttm();
		
	}

}
