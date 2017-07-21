package com.bconnelly.gdax.notifier.service;

import com.bconnelly.gdax.notifier.representation.SocketResponseRepresentation;
import org.springframework.stereotype.Service;

/**
 * Created by Bryan on 7/18/2017.
 */

@Service
public class EthSocketService {

    public void printMarketMovement(SocketResponseRepresentation response){

        if(response.getType() != null && response.getType().equals("match")) {

//            System.out.println(response);

            if (response.getSide().equals("buy")) {
                System.out.println("BUY  at " + response.getPrice() + " for " + response.getSize() + " ETH");
            } else if (response.getSide().equals("sell")) {
                System.out.println("SELL at " + response.getPrice() + " for " + response.getSize() + " ETH");
            } else {
                System.out.println("Side unknown: " + response.getSide());
            }

        }
    }

    public void notifyIfThresholdMet(SocketResponseRepresentation response) {

        int minThreshold = 200;

        if(response.getType() != null && response.getType().equals("match")){
//            check if the most recent trade breaks the threshold
            if(Double.valueOf(response.getPrice()) >= minThreshold){

            }
        }
    }
}
