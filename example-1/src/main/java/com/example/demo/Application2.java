package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

@Configuration
public class Application2 {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Bean
	static PropertySourcesPlaceholderConfigurer pspc() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Configuration
	@Profile("prod")
	@PropertySource("some-prod.properties")
	public static class ProdConfiguration {
		@Bean
		InitializingBean init() {
			return () -> LoggerFactory.getLogger(getClass()).info("prod InitializingBean");
		}
	}

	@Configuration
	@Profile({"default", "dev"})
	@PropertySource("some.properties")
	public static class DefaultConfiguration {
		@Bean
		InitializingBean init() {
			return () -> LoggerFactory.getLogger(getClass()).info("default InitializingBean");
		}
	}

	@Bean
	InitializingBean which(Environment e, @Value("${configuration.projectName}") String projectName) {
		return () -> {
			logger.info("activeProfiles :'{}'", StringUtils.arrayToCommaDelimitedString(e.getActiveProfiles()));
			logger.info("configuration.projectName : {}", projectName);
		};
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		//ac.getEnvironment().setActiveProfiles("dev");
		ac.getEnvironment().setActiveProfiles("prod");
		//ac.getEnvironment().setActiveProfiles("dev");
		ac.register(Application2.class);
		ac.refresh();
	}
}
