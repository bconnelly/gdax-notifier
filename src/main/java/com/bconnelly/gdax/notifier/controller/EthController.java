package com.bconnelly.gdax.notifier.controller;

import com.bconnelly.gdax.notifier.representation.ETH_USD_MATCH;
import com.bconnelly.gdax.notifier.service.EthService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Bryan on 7/20/2017.
 */
@CrossOrigin(origins = "http://localhost:63343")
@RestController
public class EthController {

    EthService service = new EthService();

    @RequestMapping(value = "/getUpdates/{lastUpdate}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ETH_USD_MATCH> getUpdates(@PathVariable String lastUpdate){
        System.out.println("pinged endpoint");

        return service.fetchMatchesSinceSequence(Integer.valueOf(lastUpdate));
    }

    @RequestMapping(value = "/getLastN/{nMatches}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ETH_USD_MATCH> getLastN(@PathVariable String nMatches){
        System.out.println("pinged getLastN");

        List<ETH_USD_MATCH> matches = service.fetchLastNMatches(nMatches);
        System.out.println("RETURNING: \n" + matches);
        return matches;
//        return "{\"key\":\"value\"}";
    }
}
