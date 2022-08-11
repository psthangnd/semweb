package sema_jena;

import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.util.PrintUtil;

// https://www.programcreek.com/java-api-examples/?class=org.apache.jena.riot.RDFDataMgr&method=loadModel
public class Read4_7 {

	public static void main(String[] args) {
		// https://jena.apache.org/documentation/inference/#owl
		owlExample();
	}
	
	static void owlExample() {
		Model schema = RDFDataMgr.loadModel("assets/practice/owlDemoSchema.rdf");
		Model data = RDFDataMgr.loadModel("assets/practice/owlDemoData.rdf");
		Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
		reasoner = reasoner.bindSchema(schema);

		InfModel infmodel = ModelFactory.createInfModel(reasoner, data);
		Resource nForce = infmodel.getResource("urn:x-hp:eg/nForce");
		System.out.println("nForce *:");
		printStatements(infmodel, nForce, null, null);
	}

	public static void printStatements(Model m, Resource s, Property p, Resource o) {
		for (StmtIterator i = m.listStatements(s, p, o); i.hasNext();) {
			Statement stmt = i.nextStatement();
			System.out.println(" - " + PrintUtil.print(stmt));
		}
	}

}
