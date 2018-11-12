package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

@Configuration
@PropertySource("some.properties")
public class Application {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	public static void main(String[] args) {
		new AnnotationConfigApplicationContext(Application.class);
	}

	@Bean
	static PropertySourcesPlaceholderConfigurer pspc() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Value("${configuration.projectName}")
	private String fieldValue;

	@Autowired
	Application(@Value("${configuration.projectName}") String pn) {
		logger.info("Application Constructor : {}", pn);
	}

	@Value("${configuration.projectName}")
	void setProjectName(String projectName) {
		logger.info("setProjectName : {}", projectName);
	}

	@Autowired
	void setEnvironment(Environment env) {
		logger.info("setEnvironment : {}", env.getProperty("configuration.projectName"));
	}

	@Bean
	InitializingBean both(Environment env, @Value("${configuration.projectName}") String projectName) {
		return () -> {
			logger.info("@Bean with both dependencies projectName : ({})", projectName);
			logger.info("@Bean with both dependencies env : ({})", env.getProperty("configuration.projectName"));
		};
	}

	@PostConstruct
	void afterPropertiesSet() throws Throwable {
		logger.info("fieldValue : {}", this.fieldValue);
	}

}
