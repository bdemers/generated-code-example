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
package com.okta.sdk.resource.trooper;

import com.okta.sdk.client.Client;
import com.okta.sdk.lang.Classes;
import com.okta.sdk.resource.Stormtrooper;

public interface StormtrooperBuilder {

    static StormtrooperBuilder instance() {
        return Classes.newInstance("com.okta.sdk.impl.resource.DefaultStormtrooperBuilder");
    }

    StormtrooperBuilder setId(String id);

    StormtrooperBuilder setPlanetOfOrigin(String planetOfOrigin);

    StormtrooperBuilder setSpecies(String species);

    StormtrooperBuilder setType(String type);

    Stormtrooper buildAndCreate(Client client);
}
