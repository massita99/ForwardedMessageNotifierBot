package com.massita.coreapi.game;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class BaseScenario implements Scenario {

    @Getter
    @Setter
    private List<StageEvent> stages;

}
