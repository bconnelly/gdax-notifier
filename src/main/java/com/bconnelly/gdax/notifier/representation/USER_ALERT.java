package com.bconnelly.gdax.notifier.representation;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

/**
 * Created by Bryan on 7/26/2017.
 */
@Table("user_alerts")
public class USER_ALERT {

    @PrimaryKey
    private String bucket = "1";

    private String user;
    private String alert_value;
    private boolean active;
    private boolean alert_if_above;

    public USER_ALERT(String bucket, String user, String alert_value, boolean active, boolean alert_if_above){
        this.bucket = bucket;
        this.user = user;
        this.alert_value = alert_value;
        this.active = active;
        this.alert_if_above = alert_if_above;
    }

    public String getBucket() { return bucket; }

    public String getUser() { return user; }

    public String getAlert_value() { return alert_value; }

    public boolean isActive() { return active; }

    public boolean isAlert_if_above() { return alert_if_above; }

    public static class Builder{

        private String bucket = "1";
        private String user;
        private String alert_value;
        private boolean active;
        private boolean alert_if_above;

        public Builder() {

        }

        public Builder setBucket(String bucket) {
            this.bucket = bucket;
            return this;
        }

        public Builder setUser(String user) {
            this.user = user;
            return this;
        }

        public Builder setAlert_value(String alert_value) {
            this.alert_value = alert_value;
            return this;
        }

        public Builder setActive(boolean active) {
            this.active = active;
            return this;
        }

        public Builder setAlert_if_above(boolean alert_if_above) {
            this.alert_if_above = alert_if_above;
            return this;
        }

        public USER_ALERT build(){
            return new USER_ALERT(this.bucket, this.user, this.alert_value, this.active, this.alert_if_above);
        }
    }

    @Override
    public String toString(){
        return "bucket: " + this.bucket + "\n" +
               "user: " + this.user + "\n" +
               "alert_value: " + this.alert_value+ "\n" +
               "active: " + this.active + "\n" +
               "alert_if_above: " + this.alert_if_above+ "\n";
    }

}
