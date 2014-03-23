package es.tlc.motivational.web.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import es.tlc.motivational.config.MongoConfiguration;
import es.tlc.motivational.config.MotivationalPhrasesConfiguration;
import es.tlc.motivational.config.ThymeleafConfig;
import es.tlc.motivational.config.WebMvcConfig;

public class MotivationalPhrasesInitializer implements WebApplicationInitializer {

	private static final String DISPATCHER_SERVLET = "DispatcherServlet";
	
	private static final Class<?>[] configurationClasses = new Class<?>[] { WebMvcConfig.class,
		MotivationalPhrasesConfiguration.class, MongoConfiguration.class };

	public void onStartup(ServletContext servletContext) throws ServletException {
		registerListener(servletContext);
		registerDispatcherServlet(servletContext);
	}

	private void registerListener(ServletContext servletContext) {
		WebApplicationContext rootContext = createContext(configurationClasses);
		servletContext.addListener(new ContextLoaderListener(rootContext));
	}

	private void registerDispatcherServlet(ServletContext servletContext) {
		WebApplicationContext dispatcherContext = createContext(ThymeleafConfig.class);
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet(DISPATCHER_SERVLET, new DispatcherServlet(
				dispatcherContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/*");
	}

	private WebApplicationContext createContext(final Class<?>... annotatedClasses) {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(annotatedClasses);
		return context;
	}
}
