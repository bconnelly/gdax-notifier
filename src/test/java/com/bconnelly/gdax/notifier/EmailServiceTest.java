package com.bconnelly.gdax.notifier;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Bryan on 7/19/2017.
 */
public class EmailServiceTest {
    @Test
    public void sendMail() throws Exception {
        EmailService.sendMail("Test Subject", "Test Body");
    }

}