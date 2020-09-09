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
package hu.icellmobilsoft.roaster.api;

/**
 * This exception should be thrown if an unexpected exception occours.
 *
 * NEJP-ről átemelve
 * 
 * @since 0.2
 *
 */
public class UnexpectedException extends TestException {
    private static final long serialVersionUID = 1L;

    /**
     * @see RuntimeException#RuntimeException(String,Throwable)
     * @param message
     *            the detail message.
     * @param cause
     *            the cause.
     */
    public UnexpectedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @see RuntimeException#RuntimeException(Throwable)
     * @param cause
     *            the cause.
     */
    public UnexpectedException(final Throwable cause) {
        super(cause);
    }
}