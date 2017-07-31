package com.bconnelly.gdax.notifier.repository;

import com.bconnelly.gdax.notifier.representation.SocketRequestRepresentation;
import com.bconnelly.gdax.notifier.service.EthSocketEndpoint;
import com.google.gson.Gson;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Bryan on 7/30/2017.
 */
public class EthWebsocketPoster {

    private EthSocketEndpoint socketEndpoint;


    public EthWebsocketPoster() throws URISyntaxException{
        socketEndpoint = new EthSocketEndpoint(new URI("wss://ws-feed.gdax.com"));
    }

    public boolean sendMarketOrder(String size, String funds, String buyOrSell){

        //construct message to send to websocket
        Gson gson = new Gson();
        SocketRequestRepresentation requestRepresentation = new SocketRequestRepresentation.Builder()
                                                            .setSize(size)
                                                            .setFunds(funds)
                                                            .setSide(buyOrSell)
                                                            .setType("market")
                                                            .Build();

        String requestString = gson.toJson(requestRepresentation);
        System.out.println("Request String: " + requestString);

        socketEndpoint.sendMessage(requestString);

        return false;
    }
}
