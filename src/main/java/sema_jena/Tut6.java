package sema_jena;

import java.io.StringReader;
import java.util.Iterator;

import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.rulesys.GenericRuleReasoner;
import org.apache.jena.reasoner.rulesys.Rule;

public class Tut6 {
	private static String NS = "urn:x-hp:eg/";

	public static void main(String args[]) {
		Model rawData = modelFromN3("eg:r eg:concatFirst eg:p .\n" + "eg:r eg:concatSecond eg:q .\n"
				+ "eg:A eg:p eg:B .\n" + "eg:B eg:q eg:C .\n");
		Resource A = rawData.getResource(NS + "A");
		String rules = "[r1: (?c eg:concatFirst ?p), (?c eg:concatSecond ?q) -> "
				+ " [r1b: (?x ?c ?y) <- (?x ?p ?z) (?z ?q ?y)] ]";
		Reasoner reasoner = new GenericRuleReasoner(Rule.parseRules(rules));
		InfModel inf = ModelFactory.createInfModel(reasoner, rawData);
		System.out.println("A * * =>");
		Iterator list = inf.listStatements(A, null, (RDFNode) null);
		while (list.hasNext()) {
			System.out.println(" - " + list.next());
		}
	}

	public static Model modelFromN3(String src) {
		String fullSource = "@prefix owl: <http://www.w3.org/2002/07/owl#> .\n"
				+ "@prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .\n"
				+ "@prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> .\n" + "@prefix eg: <" + NS + ">.\n"
				+ "@prefix : <#> .\n" + src + "\n";
		Model result = ModelFactory.createDefaultModel();
		result.read(new StringReader(fullSource), "", "N3");
		return result;
	}

}
