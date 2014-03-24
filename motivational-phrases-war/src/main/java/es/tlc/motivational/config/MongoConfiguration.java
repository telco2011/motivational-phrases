package es.tlc.motivational.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@Configuration
@EnableMongoRepositories
@PropertySource("classpath:/META-INF/application.properties")
public class MongoConfiguration extends AbstractMongoConfiguration {

	@Autowired
    Environment env;
	
	@Override
	protected String getDatabaseName() {
		return "davemongo";
	}

	@Override
	public Mongo mongo() throws Exception {
		return new MongoClient(getMongoURI());
	}
	
	private MongoClientURI getMongoURI() throws Exception {
		return new MongoClientURI("mongodb://readuser:readuser@troup.mongohq.com:10011/davemongo");
	}

}
