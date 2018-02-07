package com.counter.maintainer.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages= {"com.counter.maintainer.controller","com.counter.maintainer.repository","com.counter.maintainer.service"})
@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories("com.counter.maintainer.repository")
@EntityScan("com.counter.maintainer.data.contracts")
public class TellerMaintenanceApp extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TellerMaintenanceApp.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(TellerMaintenanceApp.class, args);
	}
}
