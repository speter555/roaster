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

import hu.icellmobilsoft.roaster.tm4j.dto.domain.test_execution.Execution;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.Status.Family;
import javax.ws.rs.core.Response.StatusType;
import java.util.List;
import java.util.Objects;

/**
 * Class for handling the TM4J client calls
 *
 * @author martin.nagy
 * @since 0.2.0
 */
@Dependent
public class RestTm4jService {

    @Inject
    @RestClient
    private Tm4jRestClient tm4jClient;

    /**
     * Returns {@code true} if the test run exists with the given key on the server
     *
     * @param key test run key used at the search on the server
     * @return {@code true} if the test run exists with the given key on the server
     */
    public boolean isTestRunExist(String key) {
        Response response = tm4jClient.headTestRun(Objects.requireNonNull(key));
        return isEntityExistsBasedOnResponseStatus(response.getStatusInfo());
    }

    /**
     * Returns {@code true} if the test case exists with the given key on the server
     *
     * @param key test case key used at the search on the server
     * @return {@code true} if the test case exists with the given key on the server
     */
    public boolean isTestCaseExist(String key) {
        Response response = tm4jClient.headTestCase(Objects.requireNonNull(key));
        return isEntityExistsBasedOnResponseStatus(response.getStatusInfo());
    }

    /**
     * Posts the test execution data to the server with the given test run key
     *
     * @param testRunKey the test run key
     * @param execution the {@code Execution} to be published
     */
    public void postResult(String testRunKey, Execution execution) {
        Objects.requireNonNull(testRunKey);
        Objects.requireNonNull(execution);

        tm4jClient.postExecutions(testRunKey, List.of(execution));
    }

    private boolean isEntityExistsBasedOnResponseStatus(StatusType statusType) {
        if (statusType.getFamily() == Family.SUCCESSFUL) {
            return true;
        }
        if (statusType.toEnum() == Status.NOT_FOUND) {
            return false;
        }
        throw new Tm4jClientException("Rest endpoint responded with: " + statusType);
    }
}
