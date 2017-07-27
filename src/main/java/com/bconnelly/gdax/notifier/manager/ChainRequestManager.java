package com.bconnelly.gdax.notifier.manager;

import com.bconnelly.gdax.notifier.representation.SocketResponseRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by Bryan on 7/26/2017.
 */

@ContextConfiguration(locations = "classpath:application-context.xml")
public class ChainRequestManager {

    @Autowired
    @Qualifier("requestChannel")
    private DirectChannel request;

    @Autowired
    @Qualifier("responseChannel")
    private QueueChannel response;

    public void executeChain(SocketResponseRepresentation representation){

        Message<SocketResponseRepresentation> socketMessage = MessageBuilder.withPayload(representation).build();
        request.send(socketMessage);

        Message<?> recievedMessage = response.receive(3000);
        System.out.println("Returned to manager");
    }

}
