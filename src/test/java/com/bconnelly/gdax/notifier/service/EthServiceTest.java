package com.bconnelly.gdax.notifier.service;

import com.bconnelly.gdax.notifier.representation.ETH_USD_MATCH;
import org.junit.Test;

import java.net.URISyntaxException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Bryan on 7/25/2017.
 */
public class EthServiceTest {

    private EthService ethService = new EthService();

    public EthServiceTest() throws URISyntaxException {
    }

    @Test
    public void testFetchMatchesSinceSequence() throws Exception {
        List<ETH_USD_MATCH> matches = ethService.fetchMatchesSinceSequence(861116860);
        assertTrue(matches.get(0).getSequence() >= 861116860);

    }

    @Test
    public void testFetchLastNMatches() throws Exception {
        List<ETH_USD_MATCH> matches1 = ethService.fetchLastNMatches(10);
        assertTrue(matches1.size() == 10);

        List<ETH_USD_MATCH> matches2 = ethService.fetchLastNMatches(100);
        assertTrue(matches2.size() == 100);

        List<ETH_USD_MATCH> matches3 = ethService.fetchLastNMatches(1000);
        assertTrue(matches3.size() == 1000);
    }

}