package com.example.pamel;

import org.apache.camel.Exchange;

public class tracedPreProcessorOut implements org.apache.camel.Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		
		exchange.getOut().setBody(new main.java.lib.CookedOutData());
		exchange.getOut().setHeader("processInstanceId", exchange.getIn().getHeader("processInstanceId"));
	}

}