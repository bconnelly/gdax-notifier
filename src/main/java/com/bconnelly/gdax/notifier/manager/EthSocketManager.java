package com.bconnelly.gdax.notifier.manager;

import com.bconnelly.gdax.notifier.service.EthSocketEndpoint;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Bryan on 7/18/2017.
 */
@Controller
public class EthSocketManager {

    public static void listen() throws URISyntaxException, InterruptedException {

        final EthSocketEndpoint socketRepo = new EthSocketEndpoint(new URI("wss://ws-feed.gdax.com"));

        Gson gson = new Gson();

        socketRepo.addMessageHandler(
                (String message) -> System.out.println("Message: " + message)
        );

        while(true){
            Thread.sleep(10000);
        }

    }
}
