package sema_jena;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.aksw.jena_sparql_api.algebra.expr.transform.ExprTransformVirtualBnodeUris;
import org.aksw.jena_sparql_api.rx.SparqlRx;
import org.apache.jena.query.DatasetFactory;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFactory;
import org.apache.jena.riot.RDFDataMgr;

public class LoticoExamples {

	public static void main(String[] args) {
		testBNodeRaw();
		//testBnodeSkolemized();
	}
	
	static void testBNodeRaw() {
		Model model = RDFDataMgr.loadModel("assets/bnodes-example.ttl");
		try(QueryExecution qe = QueryExecutionFactory.create("SELECT * { ?s ?p ?0 }", model)) {
			System.out.println(ResultSetFormatter.asText(qe.execSelect()));
		}
	}
	
//	static RDFConnection wrapWithVirtualBnodeUris(RDFConnection conn, String profile) {
//		Model model = RDFDataMgr.loadModel("bnodes-example.ttl");
//		RDFDataMgrEx.execSparql(model, "udf-inferences.sparql");
//		
//		Set<String> activeProfiles = new HashSet<> ( Arrays.asList("http://ns.aksw.org/profile/" + profile));
//		ExprTransformVirtualBnodeUris xform = ExprTransformVirtualBnodeUris.createTransformFromUdfModel(model, activeProfiles);
//		
//		RDFConnection result = RDFConnectionFactoryEx.wrapWithQueryTransform(conn, xform::rewrite);
//		return result;
//	}
//	
//	static void testBnodeSkolemized() {
//		Model model = RDFDataMgr.loadModel("bnodes-example.ttl");
//		
//		RDFConnection rawConn = RDFConnectionFactory.connect(DatasetFactory.wrap(model));
//		RDFConnection conn = wrapWithVirtualBnodeUris(rawConn, "jena");
//		
//		try(QueryExecution qe = conn.query("SELECT * { ?s ?p ?o }")) {
//			System.out.println(ResultSetFormatter.asText(qe.execSelect()));
//		}
//		
//		String s = SparqlRx.execSelect(() -> conn.query("SELECT * { ?s ?p ?o }"))
//				.firstElement()
//				.map(qs -> qs.get("s").asNode().getURI())
//				.blockingGet();
//		
//		System.out.println("Picked: " + s);
//		try(QueryExecution qe = conn.query("SELECT * { ?s ?p ?0 FILTER(?s = <" + s + ">)}")) {
//			System.out.println(ResultSetFormatter.asText(qe.execSelect()));
//		}
//	}

}
