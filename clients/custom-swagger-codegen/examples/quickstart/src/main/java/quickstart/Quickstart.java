/*
 * Copyright 2017 Okta
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package quickstart;

import com.okta.example.sdk.client.Client;
import com.okta.example.sdk.client.ClientBuilder;
import com.okta.example.sdk.client.Clients;
import com.okta.example.sdk.resource.ResourceException;
import com.okta.example.sdk.resource.trooper.StormtrooperBuilder;
import com.okta.example.sdk.resource.troopers.Stormtrooper;
import com.okta.example.sdk.resource.troopers.StormtrooperList;

import java.util.UUID;

/**
 * This class demonstrates the code found in the Okta Java Example SDK QuickStart Guide
 *
 * @since 0.5.0
 */
@SuppressWarnings("PMD.UnusedLocalVariable")
public class Quickstart {

    public static void main(String[] args) {

        try {
            // Instantiate a builder for your Client. If needed, settings like Proxy and Caching can be defined here.
            ClientBuilder builder = Clients.builder();

            // No need to define anything else; build the Client instance. The ClientCredential information will be automatically found
            // in pre-defined locations: i.e. ~/.okta/example-okta.yaml
            Client client = builder.build();

            // Create a Stormtrooper
            Stormtrooper stormtrooper = StormtrooperBuilder.instance()
                    .setId("FN-"+ UUID.randomUUID().toString())
                    .setPlanetOfOrigin("Earth")
                    .setSpecies("Human")
                    .setType("Basic")
                    .buildAndCreate(client);

            println("Created Stormtrooper: "+ stormtrooper);

            // get the list of stormtroopers
            StormtrooperList troopers = client.listTroopers();

            // get the first user in the collection
            println("First trooper in collection: " + troopers.stream().findFirst().get());
        }
        catch (ResourceException e) {

            // we can get the user friendly message from the Exception
            println(e.getMessage());

            // and you can get the details too
            e.getCauses().forEach( cause -> println("\t" + cause.getSummary()));
            throw e;
        }
    }

    private static void println(String message) {
        System.out.println(message);
        System.out.flush();
    }
}
