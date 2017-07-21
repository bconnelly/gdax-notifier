package com.bconnelly.gdax.notifier.repository;

import com.bconnelly.gdax.notifier.representation.ETH_USD_MATCH;
import com.bconnelly.gdax.notifier.representation.SocketResponseRepresentation;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Bryan on 7/19/2017.
 */


public class EthMatchesRepository {

    private Cluster cluster;
    private Session session;
    private String keyspace = "GDAX";


    public EthMatchesRepository(){
        try {
            cluster = Cluster.builder().addContactPoints(InetAddress.getByName("127.0.0.1")).build();
            session = cluster.connect(keyspace);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void insert(SocketResponseRepresentation representation){
        CassandraOperations ops = new CassandraTemplate(session);
        ETH_USD_MATCH match = representationToETH_USD_MATCHES(representation);

        ops.insert(match);

        System.out.println("Inserted successfully");
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

}
