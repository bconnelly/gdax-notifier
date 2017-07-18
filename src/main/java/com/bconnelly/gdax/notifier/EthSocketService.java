package com.bconnelly.gdax.notifier;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Bryan on 7/18/2017.
 */
public class EthSocketService {

    public static void listen() throws URISyntaxException, InterruptedException {

        final EthSocketRepo socketRepo = new EthSocketRepo(new URI("wss://ws-feed.gdax.com"));

        socketRepo.addMessageHandler(
            (String message) ->
                System.out.println("Message: " + message));

        while(true){
            Thread.sleep(10000);
        }

    }
}
