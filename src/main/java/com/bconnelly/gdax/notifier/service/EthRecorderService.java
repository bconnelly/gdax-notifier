package com.bconnelly.gdax.notifier.service;

import com.bconnelly.gdax.notifier.repository.EthCassandraRepository;
import com.bconnelly.gdax.notifier.representation.RestResponseRepresentation;
import org.springframework.stereotype.Service;

/**
 * Created by Bryan on 7/18/2017.
 */

@Service
public class EthRecorderService {

    private EthCassandraRepository ethCassandraRepository = new EthCassandraRepository();

    public void recordMarketMatch(RestResponseRepresentation response){

        if(response.getType() != null && response.getType().equals("match")) {

//            System.out.println(response);

            if (response.getSide().equals("buy")) {
                System.out.println("BUY  at " + response.getPrice() + " for " + response.getSize() + " ETH");
            } else if (response.getSide().equals("sell")) {
                System.out.println("SELL at " + response.getPrice() + " for " + response.getSize() + " ETH");
            } else {
                System.out.println("Side unknown: " + response.getSide());
            }

            ethCassandraRepository.insert(response);

        }
    }
}
