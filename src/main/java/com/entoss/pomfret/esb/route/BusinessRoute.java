package com.entoss.pomfret.esb.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.entoss.pomfret.esb.processor.OltuJavaClient;
import static com.entoss.pomfret.util.Constants.CLIENT_ID;

@Component
public class BusinessRoute extends RouteBuilder {

    @Autowired
    OltuJavaClient client;
    
    @Override
    public void configure() throws Exception {

        from("direct:call-fiata").routeId("direct-call-fiata")
        .removeHeaders("*")
        .bean(client,"getToken")
        .log("respomne ${header.Authorization}")
        .setHeader("Content-Type",constant("application/json"))
        .setHeader("X-Alias-ID",constant(CLIENT_ID))
        .setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.POST))
        .to("https://api.kapsule-eu-uat.komgo.io/api/trakk/v0/integrations/fiata/fbl-json?softwareProviderId=a3910ad9-e982-45bd-a10f-0f5e2ed38206&bridgeEndpoint=true&throwExceptionOnFailure=false")
        .log("done...")
        
        ;

    }

}
