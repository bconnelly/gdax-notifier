package com.bconnelly.gdax.notifier.repository;

import com.bconnelly.gdax.notifier.enums.EthStatus;
import com.bconnelly.gdax.notifier.representation.ETH_USD_MATCH;
import com.bconnelly.gdax.notifier.representation.SocketResponseRepresentation;
import com.bconnelly.gdax.notifier.representation.USER_ALERT;
import com.datastax.driver.core.*;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bryan on 7/19/2017.
 */


public class EthMatchesRepository {

    private Cluster cluster;
    private Session session;
    private CassandraOperations ops;
    private String keyspace = "GDAX";
    private String table_matches = "ETH_USD_MATCHES";
    private String table_alerts = "USER_ALERTS";


    public EthMatchesRepository(){
        try {
            cluster = Cluster.builder().addContactPoints(InetAddress.getByName("127.0.0.1")).build();
            session = cluster.connect(keyspace);
            ops = new CassandraTemplate(session);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void insert(SocketResponseRepresentation representation){
        ETH_USD_MATCH match = representationToETH_USD_MATCHES(representation);

        ops.insert(match);

//        System.out.println("Inserted successfully");
    }

    public List<ETH_USD_MATCH> getSinceSequenceId(int sequenceId){

        String query = "SELECT * FROM " + table_matches + " WHERE bucket = 1 AND sequence > " + sequenceId;

        ResultSet result = session.execute(query);

        return resultSetToListMatches(result);
    }

    public List<ETH_USD_MATCH> getLastN(String nMatches){
        String query = "SELECT * FROM " + table_matches + " WHERE bucket = 1 ORDER BY sequence desc LIMIT " + nMatches;

        ResultSet result = session.execute(query);

        return resultSetToListMatches(result);

    }

    public EthStatus setNewAlert(String user, int value, boolean alertIfAbove){
        USER_ALERT newAlert = new USER_ALERT.Builder()
                .setAlert_if_above(alertIfAbove)
                .setUser(user)
                .setAlert_value(String.valueOf(value))
                .setActive(true)
                .build();

        ops.insert(newAlert);

        return EthStatus.SUCCESS;
    }

    public List<USER_ALERT> getAlerts(String user){
        String query = "SELECT * FROM " + table_alerts + " WHERE bucket = '1' AND user = '" + user + "'";

        ResultSet result = session.execute(query);

        return resultSetToListAlerts(result);
    }

    private ETH_USD_MATCH representationToETH_USD_MATCHES(SocketResponseRepresentation representation){
        return new ETH_USD_MATCH(Integer.valueOf(representation.getSequence()),
                representation.getType(),
                representation.getTrade_id(),
                representation.getTime(),
                representation.getProduct_id(),
                representation.getMaker_order_id(),
                representation.getTaker_order_id(),
                representation.getSize(),
                representation.getPrice(),
                representation.getSide());
    }

    private List<ETH_USD_MATCH> resultSetToListMatches(ResultSet result) {

        List<ETH_USD_MATCH> matches = new ArrayList<>();

        while (!result.isExhausted()) {
            Row row = result.one();
            ETH_USD_MATCH match = new ETH_USD_MATCH.Builder()
                    .setMaker_order_id(row.get("maker_order_id", String.class))
                    .setPrice(row.get("price", String.class))
                    .setProduct_id(row.get("product_id", String.class))
                    .setSequence(row.get("sequence", int.class))
                    .setSide(row.get("side", String.class))
                    .setSize(row.get("size", String.class))
                    .setTaker_order_id(row.get("taker_order_id", String.class))
                    .setTime(row.get("time", String.class))
                    .setTrade_id(row.get("trade_id", String.class))
                    .setType(row.get("type", String.class))
                    .Build();
            matches.add(match);
        }

        return matches;
    }

    private List<USER_ALERT> resultSetToListAlerts(ResultSet result) {
        List<USER_ALERT> alerts = new ArrayList<>();

        while(!result.isExhausted()){
            Row row = result.one();
            USER_ALERT alert = new USER_ALERT.Builder()
                    .setUser(row.get("user", String.class))
                    .setAlert_if_above(row.get("alert_if_above", boolean.class))
                    .setAlert_value(row.get("alert_value", String.class))
                    .setActive(row.get("active", boolean.class))
                    .build();

            alerts.add(alert);
        }

        return alerts;
    }

}
