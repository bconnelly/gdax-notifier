package com.bconnelly.gdax.notifier.manager;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Created by Bryan on 7/25/2017.
 */
public class EthSocketManagerTest {

    EthSocketManager manager = new EthSocketManager();

    //This does not return anything by design. Just test to make sure nothing throws
    @Test
    public void run() throws Exception {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.invokeAll(Arrays.asList(new EthSocketManager()), 5, TimeUnit.SECONDS);
        executorService.shutdown();
    }

}