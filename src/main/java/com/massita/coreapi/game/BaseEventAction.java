package com.massita.coreapi.game;

import lombok.Builder;
import lombok.Getter;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Map;

@Getter
@Builder
public class BaseEventAction implements EventAction {

    private String nextStageName;

    private boolean reset = false;

    private String eventActionDescription;

    public String getEventResultDescription() {
        return eventResultDescription == null ? "" : eventResultDescription;
    }

    @Override
    public String getEventHash() {
        return DigestUtils.md5Hex(eventActionDescription);
    }

    private String eventResultDescription;

    private Map<Resource, Integer> eventPrice;

}
