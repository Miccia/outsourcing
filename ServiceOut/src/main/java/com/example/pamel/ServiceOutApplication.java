package com.example.pamel;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@EnableAutoConfiguration
@SpringBootApplication
public class ServiceOutApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceOutApplication.class, args);
	}
	@Bean
	  public ServletRegistrationBean camelServletRegistrationBean() {
	    ServletRegistrationBean registration = new ServletRegistrationBean(new CamelHttpTransportServlet(), "/camel/*");
	    registration.setName("CamelServlet");
	    return registration;
	  }
}

