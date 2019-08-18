package com.massita.query.process.player;

import com.massita.coreapi.CreateGameEvent;
import com.massita.coreapi.PlayerQuery;
import com.massita.coreapi.ResetStatsEvent;
import com.massita.coreapi.UpdateStatsEvent;
import com.massita.coreapi.game.ResourceBuilder;
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

    @EventHandler
    public void on(UpdateStatsEvent event) {
        Player player = playerRepository.findById(event.getChatId()).get();
        player.setResources(event.getStats());
        playerRepository.save(player);
    }

    @EventHandler
    public void on(ResetStatsEvent event) {
        Player player = playerRepository.findById(event.getChatId()).get();
        player.setResources(new ResourceBuilder().empty().build());
        playerRepository.save(player);
    }

    @QueryHandler
    public Player on(PlayerQuery query) {
        return playerRepository.findById(query.getChatId()).get();

    }


}
