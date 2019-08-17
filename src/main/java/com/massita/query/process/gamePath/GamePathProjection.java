package com.massita.query.process.gamePath;

import com.massita.coreapi.PathQuery;
import com.massita.coreapi.game.MainScenario;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class GamePathProjection implements InitializingBean {

    private final GamePathRepository gamePathRepository;

    public GamePathProjection(GamePathRepository gamePathRepository) {
        this.gamePathRepository = gamePathRepository;
    }

    @QueryHandler
    public GamePath on(PathQuery query) {
        return gamePathRepository.findById(query.getChatId()).get();

    }


    @Override
    public void afterPropertiesSet() throws Exception {
        if (!gamePathRepository.findById("MAIN").isPresent()) {
            gamePathRepository.save(new GamePath("MAIN", new MainScenario()));
        }
    }
}
