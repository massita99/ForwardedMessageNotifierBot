package com.massita.coreapi.game;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.List;

@Getter
@Builder
public class BaseStageEvent implements StageEvent {

    private String name;

    private String photo;

    public String getPhoto() {
        return photo == null ? "" : photo;
    }

    private String eventDescription;

    @Singular
    private List<EventAction> eventActions;

}
