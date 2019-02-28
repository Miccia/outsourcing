package com.example.pamel;

	import org.apache.camel.builder.RouteBuilder;
	import org.apache.camel.component.jackson.JacksonDataFormat;
	import org.apache.camel.model.rest.RestBindingMode;
	import org.apache.camel.model.rest.*;
	import org.springframework.stereotype.Component;

	import java.util.List;

	import org.apache.camel.Exchange;
	import org.apache.camel.LoggingLevel;
	import org.apache.camel.Processor;
	import org.kie.server.api.model.instance.TaskSummary;
	import org.kie.server.client.*;
	
	@Component
	public class CamelRoutingOut extends RouteBuilder{

	@Override
	    public void configure() throws Exception {
		JacksonDataFormat jsonDataFormat = new JacksonDataFormat(main.java.lib.RawInData.class);
		JacksonDataFormat cookedJsonDF   = new JacksonDataFormat(main.java.lib.CookedOutData.class);
   // // String serverUrl = "http://localhost:8080/kie-server/services/rest/server";
  // //  String user = "kserver";
 // //   String password = "66Server";
// //    String containerId ="pamelblu_1.0.0";
		
        
      	from("jms:queue:pamqueue")
			 .log(LoggingLevel.INFO,"SERVICEOut\t:\tmessage consumed from pamqueue")
	         .unmarshal(jsonDataFormat)  
	         .process(new tracedPreProcessorOut())
	         .marshal(cookedJsonDF)
			 .process(new tracedProcessorOut(main.java.jaegerlib.Tracing.init("pamel_amq")))
	            .to("jms:queue:camelJms");

	    from("jms:queue:LogStashQueue")
	    	.log(LoggingLevel.INFO,"in").
	    	process(new Processor() {
        public void process(Exchange exchange) throws Exception {
            String payload = exchange.getIn().getBody(String.class);
            exchange.getOut().setHeader("content-type","application/json");
        	System.out.println(payload);
       }
    }).to("file:///home/miccia/camelLog?fileName=MyFile.txt&charset=utf-8");
	    }

	}


