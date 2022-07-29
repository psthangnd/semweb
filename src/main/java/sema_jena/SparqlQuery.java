package sema_jena;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

public class SparqlQuery {

	public static void main(String[] args) {
		sparqlTest();
	}

	static void sparqlTest() {
		// Create the base model
		OntModel model = ModelFactory.createOntologyModel();
		model.read("assets/data.rdf", "RDF/XML");
		// FileManager.get().addLocatorClassLoader(Test1.class.getClassLoader());
		// Model model =
		// FileManager.get().loadModel("C:\\workspace_javacore\\sema_jena\\assets\\data.rdf");

		String queryString = "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "prefix foaf: <http://xmlns.com/foaf/0.1/> " + "SELECT * WHERE {" + " ?person foaf:name ?x ."

				+ " ?person foaf:knows ?person2 ." + " ?person2 foaf:name ?y ."

				// + " FILTER(?x = \"Egon Willighagen\")"
				+ "}";
		sparqlQuery(queryString, model);
	}

	static void sparqlQuery(String queryString, Model model) {
		Query query = QueryFactory.create(queryString);
		QueryExecution exe = QueryExecutionFactory.create(query, model);

		try {
			ResultSet results = exe.execSelect();
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();
				Literal name = soln.getLiteral("x");
				System.out.println(name);
			}
		} finally {
			exe.close();
		}
	}
}
