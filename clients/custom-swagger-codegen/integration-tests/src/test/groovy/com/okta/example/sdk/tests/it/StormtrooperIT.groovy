package com.okta.example.sdk.tests.it

import com.okta.example.sdk.client.Client
import com.okta.example.sdk.resource.trooper.StormtrooperBuilder
import com.okta.example.sdk.resource.troopers.Stormtrooper

import static org.hamcrest.Matchers.*
import static org.hamcrest.MatcherAssert.assertThat

class StormtrooperIT implements CrudTestSupport {

    @Override
    String getResourceId(def resource) {
        return resource.getId()
    }

    @Override
    def create(Client client) {

        Stormtrooper stormtrooper = StormtrooperBuilder.instance()
            .setId(UUID.randomUUID().toString())
            .setPlanetOfOrigin("Earth")
            .setSpecies("Human")
            .setType("Basic")
            .buildAndCreate(client)
        registerForCleanup(stormtrooper)
        return stormtrooper
    }

    @Override
    def read(Client client, String id) {
        return client.getTrooper(id)
    }

    @Override
    Iterator getResourceCollectionIterator(Client client) {
        return client.listTroopers().iterator()
    }

    @Override
    void update(Client client, def resource) {
        resource.setSpecies("More Human")
        resource.update()
    }

    @Override
    void assertUpdate(Client client, def resource) {
        assertThat resource.getSpecies(), equalTo("More Human")
    }
}
