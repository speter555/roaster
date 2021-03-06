/*-
 * #%L
 * Coffee
 * %%
 * Copyright (C) 2020 i-Cell Mobilsoft Zrt.
 * %%
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
 * #L%
 */
package hu.icellmobilsoft.roaster.tm4j.common.client;

import hu.icellmobilsoft.roaster.api.TestException;

import javax.enterprise.inject.Vetoed;

/**
 * Exception class indicating rest call errors.
 *
 * @author martin.nagy
 * @since 0.2.0
 */
@Vetoed
public class Tm4jClientException extends TestException {
    public Tm4jClientException(String message) {
        super(message);
    }

    public Tm4jClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public Tm4jClientException(Throwable cause) {
        super(cause);
    }
}
