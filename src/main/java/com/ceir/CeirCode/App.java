package com.ceir.CeirCode;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableJpaAuditing
@SpringBootApplication	
@EnableAutoConfiguration	
@ComponentScan("com.ceir.CeirCode")


@EnableCaching
@EnableWebMvc

@EntityScan({"com.ceir.CeirCode.model"})
@ComponentScan({"com.ceir.CeirCode"})

//@EnableEncryptableProperties

public class App { 
	public static void main(String[] args) {
		System.out.println("inside main class");
		SpringApplication.run(App.class, args);
	}	
	 public void onStartup(ServletContext servletContext) throws ServletException {
	        servletContext.setInitParameter("listings", "true");
	    }
}
	