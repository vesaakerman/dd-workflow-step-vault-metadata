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

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResumeMessage {

    private String status;
    private String reason;
    private String message;

    public ResumeMessage(@JsonProperty("Status") String status, @JsonProperty("Reason") String reason, @JsonProperty("Message") String message) {
        this.status = status;
        this.reason = reason;
        this.message = message;
    }

    @JsonProperty("Status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("Status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("Reason")
    public String getReason() {
        return reason;
    }

    @JsonProperty("Reason")
    public void setReason(String reason) {
        this.reason = reason;
    }

    @JsonProperty("Message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("Message")
    public void setMessage(String message) {
        this.message = message;
    }
}
