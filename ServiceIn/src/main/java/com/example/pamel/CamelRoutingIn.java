package com.example.pamel;

	
	
	import org.apache.camel.builder.RouteBuilder;
//	import org.apache.camel.model.rest.RestBindingMode;
	//import org.apache.camel.spi.DataFormat;
	import org.springframework.stereotype.Component;
	//import io.jaegertracing.internal.JaegerTracer;
	//import io.opentracing.Span;
	//import io.opentracing.Tracer;
	//import main.java.lib.Tracing;
	

	
	
	//import org.apache.camel.Exchange;
	import org.apache.camel.LoggingLevel;
	//import org.apache.camel.Processor;
	import org.apache.camel.component.jackson.JacksonDataFormat;
	
	
	

	@Component
	public class CamelRoutingIn extends RouteBuilder{
		
	@Override
	    public void configure() throws Exception {
					
		//JacksonDataFormat jsonDataFormat = new JacksonDataFormat(main.java.lib.RawInData.class);
		JacksonDataFormat jsonDataFormat = new JacksonDataFormat();
			jsonDataFormat.setAllowJmsType(true);
			jsonDataFormat.setAllowUnmarshallType(true);
		//JacksonDataFormat format = new JacksonDataFormat(); format.setAllowJmsType(true);
	    	restConfiguration()
	    		.component("restlet")
	    		.host("localhost").port("8081");


	            rest("pampam")
	            .post()
	            .consumes("application/json")
	            .to("direct:chittese?exchangePattern=InOnly");
	            //HOPPELA !!!
	            from("direct:chittese")
	            .log(LoggingLevel.INFO,"SERVICE0\t:\trest summoned by pam")
	            .unmarshal(jsonDataFormat)
	            .process(new tracedProcessor())
	            .marshal(jsonDataFormat)
	            .recipientList(simple("${header.destination}"));
	            //.to("jms:queue:pamqueue?exchangePattern=InOnly");
	    }

	}


