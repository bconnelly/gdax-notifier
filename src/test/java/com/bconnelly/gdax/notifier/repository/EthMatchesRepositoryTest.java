package com.bconnelly.gdax.notifier.repository;

import com.bconnelly.gdax.notifier.representation.ETH_USD_MATCH;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Bryan on 7/21/2017.
 */
public class EthMatchesRepositoryTest {

    private EthMatchesRepository matchesRepository = new EthMatchesRepository();

    @Test
    public void insert() throws Exception {
    }

    @Test
    public void testGetSinceSequenceId() throws Exception {
        List<ETH_USD_MATCH> matches = matchesRepository.getSinceSequenceId(100);
        Assert.assertTrue(matches.size() > 1000);
    }

    @Test
    public void testGetLastN() throws Exception {
        List<ETH_USD_MATCH> matches1 = matchesRepository.getLastN("10");
        Assert.assertTrue(matches1.size() == 10);

        List<ETH_USD_MATCH> matches2 = matchesRepository.getLastN("100");
        Assert.assertTrue(matches2.size() == 100);

        List<ETH_USD_MATCH> matches3 = matchesRepository.getLastN("1000");
        Assert.assertTrue(matches3.size() == 1000);
    }

    @Test
    public void testSetNewAlert() throws Exception {
        matchesRepository.setNewAlert("Bryan", 200, true);
    }

}