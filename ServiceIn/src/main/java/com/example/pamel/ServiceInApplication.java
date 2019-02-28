package com.example.pamel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import org.apache.camel.component.servlet.*;


@SpringBootApplication
//@EnableAutoConfiguration
public class ServiceInApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceInApplication.class, args);
	}
	
	/*@Bean
	  public ServletRegistrationBean camelServletRegistrationBean() {
	    ServletRegistrationBean registration = new ServletRegistrationBean(new CamelHttpTransportServlet(), "/camel/*");
	    registration.setName("CamelServlet");
	    return registration;
	  }*/
	}



