package com.bconnelly.gdax.notifier.repository;

import com.bconnelly.gdax.notifier.representation.RestRequestRepresentation;
import com.bconnelly.gdax.notifier.representation.RestResponseRepresentation;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Timestamp;
import java.util.Base64;
import java.util.Date;

/**
 * Created by Bryan on 7/30/2017.
 */
public class EthRestRepository {

    public void marketOrder(String size, String funds, String buyOrSell) throws NoSuchAlgorithmException, InvalidKeyException {

        Client client = Client.create();
        WebResource webResource = client.resource("https://api-public.sandbox.gdax.com/orders");

        RestRequestRepresentation request = new RestRequestRepresentation(size, funds, buyOrSell);

        webResource.header("CB-ACCESS-SIGN", createSignature(request));
        webResource.header("CB-ACCESS-KEY", System.getenv("gdax_access_key"));
        webResource.header("CB-ACCESS-PASSPHRASE", System.getenv("gdax_access_passphrase"));
        webResource.header("CB-ACCESS-TIMESTAMP", System.nanoTime());


        RestResponseRepresentation response = new RestResponseRepresentation();
        try {
            response = webResource.get(RestResponseRepresentation.class);
            System.out.println("Response: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private String createSignature(RestRequestRepresentation request) throws NoSuchAlgorithmException, InvalidKeyException {
//        System.out.println(System.getenv("gdax_access_key"));
//        System.out.println(System.getenv("gdax_access_passphrase"));
//        System.out.println(System.getenv("CASSANDRA_HOME"));

        Gson gson = new Gson();
        String body = gson.toJson(request);

        //create the signature for the request
        byte[] decodedKey = Base64.getDecoder().decode(System.getenv("gdax_access_key"));
        SecretKey encryptionKey = new SecretKeySpec(decodedKey, "HmacSHA1");

        Mac mac = Mac.getInstance("HmacSHA1");

        mac.init(encryptionKey);

        String date = new Date().toString();
        String method = "GET";
        String requestPath = "https://api-public.sandbox.gdax.com/orders";

        byte[] byteBody = (date + method + requestPath + body).getBytes();

        return new String(Base64.getEncoder().encode(mac.doFinal(byteBody))).trim();
    }
}
