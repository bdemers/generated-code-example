/*
 * Copyright 2018 Okta, Inc
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
package com.okta.example.sdk.impl.resource

import com.okta.example.sdk.resource.tie.TieCraft
import com.okta.example.sdk.resource.troopers.Stormtrooper
import org.testng.annotations.Test

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.equalTo
import static org.hamcrest.Matchers.nullValue

class OktaResourceHrefResolverTest {

    final static String BASE_URL = "https://okta.example.com"

    @Test
    void missingHrefReturnsNullTest() {

        ResourceHrefResolver resolver = new OktaResourceHrefResolver()

        Map<String, ?> nullHrefProps = [
            _links: [
                self: [
                    href: null
            ]]]

        Map<String, ?> missingHrefProps = [
            _links: [
                self: [
                    foo: "bar"
            ]]]

        assertThat resolver.resolveHref(Collections.emptyMap(), TestResource), nullValue()
        assertThat resolver.resolveHref(nullHrefProps, TestResource), nullValue()
        assertThat resolver.resolveHref(missingHrefProps, TestResource), nullValue()
    }

    @Test
    void resourceWithSelfLinkTest() {

        String selfHref = "https://okta-test.example.com/my/href"
        Map<String, ?> props = [
            _links: [
                self: [
                    href: selfHref
            ]]]

        ResourceHrefResolver resolver = new OktaResourceHrefResolver()
        assertThat resolver.resolveHref(props, TestResource), equalTo(selfHref)
        assertThat resolver.resolveHref(props, null), equalTo(selfHref) // clazz doesn't matter when self link is set
    }

    @Test
    void stormtrooperHrefTest() {

        String resourceId = "sp-id"
        Map<String, ?> props = [
            id: resourceId
        ]

        ResourceHrefResolver resolver = new OktaResourceHrefResolver()
        assertThat resolver.resolveHref(props, Stormtrooper), equalTo("/trooper/${resourceId}".toString())
        assertThat resolver.resolveHref(props, TestResource), nullValue() // wrong class, so this should return null
    }

    @Test
    void tieCraftHrefTest() {

        String resourceId = "tie-id"
        Map<String, ?> props = [
            serial: resourceId
        ]

        ResourceHrefResolver resolver = new OktaResourceHrefResolver()
        assertThat resolver.resolveHref(props, TieCraft), equalTo("/tie/${resourceId}".toString())
        assertThat resolver.resolveHref(props, TestResource), nullValue() // wrong class, so this should return null
    }
}