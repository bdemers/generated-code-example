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
package com.okta.example.sdk.impl.ds;

import com.okta.example.sdk.lang.Assert;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @since 0.5.0
 */
public class DefaultCacheMapCreator implements CacheMapCreator {

    private final Map<String, ?> data;

    public DefaultCacheMapCreator(Map<String, ?> data) {
        Assert.notEmpty(data, "Data cannot be null or empty.");
        this.data = data;
    }

    /**
     * Creates an empty cache map with the initial size being the size of the
     * data map provided in the constructor.
     *
     * @return an empty cache map with the initial size being the size of the
     * provided data.
     */
    @Override
    public Map<String, Object> create() {
        return new LinkedHashMap<>(data.size());
    }

    public Map<String, ?> getData() {
        return data;
    }
}
