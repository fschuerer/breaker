package de.fs.breaker.ping.control;

import java.net.URI;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ws.rs.client.Client;
import static javax.ws.rs.client.ClientBuilder.newBuilder;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;

public class ServiceCaller {
    
    Client client;
    URI backendURI;

    @PostConstruct
    void init() {
        this.client = newBuilder()
                .connectTimeout(2, TimeUnit.SECONDS)
                .build();
    }

    @PreDestroy
    void destory() {
        if (client != null) {
            client.close();
        }
    }

    @Override
    public int hashCode() {
        if (backendURI != null) {
            return Objects.hashCode(backendURI.getAuthority());
        }
        return System.identityHashCode(this);
    }

    public void prepareService(URI backendURI) {
        this.backendURI = backendURI;
    }

    @CircuitBreaker(requestVolumeThreshold = 2)
    public Response callTheService() {
        return client
                .target(backendURI)
                .request()
                .get();
    }
}
