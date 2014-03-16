package es.tlc.motivational.core;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import es.tlc.motivational.model.Frase;
import es.tlc.motivational.util.ReadCVS;

public class InsertData {

	public static void main(String[] args) {

		ApplicationContext ctx = new GenericXmlApplicationContext("SpringConfig.xml");		
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

		mongoOperation.dropCollection(Frase.class);
		List<String[]> lista = ReadCVS.getFrases("src/main/resources/FrasesMotivacion.csv");
		
		int i = 0;
		for(String[] frase : lista) {
			Frase f = new Frase(frase[0], frase[1], frase[2], false);
			mongoOperation.save(f);
			i++;
			System.out.println(i);
		}

	}

}