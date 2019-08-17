package com.massita.services.queries;

import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

@Service
public class GameQueryService {

    private final QueryGateway gateway;

    public GameQueryService(QueryGateway gateway) {
        this.gateway = gateway;
    }

}
