package com.entoss.pomfret.esb.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class RestConsumerRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        //// @formatter:off
        restConfiguration()
                .component("undertow")
                .port("{{api.port}}")
                .scheme("{{api.scheme}}")
                .contextPath("{{api.contextpath}}")
                .bindingMode(RestBindingMode.off)
                .apiContextPath("/api-doc")
                .apiProperty("api.title",  "{{camel.springboot.name}}")
                .apiProperty("api.version", "{{service.version}}")
                ;

        rest().post("/fiata/registerdocument")
                .consumes(MediaType.APPLICATION_JSON_VALUE)
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .id("direct-rest-consumer-route")
                .to("direct:call-fiata")
                ;

        // @formatter:on
    }

}
