package com.massita.processor;

import com.massita.coreapi.CreateGameEvent;
import com.massita.coreapi.PathQuery;
import com.massita.coreapi.PlayerQuery;
import com.massita.coreapi.ProcessEventEvent;
import com.massita.coreapi.game.EventAction;
import com.massita.coreapi.game.MainScenario;
import com.massita.coreapi.game.Resource;
import com.massita.query.process.gamePath.GamePath;
import com.massita.query.process.gamePath.GamePathRepository;
import com.massita.query.process.player.Player;
import com.massita.query.process.player.PlayerRepository;
import com.massita.services.commands.GameCommandService;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static org.axonframework.messaging.responsetypes.ResponseTypes.instanceOf;

@Service
public class GameProcessor {

    private final QueryGateway gateway;

    private final GameCommandService gameCommandService;

    private final PlayerRepository playerRepository;

    private final GamePathRepository gamePathRepository;

    public GameProcessor(QueryGateway gateway, GameCommandService gameCommandService, PlayerRepository playerRepository, GamePathRepository gamePathRepository) {
        this.gateway = gateway;
        this.gameCommandService = gameCommandService;
        this.playerRepository = playerRepository;
        this.gamePathRepository = gamePathRepository;
    }

    @EventHandler
    public void on(CreateGameEvent event) throws ExecutionException, InterruptedException {
        GamePath path = new GamePath(event.getChatId(), new MainScenario());
        gamePathRepository.save(path);
        gameCommandService.startEvent(event.getChatId(),
                path.getPathEvents().get("START").getEventDescription(),
                path.getPathEvents().get("START").getEventActions().stream()
                        .map(EventAction::getEventActionDescription)
                        .collect(Collectors.toList()
                        ),
                path.getPathEvents().get("START").getPhoto()

                );
    }

    @EventHandler
    public void on(ProcessEventEvent event) throws ExecutionException, InterruptedException {
        GamePath path = gateway.query(new PathQuery(event.getChatId()), instanceOf(GamePath.class)).get();
        Player player = gateway.query(new PlayerQuery(event.getChatId()), instanceOf(Player.class)).get();

        EventAction selectedAction = path.getPathActions().get(event.getAnswerText());

        Map<Resource, Integer> newPlayerResources = player.getResources();
        selectedAction.getEventPrice().forEach((key, value) -> newPlayerResources.put(key, newPlayerResources.getOrDefault(key, 0) + value));

        player.setResources(newPlayerResources);

        playerRepository.save(player);

        if (player.getResources().values().stream().anyMatch(el -> el <= 0)) {
            //finish
        }

        if (!selectedAction.getEventPrice().isEmpty()) {
            gameCommandService.returnEventResult(
                    event.getChatId(),
                    selectedAction.getEventResultDescription(),
                    player.getResources().entrySet().stream()
                            .map(el -> el.getKey().toString() + " - " + el.getValue())
                            .collect(Collectors.joining(", "))
            );
        }

        //Идем на следюущий евент
        gameCommandService.startEvent(event.getChatId(),
                path.getPathEvents().get(selectedAction.getNextStageName()).getEventDescription(),
                path.getPathEvents().get(selectedAction.getNextStageName()).getEventActions().stream().map(EventAction::getEventActionDescription).collect(Collectors.toList()),
                path.getPathEvents().get(selectedAction.getNextStageName()).getPhoto()
                );
    }

}
