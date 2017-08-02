package com.bconnelly.gdax.notifier.manager;

import com.bconnelly.gdax.notifier.config.ApplicationContextHolder;
import com.bconnelly.gdax.notifier.config.ChainConfig;
import com.bconnelly.gdax.notifier.handler.EthAlertHandler;
import com.bconnelly.gdax.notifier.handler.EthMessageHandlerChain;
import com.bconnelly.gdax.notifier.handler.EthRecorderHandler;
import com.bconnelly.gdax.notifier.representation.RestResponseRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by Bryan on 7/26/2017.
 */

//@ContextConfiguration(locations = "classpath:application-context.xml")
@Component
public class ChainRequestManager {

//    @Autowired
    private ApplicationContext context = ApplicationContextHolder.getContext();

    @Autowired
    private ChainConfig config;

    private DirectChannel requestChannel = new DirectChannel();

    private QueueChannel responseChannel = new QueueChannel();

//    public ChainRequestManager() {
//        context = SpringApplication.run(ChainRequestManager.class);
//    }

    public void executeChain(RestResponseRepresentation representation){
        //setup channels
        requestChannel = (DirectChannel) context.getBean("inputChannel");
        responseChannel = (QueueChannel) context.getBean("outputChannel");
        //create list of handlers to be called in the chain
        ArrayList<MessageHandler> ethHandlers = new ArrayList<>();
        ethHandlers.add(new EthRecorderHandler());
        ethHandlers.add(new EthAlertHandler());

        //assign handlers to handler chain
        EthMessageHandlerChain handlerChain = new EthMessageHandlerChain();
        handlerChain.setHandlers(ethHandlers);

        //boilerplate
        handlerChain.setBeanFactory(ApplicationContextHolder.getContext());

        //subscribe the handler chain to the output channel
        handlerChain.setOutputChannel(responseChannel);

        //subscribe the handler chain to the input channel
        requestChannel.subscribe(handlerChain);

        //start chain
        handlerChain.init();
        handlerChain.start();

        //create message
        Message<RestResponseRepresentation> socketMessage = MessageBuilder.withPayload(representation).build();

        //send/receive message
        requestChannel.send(socketMessage);

        Message<?> recievedMessage = responseChannel.receive(3000);
        System.out.println("Returned to manager: " + recievedMessage.getPayload());

        //cleanup
        handlerChain.stop();
        requestChannel.unsubscribe(handlerChain);
    }

}
