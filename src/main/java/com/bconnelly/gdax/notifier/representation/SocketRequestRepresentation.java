package com.bconnelly.gdax.notifier.representation;

/**
 * Created by Bryan on 7/30/2017.
 */
public class SocketRequestRepresentation {

    private String client_oid;
    private String type;
    private String side;
    private String product_id = "ETH-USD";
    private String stp;

    private String price;
    private String size;
    private String time_in_force;
    private String cancel_after;
    private String post_only;

    private String funds;

    private String overdraft_enabled;
    private String funding_amount;

    public SocketRequestRepresentation(String client_oid, String type, String side, String stp, String price, String size, String time_in_force, String cancel_after, String post_only, String funds, String overdraft_enabled, String funding_amount) {
        this.client_oid = client_oid;
        this.type = type;
        this.side = side;
        this.stp = stp;
        this.price = price;
        this.size = size;
        this.time_in_force = time_in_force;
        this.cancel_after = cancel_after;
        this.post_only = post_only;
        this.funds = funds;
        this.overdraft_enabled = overdraft_enabled;
        this.funding_amount = funding_amount;
    }

    public static class Builder{

        private String client_oid;
        private String type;
        private String side;
        private String stp;

        private String price;
        private String size;
        private String time_in_force;
        private String cancel_after;
        private String post_only;

        private String funds;

        private String overdraft_enabled;
        private String funding_amount;

        public Builder setClient_oid(String client_oid) {
            this.client_oid = client_oid;
            return this;
        }

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public Builder setSide(String side) {
            this.side = side;
            return this;
        }

        public Builder setStp(String stp) {
            this.stp = stp;
            return this;
        }

        public Builder setPrice(String price) {
            this.price = price;
            return this;
        }

        public Builder setSize(String size) {
            this.size = size;
            return this;
        }

        public Builder setTime_in_force(String time_in_force) {
            this.time_in_force = time_in_force;
            return this;
        }

        public Builder setCancel_after(String cancel_after) {
            this.cancel_after = cancel_after;
            return this;
        }

        public Builder setPost_only(String post_only) {
            this.post_only = post_only;
            return this;
        }

        public Builder setFunds(String funds) {
            this.funds = funds;
            return this;
        }

        public Builder setOverdraft_enabled(String overdraft_enabled) {
            this.overdraft_enabled = overdraft_enabled;
            return this;
        }

        public Builder setFunding_amount(String funding_amount) {
            this.funding_amount = funding_amount;
            return this;
        }

        public SocketRequestRepresentation Build(){
            return new SocketRequestRepresentation(this.client_oid, this.type, this.side, this.stp, this.price,
                                                   this.size, this.time_in_force, this.cancel_after, this.post_only,
                                                   this.funds, this.overdraft_enabled, this.funding_amount);
        }
    }

    public String getClient_oid() {
        return client_oid;
    }

    public String getType() {
        return type;
    }

    public String getSide() {
        return side;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getStp() {
        return stp;
    }

    public String getPrice() {
        return price;
    }

    public String getSize() {
        return size;
    }

    public String getTime_in_force() {
        return time_in_force;
    }

    public String getCancel_after() {
        return cancel_after;
    }

    public String getPost_only() {
        return post_only;
    }

    public String getFunds() {
        return funds;
    }

    public String getOverdraft_enabled() {
        return overdraft_enabled;
    }

    public String getFunding_amount() {
        return funding_amount;
    }
}
