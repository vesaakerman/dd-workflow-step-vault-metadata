package nl.knaw.dans.wf.vaultmd.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import java.io.IOException;
import java.net.URI;

public class WorkflowsApi {

    private static final ObjectMapper mapper = new ObjectMapper();

    private final URI baseUrl;
    private final HttpClient httpClient;

    public WorkflowsApi(URI baseUrl, HttpClient httpClient) {
        this.baseUrl = baseUrl;
        this.httpClient = httpClient;
    }

    public HttpResponse resume(String invocationId, ResumeMessage resumeMessage) throws IOException {
        HttpPost post = new HttpPost(baseUrl.resolve(invocationId));
        post.setEntity(new StringEntity(mapper.writeValueAsString(resumeMessage)));
        return httpClient.execute(post);
    }

}
