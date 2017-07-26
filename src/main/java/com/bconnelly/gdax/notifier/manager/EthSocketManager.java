package com.bconnelly.gdax.notifier.manager;

import com.bconnelly.gdax.notifier.service.EthSocketEndpoint;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.Callable;

/**
 * Created by Bryan on 7/18/2017.
 */
@Controller
public class EthSocketManager implements Callable<String>{

    @Override
    public String call() {

        final EthSocketEndpoint socketRepo;

        try {
            socketRepo = new EthSocketEndpoint(new URI("wss://ws-feed.gdax.com"));

            socketRepo.addMessageHandler(
                    (String message) -> System.out.println("Message: " + message)
            );

            while(true){
                Thread.sleep(10000);
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("Thread Killed");
        }

        return "Called";
    }

}
