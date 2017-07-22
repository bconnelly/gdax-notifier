package com.bconnelly.gdax.notifier.repository;

import com.bconnelly.gdax.notifier.representation.ETH_USD_MATCH;
import com.bconnelly.gdax.notifier.representation.SocketResponseRepresentation;
import com.datastax.driver.core.*;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
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
    private String table = "ETH_USD_MATCHES";


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

        System.out.println("Inserted successfully");
    }

    public List<ETH_USD_MATCH> getSinceSequenceId(int sequenceId){

        String query = "SELECT * FROM " + table + " WHERE token(sequence) > " + sequenceId;

        ResultSet result = session.execute(query);
        List<ETH_USD_MATCH> matches = new ArrayList<>();

        while(!result.isExhausted()){
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

        System.out.println(result.all());
//        Select select = QueryBuilder.select().from(table);
//        Select.Where where = select.where(QueryBuilder.gt("sequence", sequenceId));


//        System.out.println("PAYLOAD: " + where.getOutgoingPayload());
//        System.out.println("PAYLOAD: " + where.getNamedValues(ProtocolVersion.NEWEST_SUPPORTED, CodecRegistry.DEFAULT_INSTANCE));
//        System.out.println("QUERY STRING: " + where.getQueryString());
        return null;

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
