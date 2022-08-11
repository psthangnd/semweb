package sema_jena;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;

public class Example3 {

	public static void main(String[] args) {
		write2OtherFormat("assets/foaf-a-matic.rdf");
	}
	
	static void write2OtherFormat(String rdfFile) {
		// Create the base model
		OntModel model = ModelFactory.createOntologyModel();
		model.read(rdfFile, "RDF/XML");
		
		model.write(System.out, "TURTLE");
		//model.write(System.out, "RDF/JSON");
	}

}
