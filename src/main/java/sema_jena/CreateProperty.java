package sema_jena;

import java.util.Iterator;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.ObjectProperty;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;

public class CreateProperty {
	// https://jena.apache.org/documentation/io/

	public static void main(String[] args) {
		test1();
	}


	static void test1() {
		// Create the base model
		OntModel base = ModelFactory.createOntologyModel();
		base.read("assets/wine.rdf", "RDF/XML");

		// Create a wine made by Viet for this exam
		String wine_NS = "http://www.w3.org/TR/2003/PR-owl-guide-20031209/wine#";
		OntClass wine = base.getOntClass(wine_NS + "RedBordeaux");
		Individual vietWine = base.createIndividual(wine_NS + "DrinkOneTime_FlyToHeaven", wine);
		
		OntClass black_wine = base.createClass(wine_NS + "BlackWine");
		OntClass best_wine = base.createClass(wine_NS + "Drink_and_Sleep4ever");
		
		ObjectProperty hasPoison = base.createObjectProperty(wine_NS + "hasPoison");
		hasPoison.addDomain(best_wine);
		hasPoison.addRange(black_wine);
		//hasPoison.addLabel("has poison", "en");
		hasPoison.addLabel("èâÇﬂÇ‹ÇµÇƒÅI", "jp");
		
		vietWine.addProperty(hasPoison, "cyanure");
		listProperty(hasPoison, true);
		//listProperty(hasPoison, false);

		// List the asserted types
		for (Iterator<Resource> i = vietWine.listRDFTypes(true); i.hasNext();) {
			System.out.println(vietWine.getURI() + " is asserted in class " + i.next());
		}

		
		
		// Create the reasoning model using the base
		OntModel inf = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_MICRO_RULE_INF, base);

		// List the inferred types
		//vietWine = inf.getIndividual(wine_NS + "DrinkOneTime_FlyToHeaven-madebyViet");
		//listProperty(vietWine, true);
		
		//for (Iterator<Resource> i = vietWine.listRDFTypes(true); i.hasNext();) {
		//	System.out.println(vietWine.getURI() + " is asserted in class " + i.next());
		//}
	}
	
	private static void listProperty(Resource resource, boolean stripUri) {
		Statement statement;
		System.out.println("============= Properties of " + resource.getLocalName() + "===============");
		
		for(StmtIterator i = resource.listProperties(); i.hasNext();) {
			statement = i.next();
			if(stripUri) {
				if(statement.getObject().isLiteral())
					System.out.println("Resource: " + statement.getPredicate().getLocalName() + " => " + statement.getObject().asLiteral().toString());
				else if(statement.getObject().isResource() && statement.getObject().asResource().getLocalName() != null)
					System.out.println("Resource: " + statement.getPredicate().getLocalName() + " => " + statement.getObject().asResource().getLocalName());
			} else {
				System.out.println(statement);
			}
		}
		System.out.println("==================================================");
	}

}
