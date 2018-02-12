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
package com.okta.sdk.tests.it.util
import org.testng.Assert


import static org.hamcrest.MatcherAssert.*
import static org.hamcrest.Matchers.*
import static org.hamcrest.Matchers.hasSize
import static org.hamcrest.Matchers.notNullValue

class Util {

    static def ignoring = { Class<? extends Throwable> catchMe, Closure callMe ->
        try {
            callMe.call()
        } catch(e) {
            if (!e.class.isAssignableFrom(catchMe)) {
                throw e
            }
        }
    }

    static def expect = { Class<? extends Throwable> catchMe, Closure callMe ->
        try {
            callMe.call()
            Assert.fail("Expected ${catchMe.getName()} to be thrown.")
        } catch(e) {
            if (!e.class.isAssignableFrom(catchMe)) {
                throw e
            }
        }
    }
}
