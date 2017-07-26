package com.bconnelly.gdax.notifier.controller;

import com.bconnelly.gdax.notifier.representation.ETH_USD_MATCH;
import com.bconnelly.gdax.notifier.service.EthService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Bryan on 7/20/2017.
 */
@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("/gdax")
public class EthController {

    EthService service = new EthService();

    @RequestMapping(value = "/getUpdates/{lastUpdate}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ETH_USD_MATCH>> getUpdates(@PathVariable String lastUpdate){
        System.out.println("pinged endpoint");

        try{
            return ResponseEntity.status(HttpStatus.OK).body(
                    service.fetchMatchesSinceSequence(Integer.valueOf(lastUpdate))
            );
        } catch(NumberFormatException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @RequestMapping(value = "/getLastN/{nMatches}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ETH_USD_MATCH>> getLastN(@PathVariable String nMatches){
        System.out.println("pinged getLastN");

        try{
            List<ETH_USD_MATCH> matches = service.fetchLastNMatches(Integer.valueOf(nMatches));
            return ResponseEntity.status(HttpStatus.OK).body(matches);
        }catch(NumberFormatException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
//        return "{\"key\":\"value\"}";
    }
}
