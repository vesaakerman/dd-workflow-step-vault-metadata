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
package nl.knaw.dans.wf.vaultmd.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.knaw.dans.wf.vaultmd.api.StepInvocation;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class SetVaultMetadataTask implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(SetVaultMetadataTask.class);
    private final StepInvocation stepInvocation;
    private final HttpClient httpClient;

    public SetVaultMetadataTask(StepInvocation stepInvocation, HttpClient httpClient) {
        this.stepInvocation = stepInvocation;
        this.httpClient = httpClient;
    }

    @Override
    public String toString() {
        return "SetVaultMetadataTask{" + "invocationId='" + stepInvocation.getInvocationId() + "'}";
    }

    @Override
    public void run() {
        log.info("Running task " + this);
        try {
            try {
                Thread.sleep(3000);
            }
            catch (InterruptedException e) {
            }
        }
        catch (RuntimeException e) {
            log.warn("Task execution failed for task " + this, e);
        }
        log.info("Resuming publication");
        try {
            HttpPost post = new HttpPost(new URI("http://dar.dans.knaw.nl:8080/api/workflows/" + stepInvocation.getInvocationId()));
            ResumeMessage msg = new ResumeMessage("Success", "", "");
            ObjectMapper mapper = new ObjectMapper(); // TODO: reuse
            post.setEntity(new StringEntity(mapper.writeValueAsString(msg)));
            HttpResponse response = httpClient.execute(post);
            log.info("Resume call returned " + response.getStatusLine());
        }
        catch (URISyntaxException e) {
            log.error("Invalid URI for resume call", e);
        }
        catch (ClientProtocolException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        log.info("Done running task " + this);
    }

}
