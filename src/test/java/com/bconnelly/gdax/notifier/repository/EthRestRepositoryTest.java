package com.bconnelly.gdax.notifier.repository;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Bryan on 8/1/2017.
 */
public class EthRestRepositoryTest {

    EthRestRepository repo = new EthRestRepository();

    @Test
    public void marketOrder() throws Exception {

        repo.marketOrder("1", "100", "sell");

    }

}