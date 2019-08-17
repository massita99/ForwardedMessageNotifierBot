package com.massita.query.process.player;

import com.massita.coreapi.CreateGameEvent;
import com.massita.coreapi.PlayerQuery;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class PlayerProjection {

    private final PlayerRepository playerRepository;

    public PlayerProjection(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @EventHandler
    public void on(CreateGameEvent event) {
        playerRepository.save(new Player(event.getChatId()));
    }

    @QueryHandler
    public Player on(PlayerQuery query) {
        return playerRepository.findById(query.getChatId()).get();

    }


}
