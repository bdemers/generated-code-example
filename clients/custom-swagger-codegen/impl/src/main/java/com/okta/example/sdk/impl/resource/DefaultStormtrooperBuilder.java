package com.okta.example.sdk.impl.resource;

import com.okta.example.sdk.client.Client;
import com.okta.example.sdk.resource.Stormtrooper;
import com.okta.example.sdk.resource.trooper.StormtrooperBuilder;
import com.okta.example.sdk.client.Client;
import com.okta.example.sdk.resource.Stormtrooper;
import com.okta.example.sdk.resource.trooper.StormtrooperBuilder;

public class DefaultStormtrooperBuilder implements StormtrooperBuilder {

    private String id;
    private String planetOfOrigin;
    private String species;
    private String type;

    @Override
    public StormtrooperBuilder setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public StormtrooperBuilder setPlanetOfOrigin(String planetOfOrigin) {
        this.planetOfOrigin = planetOfOrigin;
        return this;
    }

    @Override
    public StormtrooperBuilder setSpecies(String species) {
        this.species = species;
        return this;
    }

    @Override
    public StormtrooperBuilder setType(String type) {
        this.type = type;
        return this;
    }

    @Override
    public Stormtrooper buildAndCreate(Client client) {
        Stormtrooper stormtrooper = client.instantiate(Stormtrooper.class)
            .setId(id)
            .setPlanetOfOrigin(planetOfOrigin)
            .setSpecies(species)
            .setType(type);
        return client.createTrooper(stormtrooper);
    }
}
