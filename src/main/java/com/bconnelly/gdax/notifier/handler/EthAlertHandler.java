package com.bconnelly.gdax.notifier.handler;

import com.bconnelly.gdax.notifier.representation.SocketResponseRepresentation;
import com.bconnelly.gdax.notifier.service.EthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.handler.AbstractMessageHandler;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * Created by Bryan on 7/26/2017.
 */
@Component
public class EthAlertHandler extends AbstractMessageHandler{

    @Autowired
    private EthService service;

    @Override
    protected void handleMessageInternal(Message<?> message) throws Exception {
        System.out.println("Handled message in alert handler");
//        TODO: Update this to reflect actual method for retrieving user info from payload
        service.getAlerts("all");
    }
}
