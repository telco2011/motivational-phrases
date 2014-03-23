package es.tlc.motivational.core;

import java.util.Random;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import es.tlc.motivational.model.Frase;

public class GetFrase {

	public static void main(String[] args) {

		ApplicationContext ctx = new GenericXmlApplicationContext("SpringConfig.xml");		
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

		long frasesSize = mongoOperation.count(new Query(), "motivacion");
		System.out.println("Total de frases: " + frasesSize);
		
		Random random = new Random();
		Query query = new Query();
		int i = 0;
		Frase frase = null;
		
		do {
			query = new Query(new Criteria("contador").is(random.nextInt(111)).and("mostrado").is(false));
			frase = mongoOperation.findOne(query, Frase.class);
			System.out.println(frase);
			i++;
		} while (frase==null && i<frasesSize);
		frase.setMostrado(true);
		mongoOperation.save(frase);
		long frasesSinMostrarSize = mongoOperation.count(new Query(new Criteria("mostrado").is(false)), "motivacion");
		System.out.println("Total frases sin mostrar: "+ frasesSinMostrarSize);
	}

}