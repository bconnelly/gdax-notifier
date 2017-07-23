package com.bconnelly.gdax.notifier.service;

import com.bconnelly.gdax.notifier.repository.EthMatchesRepository;
import com.bconnelly.gdax.notifier.representation.ETH_USD_MATCH;

import java.util.List;

/**
 * Created by Bryan on 7/21/2017.
 */
public class EthService {

    private EthMatchesRepository repository = new EthMatchesRepository();

    public List<ETH_USD_MATCH> fetchMatchesSinceSequence(int sequence){
        return repository.getSinceSequenceId(sequence);
    }

    public List<ETH_USD_MATCH> fetchLastNMatches(String nMatches){
        return repository.getLastN(nMatches);
    }
}
