package sema_jena;

import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.rulesys.GenericRuleReasonerFactory;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.util.PrintUtil;
import org.apache.jena.vocabulary.ReasonerVocabulary;

public class Tut7 {
	private static String demoURI = "http://jena.hpl.hp.com/demo#";

	public static void main(String[] args) {
		//run("assets/tut7_data.ttl", "assets/tut7_rules.ttl");
		if(args.length == 2) {
			run(args[0], args[1]);
		}
	}

	static void run(String fname, String frule) {
		// Register a namespace for use in the demo
		PrintUtil.registerPrefix("demo", demoURI);

		// Create an (RDF) specification of a hybrid reasoner which loads its data from
		// an external file.
		Model m = ModelFactory.createDefaultModel();
		Resource configuration = m.createResource();
		configuration.addProperty(ReasonerVocabulary.PROPruleMode, "hybrid");
		configuration.addProperty(ReasonerVocabulary.PROPruleSet, frule);

		// Create an instance of such a reasoner
		Reasoner reasoner = GenericRuleReasonerFactory.theInstance().create(configuration);

		// Load test data
		Model data = RDFDataMgr.loadModel(fname);
		InfModel infmodel = ModelFactory.createInfModel(reasoner, data);

		// Query for all things related to "a" by "p"
		Property p = data.getProperty(demoURI, "p");
		Resource a = data.getResource(demoURI + "a");
		StmtIterator i = infmodel.listStatements(a, p, (RDFNode) null);
		while (i.hasNext()) {
			System.out.println(" - " + PrintUtil.print(i.nextStatement()));
		}
	}

}
