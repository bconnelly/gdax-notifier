package com.bconnelly.gdax.notifier.representation;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

/**
 * Created by Bryan on 7/21/2017.
 */

@Table("ETH_USD_MATCHES")
public class ETH_USD_MATCH {

    @PrimaryKey
    private int sequence;

    private String type;
    private String trade_id;
    private String time;
    private String product_id;
    private String maker_order_id;
    private String taker_order_id;
    private String size;
    private String price;
    private String side;

    public ETH_USD_MATCH(int sequence, String type, String trade_id, String time, String product_id, String maker_order_id, String taker_order_id, String size, String price, String side) {
        this.sequence = sequence;
        this.type = type;
        this.trade_id = trade_id;
        this.time = time;
        this.product_id = product_id;
        this.maker_order_id = maker_order_id;
        this.taker_order_id = taker_order_id;
        this.size = size;
        this.price = price;
        this.side = side;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTrade_id(String trade_id) {
        this.trade_id = trade_id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public void setMaker_order_id(String maker_order_id) {
        this.maker_order_id = maker_order_id;
    }

    public void setTaker_order_id(String taker_order_id) {
        this.taker_order_id = taker_order_id;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setSide(String side) {
        this.side = side;
    }
}
