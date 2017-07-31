package com.bconnelly.gdax.notifier.controller;

import com.bconnelly.gdax.notifier.enums.EthStatus;
import com.bconnelly.gdax.notifier.representation.ETH_USD_MATCH;
import com.bconnelly.gdax.notifier.representation.USER_ALERT;
import com.bconnelly.gdax.notifier.service.EthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by Bryan on 7/20/2017.
 */
@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("/gdax")
public class EthController {

    EthService service = new EthService();

    public EthController() throws URISyntaxException {
    }

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
    }

    @RequestMapping(value = "/setNewAlert", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> setNewAlert(@RequestHeader String user, @RequestHeader String value, @RequestHeader boolean above){
        System.out.println("Recording new alert");

        if(EthStatus.SUCCESS == service.setNewAlert(user, Integer.valueOf(value), above)){
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @RequestMapping(value = "/checkAlerts/{user}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAlerts(@PathVariable String user){
        System.out.println("Checking alerts for " + user);

        try{
            List<USER_ALERT> alerts = service.getAlerts(user);
            return ResponseEntity.status(HttpStatus.OK).body(alerts);
        } catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @RequestMapping(value = "/marketOrder", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> marketOrder(@RequestHeader(required = false) String size,
                                         @RequestHeader(required = false) String funds,
                                         @RequestHeader String buyOrSell){

        System.out.println("Executing market order");

//      sanity check fields
        if(buyOrSell.compareTo("buy") != 0 && buyOrSell.compareTo("sell") != 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{message: \"Invalid buyOrSell field: \"" + buyOrSell + "}");
        }

        //call service
        if(size == null && funds != null){
            service.marketOrder("", funds, buyOrSell);
        } else if(size != null && funds == null) {
            service.marketOrder(size, "", buyOrSell);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{message: \"At least one of 'size' and 'funds' must be present.\"}");
        }


        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
