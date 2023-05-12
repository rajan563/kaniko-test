package com.entoss.pomfret.esb.processor;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.springframework.stereotype.Service;
import org.apache.camel.Exchange;

import static com.entoss.pomfret.util.Constants.TOKEN_REQUEST_URL;
import static com.entoss.pomfret.util.Constants.CLIENT_ID;
import static com.entoss.pomfret.util.Constants.CLIENT_SECRET;
import static com.entoss.pomfret.util.Constants.USERNAME;
import static com.entoss.pomfret.util.Constants.PASSWORD;


@Service
public class OltuJavaClient {
    
    public void getToken(Exchange exchange) {
        try {
            
            OAuthClient client = new OAuthClient(new URLConnectionClient());
            OAuthClientRequest request = OAuthClientRequest.tokenLocation(TOKEN_REQUEST_URL)
                    .setGrantType(GrantType.PASSWORD)
                    .setClientId(CLIENT_ID)
                    .setClientSecret(CLIENT_SECRET)
                    .setUsername(USERNAME)
                    .setPassword(PASSWORD)
                    .buildBodyMessage();

            String token = client.accessToken(request, OAuth.HttpMethod.POST, OAuthJSONAccessTokenResponse.class)
                    .getAccessToken();
            exchange.getIn().setHeader("Authorization", "Bearer " + token);

        } catch (Exception exn) {
            exn.printStackTrace();
        }
    }

}