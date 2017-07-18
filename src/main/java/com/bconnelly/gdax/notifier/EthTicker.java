package com.bconnelly.gdax.notifier;


import com.google.gson.Gson;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Bryan on 7/17/2017.
 */

@Repository
public class EthTicker {

    private String url = "https://api.gdax.com/products/ETH-USD/ticker";

    public TickerResponseRepresentation getLastPrice(){
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);

        // add request header
        CloseableHttpResponse response;
        try {
            response = client.execute(request);

            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer result = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) result.append(line);

            Gson gson = new Gson();
            return gson.fromJson(result.toString(), TickerResponseRepresentation.class);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
