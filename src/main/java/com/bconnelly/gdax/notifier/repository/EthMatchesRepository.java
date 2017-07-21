package com.bconnelly.gdax.notifier.repository;

import com.bconnelly.gdax.notifier.representation.SocketResponseRepresentation;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Bryan on 7/19/2017.
 */


public interface EthMatchesRepository extends CrudRepository<SocketResponseRepresentation, String>{

    @Query("SELECT * FROM GDAX.ETH_USD_MATCHES")
    SocketResponseRepresentation getAllMatches();

    @Query("SELECT * FROM ETH_USD_MATCHES WHERE token(sequence) > token({id});")
    SocketResponseRepresentation getMatchesSinceId(int id);

}
