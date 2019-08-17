package com.massita.coreapi.game;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class BaseEventAction implements EventAction {

    private String nextStageName;

    private String eventActionDescription;

    private String eventResultDescription;

    private Map<Resource, Integer> eventPrice;

}
