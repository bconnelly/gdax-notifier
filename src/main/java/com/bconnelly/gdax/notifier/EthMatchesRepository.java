package com.bconnelly.gdax.notifier;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

/**
 * Created by Bryan on 7/19/2017.
 */


public interface EthMatchesRepository extends CrudRepository<SocketResponseRepresentation, String>{

    @Query("Select * from GDAX.ETH_USD_MATCHES")
    SocketResponseRepresentation getAllMatches();

}
