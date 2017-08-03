package com.bconnelly.gdax.notifier.repository;

import com.bconnelly.gdax.notifier.representation.RestRequestRepresentation;
import com.bconnelly.gdax.notifier.representation.RestResponseRepresentation;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
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
        RestResponseRepresentation response = new RestResponseRepresentation();

        String timestamp = getGdaxTime();

        try {
            ClientResponse webResponse = webResource.header("CB-ACCESS-SIGN", createSignature(request, timestamp))
                    .header("CB-ACCESS-KEY", System.getenv("gdax_access_key"))
                    .header("CB-ACCESS-PASSPHRASE", System.getenv("gdax_access_passphrase"))
                    .header("CB-ACCESS-TIMESTAMP", timestamp)
                    .get(ClientResponse.class);

            System.out.println("Response: " + webResponse.getEntity(String.class));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private String createSignature(RestRequestRepresentation request, String date) throws NoSuchAlgorithmException, InvalidKeyException {

        //convert request to json string
        Gson gson = new Gson();
        String body = gson.toJson(request);

        //decode the access key and convert it to an encryption key
        byte[] decodedKey = Base64.getDecoder().decode(System.getenv("gdax_access_key"));
        SecretKey encryptionKey = new SecretKeySpec(decodedKey, "HmacSHA1");

        //setup mac for proper encryption type
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(encryptionKey);

        //setup params to be encrypted
//        String date = String.valueOf(Instant.now().toEpochMilli()/1000);
        String method = "GET";
        String requestPath = "https://api-public.sandbox.gdax.com/orders";
        byte[] byteBody = (date + method + requestPath + body).getBytes();

        byte[] digest = Base64.getEncoder().encode(mac.doFinal(byteBody));

        String retString = new BigInteger(digest).toString(16);

        System.out.println(retString);

        return retString;
    }

    private String getGdaxTime(){
        RestTemplate template = new RestTemplate();
        timeResponse response = template.getForObject("https://api-public.sandbox.gdax.com/time", timeResponse.class);

        System.out.println("Time: " + response.epoch);

        return response.epoch;
    }

    private static class timeResponse{
        private static String iso;
        private static String epoch;

        public String getIso() {
            return iso;
        }

        public void setIso(String iso) {
            this.iso = iso;
        }

        public String getEpoch() {
            return epoch;
        }

        public void setEpoch(String epoch) {
            this.epoch = epoch;
        }
    }
}
