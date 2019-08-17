package com.massita.coreapi.game;

import java.util.List;

public interface StageEvent {

    String getEventDescription();

    List<EventAction> getEventActions();

    String getName();

    String getPhoto();
}
