package com.bconnelly.gdax.notifier.representation;

/**
 * Created by Bryan on 8/1/2017.
 */
public class RestRequestRepresentation {
    String size;
    String funds;
    String side;
    String product_id = "ETH_USD";

    public RestRequestRepresentation(String size, String funds, String side) {
        this.size = size;
        this.funds = funds;
        this.side = side;
    }
}
