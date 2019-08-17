package com.massita.coreapi.game;

import java.util.Map;

public interface EventAction {

    String getEventActionDescription();

    String getEventResultDescription();

    Map<Resource, Integer> getEventPrice();

    String getNextStageName();
}
