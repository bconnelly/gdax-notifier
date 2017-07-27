package com.bconnelly.gdax.notifier.service;

import com.bconnelly.gdax.notifier.enums.EthStatus;
import com.bconnelly.gdax.notifier.repository.EthMatchesRepository;
import com.bconnelly.gdax.notifier.representation.ETH_USD_MATCH;
import com.bconnelly.gdax.notifier.representation.USER_ALERT;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Bryan on 7/21/2017.
 */
@Service
public class EthService {

    private EthMatchesRepository repository = new EthMatchesRepository();

    public List<ETH_USD_MATCH> fetchMatchesSinceSequence(int sequence){
        return repository.getSinceSequenceId(sequence);
    }

    public List<ETH_USD_MATCH> fetchLastNMatches(int nMatches){
        return repository.getLastN(String.valueOf(nMatches));
    }

    public EthStatus setNewAlert(String user, int value, boolean alertIfAbove){
        return repository.setNewAlert(user, value, alertIfAbove);
    }

    public List<USER_ALERT> getAlerts(String user){
        return repository.getAlerts(user);
    }
}
