package nl.knaw.dans.wf.vaultmd.core;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;

import java.net.URI;

public class DataverseClient {

    private final URI baseUrl;
    private final HttpClient httpClient;

    public DataverseClient(URI baseUrl) {
        this(baseUrl, HttpClients.createDefault());
    }

    public DataverseClient(URI baseUrl, HttpClient httpClient) {
        this.baseUrl = baseUrl;
        this.httpClient = httpClient;
    }

    public WorkflowsApi workflows() {
        return new WorkflowsApi(baseUrl.resolve("api/workflows/"), httpClient);
    }

}
