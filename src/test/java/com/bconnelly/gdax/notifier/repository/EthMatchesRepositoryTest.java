package com.bconnelly.gdax.notifier.repository;

import org.junit.Test;

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
    public void getSinceSequenceId() throws Exception {
        matchesRepository.getSinceSequenceId(100);
    }

}