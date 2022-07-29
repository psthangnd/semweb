package sema_jena;

import java.time.LocalDate;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.DC;

public class CreateResource {

	public static void main(String[] args) {

	}
	
	static void create() {
		String url = "https://huts.edu.vn";
		String author = "Duc Thang";
		String title = "Sematic Web";
		LocalDate date = java.time.LocalDate.now();
		
		/*
		 * Model model = new ModelMem(); Resource tut = model.createResource(url);
		 * tut.addProperty(DC.creator, author); tut.addProperty(DC.title, title);
		 * tut.addProperty(DC.date, date.toString());
		 */
	}

}
