package com.bconnelly.gdax.notifier.repository;

import com.bconnelly.gdax.notifier.representation.RestResponseRepresentation;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Created by Bryan on 7/30/2017.
 */
public class EthRestRepository {

    public void marketOrder(String size, String funds, String buyOrSell) throws NoSuchAlgorithmException, InvalidKeyException {

        Client client = Client.create();
        WebResource webResource = client.resource("https://api-public.sandbox.gdax.com/orders");

        webResource.header("CB-ACCESS-SIGN", createSignature());
        webResource.header("CB-ACCESS-KEY", System.getenv("gdax_access_key"));
        webResource.header("CB-ACCESS-PASSPHRASE", System.getenv("gdax_access_passphrase"));
        webResource.header("CB-ACCESS-TIMESTAMP", System.nanoTime());


        RestResponseRepresentation response = new RestResponseRepresentation();
        try {
            response = webResource.get(RestResponseRepresentation.class);
//            response = template.getForObject(new URI("https://api-public.sandbox.gdax.com/orders"), RestResponseRepresentation.class);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private String createSignature(){
        System.out.println(System.getenv("gdax_access_key"));
        System.out.println(System.getenv("gdax_passphrase_key"));
        System.out.println(System.getenv("CASSANDRA_HOME"));

        String ret = "";

        //create the signature for the request
        byte[] decodedKey = Base64.getDecoder().decode(System.getenv("gdax_access_key"));






        return ret;
    }
}
