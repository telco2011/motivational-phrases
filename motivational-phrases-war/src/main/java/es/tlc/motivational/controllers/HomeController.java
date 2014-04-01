package es.tlc.motivational.controllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.tlc.motivational.model.Frase;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private MongoOperations mongoOperation;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.debug("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		StringBuilder formattedDate = new StringBuilder(dateFormat.format(date));

		model.addAttribute("serverTime", formattedDate.toString());

		Random random = new Random();
		Query query = new Query(new Criteria("contador").is(random.nextInt(111)).and("mostrado").is(false));
		Frase frase = mongoOperation.findOne(query, Frase.class);
		
		model.addAttribute("phrase", frase.getFrase());
		model.addAttribute("author", frase.getAutor());

		return "home";
	}

}
