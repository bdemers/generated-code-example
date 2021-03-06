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
package com.okta.example.sdk.impl.ds;

import java.util.Map;

/**
 * A DiscriminatorRegistry allows for the resolution of a specific type, based on the data of the object.
 * For example, the Factors API return a collection of {@link com.okta.example.sdk.resource.trooper.factor.Factor Factor} objects,
 * The actual type is dependent on a property of the data map 'factorType'.
 *
 * @since 0.8.0
 */
public interface DiscriminatorRegistry {

    boolean supportedClass(Class clazz);

    <P> Class<P> resolve(Class<P> clazz, Map data);
}
