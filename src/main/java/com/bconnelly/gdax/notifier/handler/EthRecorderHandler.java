package com.bconnelly.gdax.notifier.handler;

import com.bconnelly.gdax.notifier.representation.RestResponseRepresentation;
import com.bconnelly.gdax.notifier.service.EthRecorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.handler.AbstractMessageHandler;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * Created by Bryan on 7/26/2017.
 */
@Component
public class EthRecorderHandler extends AbstractMessageHandler {

    @Autowired
    private EthRecorderService service;

    @Override
    protected void handleMessageInternal(Message<?> message) throws Exception {
        System.out.println("Handling message");
        service.recordMarketMatch((RestResponseRepresentation)message.getPayload());
    }
}
