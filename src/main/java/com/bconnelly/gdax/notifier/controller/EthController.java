package com.bconnelly.gdax.notifier.controller;

import com.bconnelly.gdax.notifier.representation.ETH_USD_MATCH;
import com.bconnelly.gdax.notifier.service.EthService;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Bryan on 7/20/2017.
 */

@RestController
public class EthController {

    EthService service = new EthService();

    @RequestMapping("/getUpdates")
    public List<ETH_USD_MATCH> getUpdates(@RequestHeader String lastUpdate){
        System.out.println("pinged endpoint");

        return service.fetchMatchesSinceSequence(Integer.valueOf(lastUpdate));
    }
}
