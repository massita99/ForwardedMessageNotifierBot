package com.massita.processor;

import com.massita.coreapi.CreateGameEvent;
import com.massita.coreapi.ProcessEventEvent;
import com.massita.coreapi.game.EventAction;
import com.massita.coreapi.game.Resource;
import com.massita.query.process.gamePath.GamePath;
import com.massita.query.process.player.Player;
import com.massita.services.commands.GameCommandService;
import com.massita.services.queries.GameQueryService;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class GameProcessor {

    private final GameCommandService gameCommandService;

    private final GameQueryService queryService;


    public GameProcessor(GameCommandService gameCommandService, GameQueryService queryService) {
        this.gameCommandService = gameCommandService;
        this.queryService = queryService;
    }

    @EventHandler
    public void on(CreateGameEvent event) throws ExecutionException, InterruptedException {
        GamePath path = queryService.getGamePath("MAIN");

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
        GamePath path = queryService.getGamePath("MAIN");
        Player player = queryService.getPlayer(event.getChatId());

        EventAction selectedAction = path.getPathActions().get(event.getAnswerText());

        if (selectedAction.getEventPrice() != null) {
            handleResourceChange(event, player, selectedAction);
        }

        //Идем на следюущий евент
        gameCommandService.startEvent(event.getChatId(),
                path.getPathEvents().get(selectedAction.getNextStageName()).getEventDescription(),
                path.getPathEvents().get(selectedAction.getNextStageName()).getEventActions().stream().map(EventAction::getEventActionDescription).collect(Collectors.toList()),
                path.getPathEvents().get(selectedAction.getNextStageName()).getPhoto()
                );
    }

    private void handleResourceChange(ProcessEventEvent event, Player player, EventAction selectedAction) {
        Map<Resource, Integer> newPlayerResources = player.getResources();
        selectedAction.getEventPrice().forEach((key, value) -> newPlayerResources.put(key, newPlayerResources.getOrDefault(key, 0) + value));

        player.setResources(newPlayerResources);

        gameCommandService.updateStats(event.getChatId(), newPlayerResources);

        if (newPlayerResources.values().stream().anyMatch(el -> el <= 0)) {
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
    }

}
