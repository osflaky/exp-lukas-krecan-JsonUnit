/**
 * Copyright 2009-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.javacrumbs.jsonunit.test.base;

import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;

// AssertJ and Hamcrest put System.lineSeparator() in their messages, so compare messages with normalized newlines
public class NewlineAgnosticAssert extends AbstractThrowableAssert<NewlineAgnosticAssert, Throwable> {

    private NewlineAgnosticAssert(Throwable actual) {
        super(actual, NewlineAgnosticAssert.class);
    }

    public static NewlineAgnosticAssert assertThatThrownBy(ThrowingCallable shouldRaiseThrowable) {
        return new NewlineAgnosticAssert(Assertions.catchThrowable(shouldRaiseThrowable)).hasBeenThrown();
    }

    @Override
    public NewlineAgnosticAssert hasMessage(String message) {
        Assertions.assertThat(actual.getMessage()).isEqualToNormalizingNewlines(message);
        return myself;
    }

    @Override
    public NewlineAgnosticAssert hasMessageStartingWith(String description) {
        Assertions.assertThat(String.valueOf(actual.getMessage()).replace("\r\n", "\n"))
                .startsWith(description);
        return myself;
    }
}
