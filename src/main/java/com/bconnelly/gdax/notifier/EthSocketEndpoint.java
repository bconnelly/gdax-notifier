package com.bconnelly.gdax.notifier;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import java.net.URI;

/**
 * Created by Bryan on 7/18/2017.
 */
@ClientEndpoint
public class EthSocketEndpoint {

    private Session userSession;
    private MessageHandler handler;

    private EthSocketService service = new EthSocketService();

    public EthSocketEndpoint(URI endpoint){
        try{
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, endpoint);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @OnOpen
    public void onOpen(Session session){
        System.out.println(session.getId() + " opened a connection");
        userSession = session;
        subscribeEthUsd();
    }

    @OnMessage
    public void onMessage(String message){
//        System.out.println("Received message: " + message);

        Gson gson = new Gson();
        SocketResponseRepresentation response = gson.fromJson(message, SocketResponseRepresentation.class);
        service.printMarketMovement(response);
    }

    @OnClose
    public void onClose(Session session, CloseReason reason){
        System.out.println(session.getId() + " closed the session. Reason: " + reason.getReasonPhrase());
    }

    public void addMessageHandler(MessageHandler handler){
        this.handler = handler;
    }

    private void subscribeEthUsd(){
        //            subscribe to ETH-USD updates
        String subscriptionString = "{\n" +
                "    \"type\": \"subscribe\",\n" +
                "    \"product_ids\": [\n" +
                "        \"ETH-USD\"\n" +
                "    ]\n" +
                "}";
        userSession.getAsyncRemote().sendText(subscriptionString);
        System.out.println("Sent message");
    }

    public interface MessageHandler{
        void handleMessage(String message);
    }

}
