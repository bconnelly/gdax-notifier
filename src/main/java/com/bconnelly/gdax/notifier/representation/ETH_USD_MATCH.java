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

    private int bucket = 1;

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

    public int getSequence() {
        return sequence;
    }

    public String getType() {
        return type;
    }

    public String getTrade_id() {
        return trade_id;
    }

    public String getTime() {
        return time;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getMaker_order_id() {
        return maker_order_id;
    }

    public String getTaker_order_id() {
        return taker_order_id;
    }

    public String getSize() {
        return size;
    }

    public String getPrice() {
        return price;
    }

    public String getSide() {
        return side;
    }

    public static class Builder{
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

        public Builder(){

        }

        public Builder setSequence(int sequence){
            this.sequence = sequence;
            return this;
        }

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public Builder setTrade_id(String trade_id) {
            this.trade_id = trade_id;
            return this;
        }

        public Builder setTime(String time) {
            this.time = time;
            return this;
        }

        public Builder setProduct_id(String product_id) {
            this.product_id = product_id;
            return this;
        }

        public Builder setMaker_order_id(String maker_order_id) {
            this.maker_order_id = maker_order_id;
            return this;
        }

        public Builder setTaker_order_id(String taker_order_id) {
            this.taker_order_id = taker_order_id;
            return this;
        }

        public Builder setSize(String size) {
            this.size = size;
            return this;
        }

        public Builder setPrice(String price) {
            this.price = price;
            return this;
        }

        public Builder setSide(String side) {
            this.side = side;
            return this;
        }

        public ETH_USD_MATCH Build(){
            return new ETH_USD_MATCH(this.sequence, this.type, this.trade_id, this.time, this.product_id, this.maker_order_id,
                                     this.taker_order_id, this.size, this.price, this.side);
        }
    }

    @Override
    public String toString(){

        return "sequence: " + sequence + "\n" +
               "type: " + type + "\n" +
               "trade_id: " + trade_id + "\n" +
               "time: " + time + "\n" +
               "product_id: " + product_id + "\n" +
               "maker_order_id: " + maker_order_id + "\n" +
               "taker_order_id: " + taker_order_id + "\n" +
               "size: " + size + "\n" +
               "funds: " + price+ "\n" +
               "side: " + side + "\n";
    }
}
