package com.facebook.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.facebook.community")
@EntityScan("com.facebook.community.model")
@EnableJpaRepositories("com.facebook.community.repository")
public class Application extends  SpringBootServletInitializer{

	   // Spring Boot Application Main class
  @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
	 

	
	public static void main(String[] args) {
		     SpringApplication.run(Application.class, args);
	    }

}