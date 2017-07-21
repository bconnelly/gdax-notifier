package com.bconnelly.gdax.notifier.controller;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Bryan on 7/20/2017.
 */

@RestController
public class EthController {

    @RequestMapping("/getUpdates")
    public void getUpdates(@RequestHeader String lastUpdate){
        System.out.println("pinged endpoint");
    }
}
