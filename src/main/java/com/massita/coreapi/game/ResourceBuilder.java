package com.massita.coreapi.game;

import java.util.HashMap;
import java.util.Map;

import static com.massita.coreapi.game.Resource.*;

public class ResourceBuilder {

    Map<Resource, Integer> resources = new HashMap<>();

    public ResourceBuilder full() {
        resources = new HashMap<>();
        resources.put(MONEY, 100);
        resources.put(TEAM, 100);
        resources.put(TIME, 100);
        resources.put(NERVES, 100);
        resources.put(LOYALITY, 100);
        return this;
    }

    public ResourceBuilder empty() {
        resources = new HashMap<>();
        resources.put(MONEY, 0);
        resources.put(TEAM, 0);
        resources.put(TIME, 0);
        resources.put(NERVES, 0);
        resources.put(LOYALITY, 0);
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
