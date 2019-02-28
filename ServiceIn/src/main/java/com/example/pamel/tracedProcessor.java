package com.example.pamel;

//import org.apache.camel.Exchange;
//import io.jaegertracing.internal.JaegerTracer;
//import io.opentracing.Span;
//import io.opentracing.Tracer;
//import main.java.lib.Tracing;


public class tracedProcessor implements org.apache.camel.Processor{
	
	public tracedProcessor(){
		super();
		}

	@Override
	public void process(org.apache.camel.Exchange ex) throws Exception {
		
		System.out.println("INCOMING EXCHANGE!!!!!!!!!!!!!!\n"+
							ex.getIn().getHeaders()+"\n\n\n!!!!!!!!!!!!!!!\n"+
							ex.getIn().getHeader("CamelJacksonUnmarshalType")+
							"\n\n\n"+
							ex.getIn().getBody()+
							"\n\n\n\n"+
							"Sending Exchange to : \t"+
							ex.getIn().getHeader("destination")+
							"\n\n\n\n!!!!!!!!!!!!!!!!!!!!!!!!!");

		io.jaegertracing.internal.JaegerTracer jtrc = main.java.jaegerlib.Tracing.init("pamel_amq");
	       try (io.opentracing.Scope scope = jtrc.buildSpan("ServiceIn").startActive(true)) {
            	
            	Object rid=ex.getIn().getBody();
            	if(rid instanceof main.java.lib.RawInData)
					rid = (main.java.lib.RawInData) ex.getIn().getBody();
                else
                	rid = (main.java.lib.CookedOutData) ex.getIn().getBody();

                scope.span().setTag("processing_json",""+rid.toString());
	        }
		
	}

}
