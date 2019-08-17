package com.massita.query.process.gamePath;

import com.massita.coreapi.game.EventAction;
import com.massita.coreapi.game.Scenario;
import com.massita.coreapi.game.StageEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Document
@NoArgsConstructor
@Getter
public class GamePath {

    @Id
    private String id;

    private Map<String, StageEvent> pathEvents;

    private Map<String, EventAction> pathActions;

    public GamePath(String id, Scenario scenario) {
        this.id = id;
        this.pathEvents = new HashMap<>();
        this.pathActions = new HashMap<>();

        List<StageEvent> events = scenario.getStages();
        events.forEach(el -> this.pathEvents.put(el.getName(), el));

        List<EventAction> eventActions = scenario.getStages().stream()
                .flatMap(event -> event.getEventActions().stream())
                .collect(Collectors.toList());

        eventActions.forEach(el -> this.pathActions.put(el.getEventActionDescription(), el));
    }




}
