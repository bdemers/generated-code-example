/*
 * Copyright 2018 Okta, Inc.
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
package com.okta.sdk.impl.resource;

import com.okta.sdk.resource.Resource;
import com.okta.sdk.resource.Stormtrooper;
import com.okta.sdk.resource.TieCraft;

import java.util.Map;

/**
 * The default HREF resolver return a HAL self link if available ({@code _links.self.href}). If the {@code self} link is
 * missing, it will be statically built for known objects (or null will be returned).
 * <p>
 * Known Objects:
 * <ul>
 *     <li>Stormtrooper</li>
 *     <li>TieCraft</li>
 * </ul>
 *
 * @since 1.0
 */
public class OktaResourceHrefResolver implements ResourceHrefResolver {

    private final ResourceHrefResolver halResourceHrefResolver = new HalResourceHrefResolver();

    @Override
    public <R extends Resource> String resolveHref(Map<String, ?> properties, Class<R> clazz) {
        String href = halResourceHrefResolver.resolveHref(properties, clazz);

        return href != null
                ? href
                : fixSelfHref(properties, clazz);
    }

    private <R extends Resource> String fixSelfHref(Map<String, ?> properties, Class<R> clazz) {
        if (Stormtrooper.class.isAssignableFrom(clazz)) {
            return "/trooper/" + properties.get("id");
        }

        if (TieCraft.class.isAssignableFrom(clazz)) {
            return "/tie/" + properties.get("serial");
        }

        return null;
    }
}