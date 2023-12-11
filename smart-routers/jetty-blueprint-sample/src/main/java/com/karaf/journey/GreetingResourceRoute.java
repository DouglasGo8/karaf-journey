package com.karaf.journey;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

public class GreetingResourceRoute extends RouteBuilder {
  @Override
  public void configure() {
    /*from("timer://myTimer?period=5s")*/
    from("jetty:http://0.0.0.0:9090/sample").id("http-inbound")
            //.setBody(constant("Hi"))
            .convertBodyTo(String.class)
            .log(LoggingLevel.INFO, "[EXAMPLE INBOUND] Received: ${body}")
            .choice()
              .when(simple("${headers.CamelHttpMethod} == 'POST'"))
                .setHeader("type", jsonpath("$.notification.type"))
                //.log(LoggingLevel.INFO,"${header.type}")
                .choice()
                  .when(simple("${header.type} == 'email'"))
                    .log(LoggingLevel.INFO, "[EXAMPLE INBOUND] Received email notification")
                    .to("direct:email")
                    .setHeader("Exchange.HTTP_RESPONSE_CODE", constant(200))
                  .when(simple("${header.type} == 'http'"))
                    .log(LoggingLevel.INFO, "[EXAMPLE INBOUND] Received http notification")
                    .to("direct:http")
                    .setHeader("Exchange.HTTP_RESPONSE_CODE", constant(200))
                  .otherwise()
                    .log(LoggingLevel.INFO,"[EXAMPLE INBOUND] Unknown notification")
                    .transform(simple("{ \"status\": \"reject\", \"type\": \"unknown\" }"))
                    .setHeader("Exchange.HTTP_RESPONSE_CODE", constant(400))
                .endChoice()
              .otherwise()
                .log(LoggingLevel.INFO,"[EXAMPLE INBOUND] only POST is accepted (${headers.CamelHttpMethod})")
                .setBody(constant("{ \"error\": \"only POST is accepted\" }"))
                .setHeader("Exchange.HTTP_RESPONSE_CODE", constant(500))
            .end();

    from("direct:email")
            .log(LoggingLevel.INFO, "[EXAMPLE EMAIL] Sending notification email")
            .setHeader("to", jsonpath("$.notification.to"))
            .setHeader("subject", constant("Notification"))
            .transform(simple("{ \"status\": \"http requested\", \"service\": \"${header.service}\" }"));

    from("direct:http")
            .log(LoggingLevel.INFO, "[EXAMPLE HTTP] Sending notification http")
            .setHeader("service", jsonpath("$.notification.service"))
            .transform(simple("{ \"status\": \"http requested\", \"service\": \"${header.service}\" }"));

  }
}
