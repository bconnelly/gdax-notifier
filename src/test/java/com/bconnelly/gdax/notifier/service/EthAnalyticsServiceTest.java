package com.bconnelly.gdax.notifier.service;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Bryan on 8/13/2017.
 */
public class EthAnalyticsServiceTest {

    private EthAnalyticsService service = new EthAnalyticsService();

    @Test
    public void countMatches() throws Exception {
        long result = service.countMatches();
        System.out.println(result);
    }

}