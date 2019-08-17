package com.massita.query.process.gamePath;

import com.massita.coreapi.CreateGameEvent;
import com.massita.coreapi.PathQuery;
import com.massita.coreapi.game.MainScenario;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class GamePathProjection {

    private final GamePathRepository gamePathRepository;

    public GamePathProjection(GamePathRepository gamePathRepository) {
        this.gamePathRepository = gamePathRepository;
    }

    @EventHandler
    public void on(CreateGameEvent event) {
        gamePathRepository.save(
                new GamePath(
                        event.getChatId(),
                        new MainScenario()
                )
        );
    }

    @QueryHandler
    public GamePath on(PathQuery query) {
        return gamePathRepository.findById(query.getChatId()).get();

    }





}
