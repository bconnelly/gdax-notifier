package com.bconnelly.gdax.notifier.representation;

/**
 * Created by Bryan on 7/18/2017.
 */
public class RestResponseRepresentation {
    private String type;
    private String trade_id;
    private String time;
    private String product_id;
    private String sequence;
    private String maker_order_id;
    private String taker_order_id;
    private String size;
    private String price;
    private String side;

    public String getType() { return type; }

    public void setType(String type) {
        this.type = type;
    }

    public String getTrade_id() { return trade_id; }

    public void setTrade_id(String trade_id) { this.trade_id = trade_id; }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getMaker_order_id() {
        return maker_order_id;
    }

    public void setMaker_order_id(String maker_order_id) {
        this.maker_order_id = maker_order_id;
    }

    public String getTaker_order_id() { return taker_order_id; }

    public void setTaker_order_id(String taker_order_id) { this.taker_order_id = taker_order_id; }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    @Override
    public String toString(){
        return  "type: " + getType() + "\n" +
                "time: " + getTime() + "\n" +
                "product_id: " + getProduct_id() + "\n" +
                "sequence: " + getSequence() + "\n" +
                "maker_order_id: " + getMaker_order_id() + "\n" +
                "size: " + getSize() + "\n" +
                "funds: " + getPrice() + "\n" +
                "side: " + getSide();
    }
}
