/*
 * Copyright 2014 Stormpath, Inc.
 * Modifications Copyright 2018 Okta, Inc.
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
package com.okta.example.sdk.impl.io

import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import static org.testng.Assert.assertTrue

/**
 * @since 0.5.0
 */
class DefaultResourceFactoryTest {

    def resourceFactory

    @BeforeMethod
    void setup() {
        resourceFactory = new DefaultResourceFactory()
    }

    @Test
    void testCreateResourceClasspath() {
        def resource = resourceFactory.createResource("classpath:com/okta/example/sdk/impl/io/DefaultResourceFactory.class")

        assertTrue resource instanceof ClasspathResource
    }

    @Test
    void testCreateResourceUrl() {
        def resource = resourceFactory.createResource("https://www.google.com")

        assertTrue resource instanceof UrlResource
    }
}
