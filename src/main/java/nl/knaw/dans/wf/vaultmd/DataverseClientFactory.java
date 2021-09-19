package nl.knaw.dans.wf.vaultmd;

import nl.knaw.dans.wf.vaultmd.core.DataverseClient;

import java.net.URI;

public class DataverseClientFactory {

    private URI baseUrl;

    public DataverseClient build() {
        return new DataverseClient(baseUrl);
    }

    public void setBaseUrl(URI baseUrl) {
        this.baseUrl = baseUrl;
    }
}
