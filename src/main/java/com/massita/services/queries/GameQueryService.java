package com.massita.services.queries;

import com.massita.coreapi.PathQuery;
import com.massita.coreapi.PlayerQuery;
import com.massita.query.process.gamePath.GamePath;
import com.massita.query.process.player.Player;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

import static org.axonframework.messaging.responsetypes.ResponseTypes.instanceOf;

@Service
public class GameQueryService {

    private final QueryGateway gateway;

    public GameQueryService(QueryGateway gateway) {
        this.gateway = gateway;
    }

    public GamePath getGamePath(String id) throws ExecutionException, InterruptedException {
        PathQuery query = new PathQuery(id);

        return  gateway.query(query, instanceOf(GamePath.class)).get();
    }

    public Player getPlayer(String id) throws ExecutionException, InterruptedException {
        PlayerQuery query = new PlayerQuery(id);

        return gateway.query(query, instanceOf(Player.class)).get();
    }

}
