package com.bconnelly.gdax.notifier.service;

import com.bconnelly.gdax.notifier.enums.EthStatus;
import com.bconnelly.gdax.notifier.repository.EthCassandraRepository;
import com.bconnelly.gdax.notifier.repository.EthWebsocketPoster;
import com.bconnelly.gdax.notifier.representation.ETH_USD_MATCH;
import com.bconnelly.gdax.notifier.representation.USER_ALERT;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by Bryan on 7/21/2017.
 */
@Service
public class EthService {

    private EthCassandraRepository cassandraRepository = new EthCassandraRepository();
    private EthWebsocketPoster websocketPoster = new EthWebsocketPoster();

    public EthService() throws URISyntaxException {
    }

    public List<ETH_USD_MATCH> fetchMatchesSinceSequence(int sequence){
        return cassandraRepository.getSinceSequenceId(sequence);
    }

    public List<ETH_USD_MATCH> fetchLastNMatches(int nMatches){
        return cassandraRepository.getLastN(String.valueOf(nMatches));
    }

    public EthStatus setNewAlert(String user, int value, boolean alertIfAbove){
        return cassandraRepository.setNewAlert(user, value, alertIfAbove);
    }

    public List<USER_ALERT> getAlerts(String user){

        ETH_USD_MATCH currentPrice = fetchLastNMatches(1).get(0);

        List<USER_ALERT> results = cassandraRepository.getAlerts(user);
        for(USER_ALERT alert : results){
            if(alert.isActive()){
                //if we're looking for a price above the current price and the current price is over the alert, OR
                //if we're looking for a price below the current price and the current price is below the alert
                alert.setTriggered(alert.isAlert_if_above() == (Double.valueOf(alert.getAlert_value()) < Double.valueOf(currentPrice.getPrice())));
            }
        }

        return results;



        //create new data entity to define if alert is triggered or not
    }

    public boolean marketOrder(String size, String funds, String buyOrSell){

        websocketPoster.sendMarketOrder(size, funds, buyOrSell);

        return false;
    }
}
