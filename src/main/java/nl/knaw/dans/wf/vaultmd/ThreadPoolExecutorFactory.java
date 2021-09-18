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
import io.dropwizard.lifecycle.Managed;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// TODO: Move to dans-utils (or should it be named dans-dropwizard-utils? It has a dependency on DropWizard classes)
public class ThreadPoolExecutorFactory {

    private static final Logger log = LoggerFactory.getLogger(ThreadPoolExecutorFactory.class);

    private int maxQueueSize;

    private int minThreads;

    private int maxThreads;

    private long maxIdleSeconds;

    public ThreadPoolExecutor build(Environment environment) {
        final ThreadPoolExecutor executor = new ThreadPoolExecutor(minThreads, maxThreads, maxIdleSeconds, TimeUnit.SECONDS, new LinkedBlockingDeque<>(maxQueueSize));
        log.info("Created thread pool executor minThreads = " + minThreads + ", maxThreads = " + maxThreads);
        environment.lifecycle().manage(new Managed() {

            @Override
            public void start() {
            }

            @Override
            public void stop() {
                executor.shutdown();
            }
        });
        return executor;
    }

    @JsonProperty
    public int getMinThreads() {
        return minThreads;
    }

    @JsonProperty
    public void setMinThreads(int minThreads) {
        this.minThreads = minThreads;
    }

    @JsonProperty
    public int getMaxThreads() {
        return maxThreads;
    }

    @JsonProperty
    public void setMaxThreads(int maxThreads) {
        this.maxThreads = maxThreads;
    }

    @JsonProperty
    public long getMaxIdleSeconds() {
        return maxIdleSeconds;
    }

    @JsonProperty
    public void setMaxIdleSeconds(long maxIdleSeconds) {
        this.maxIdleSeconds = maxIdleSeconds;
    }

    @JsonProperty
    public int getMaxQueueSize() {
        return maxQueueSize;
    }

    @JsonProperty
    public void setMaxQueueSize(int maxQueueSize) {
        this.maxQueueSize = maxQueueSize;
    }
}
