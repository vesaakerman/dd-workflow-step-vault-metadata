/*
 * Copyright (C) 2021 DANS - Data Archiving and Networked Services (info@dans.knaw.nl)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nl.knaw.dans.wf.vaultmd;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.client.HttpClientConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class DdWorkflowStepVaultMetadataConfiguration extends Configuration {

    @Valid
    @NotNull
    private HttpClientConfiguration httpClient = new HttpClientConfiguration();

    @Valid
    @NotNull
    private ThreadPoolExecutorFactory taskExecutorThreadPool;

    @JsonProperty("httpClient")
    public HttpClientConfiguration getHttpClientConfiguration() {
        return httpClient;
    }

    @JsonProperty("httpClient")
    public void setHttpClientConfiguration(HttpClientConfiguration httpClient) {
        this.httpClient = httpClient;
    }

    @JsonProperty("taskExecutorThreadPool")
    public void setTaskExecutorThreadPool(ThreadPoolExecutorFactory taskExecutorThreadPool) {
        this.taskExecutorThreadPool = taskExecutorThreadPool;
    }

    @JsonProperty("taskExecutorThreadPool")
    public ThreadPoolExecutorFactory getTaskExecutorThreadPool() {
        return taskExecutorThreadPool;
    }

}
