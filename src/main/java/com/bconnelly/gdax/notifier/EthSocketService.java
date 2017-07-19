package com.bconnelly.gdax.notifier;

import org.springframework.stereotype.Service;

/**
 * Created by Bryan on 7/18/2017.
 */

@Service
public class EthSocketService {

    public void printMarketMovement(SocketResponseRepresentation response){

        if(response.getOrder_type() != null && response.getOrder_type().equals("market")) {

            if (response.getSide().equals("buy")) {
                System.out.println("BUY  at " + response.getPrice() + " for " + response.getSize() + " ETH");
            } else if (response.getSide().equals("sell")) {
                System.out.println("SELL at " + response.getPrice() + " for " + response.getSize() + " ETH");
            } else {
                System.out.println("Side unknown: " + response.getSide());
            }

        }
    }

}
