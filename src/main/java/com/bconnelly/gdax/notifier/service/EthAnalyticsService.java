package com.bconnelly.gdax.notifier.service;

import com.datastax.spark.connector.japi.CassandraJavaUtil;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.List;

import static com.datastax.spark.connector.japi.CassandraJavaUtil.mapColumnTo;

/**
 * Created by Bryan on 8/9/2017.
 */
public class EthAnalyticsService {

    private SparkConf conf;
    private SparkContext context;


    public EthAnalyticsService() {
        this.conf = new SparkConf(true)
                .setAppName("GDAX Analyzer")
                .setMaster("local")
                .set("spark.cassandra.connection.host", "localhost");
        this.context = new SparkContext(conf);
    }

    public long countMatches(){

        JavaRDD<String> cassandraRdd = CassandraJavaUtil.javaFunctions(context)
                .cassandraTable("GDAX", "ETH_USD_MATCHES", mapColumnTo(String.class))
                .select("sequence");

        return cassandraRdd.count();
    }
}
