package com.massita.coreapi.game;

import java.util.HashMap;
import java.util.Map;

import static com.massita.coreapi.game.Resource.*;

public class ResourceBuilder {

    Map<Resource, Integer> resources = new HashMap<>();

    public ResourceBuilder full() {
        resources = Map.of(
                MONEY, 100,
                TEAM, 100,
                TIME, 100,
                NERVES, 100,
                LOYALITY, 100
        );
        return this;
    }

    public ResourceBuilder empty() {
        resources = Map.of(
                MONEY, 0,
                TEAM, 0,
                TIME, 0,
                NERVES, 0,
                LOYALITY, 0
        );
        return this;
    }

    public ResourceBuilder plus(Resource resource, Integer count) {
        resources.put(resource, resources.getOrDefault(resource, 0) + count);
        return this;
    }

    public ResourceBuilder minus(Resource resource, Integer count) {
        resources.put(resource, resources.getOrDefault(resource, 0) - count);
        return this;
    }

    public Map<Resource, Integer> build() {
        return resources;
    }
}
