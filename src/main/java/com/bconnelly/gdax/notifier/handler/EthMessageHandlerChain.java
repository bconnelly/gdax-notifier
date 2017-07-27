package com.bconnelly.gdax.notifier.handler;

import org.springframework.integration.handler.MessageHandlerChain;

/**
 * Created by Bryan on 7/27/2017.
 */
public class EthMessageHandlerChain extends MessageHandlerChain {

    public void init(){
        try{
            super.onInit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
