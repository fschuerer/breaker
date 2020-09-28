package de.fs.breaker.ping;

import java.net.URI;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

/**
 *
 * @author XLKAFR
 */
public class BreakerIT {

    BreakerClient client;
    
    static final String AVAILABLE_SERVICE = "http://payara.com";
    static final String UNAVAILABLE_SERVICE = "http://localhost:1234";
    
    @BeforeEach
    public void setup() {
        this.client = RestClientBuilder
                .newBuilder()
                .baseUri(URI.create("http://localhost:8080"))
                .property("microprofile.rest.client.disable.default.mapper",true)
                .build(BreakerClient.class);
    }
    
    @Test
    @Order(1)
    public void test() {
        Response response = client.callService(AVAILABLE_SERVICE);
        assertEquals(200, response.getStatus());
        
        response = client.callService(UNAVAILABLE_SERVICE);
        assertEquals(500, response.getStatus());
        assertEquals("ProcessingException", response.readEntity(String.class));
        
        response = client.callService(UNAVAILABLE_SERVICE);
        assertEquals(500, response.getStatus());
        
        response = client.callService(UNAVAILABLE_SERVICE);
        assertEquals(500, response.getStatus());
        assertEquals("CircuitBreakerOpenException", response.readEntity(String.class));
        
        response = client.callService(AVAILABLE_SERVICE);
        assertEquals(200, response.getStatus());
        
        response = client.callService(UNAVAILABLE_SERVICE);
        assertEquals(500, response.getStatus());
        assertEquals("CircuitBreakerOpenException", response.readEntity(String.class));
        
        response = client.callService(AVAILABLE_SERVICE);
        assertEquals(200, response.getStatus());
    }
    
    @Test
    @Order(2)
    // Only valid for the first run, other deployment of breaker.war
    public void metrics() {
        JsonObject metrics = client.metrics();
        
        assertEquals(1, metrics.getInt("ft.de.fs.breaker.ping.control.ServiceCaller.callTheService.circuitbreaker.opened.total"));
        assertEquals(4, metrics.getInt("ft.de.fs.breaker.ping.control.ServiceCaller.callTheService.invocations.failed.total"));
        
        assertEquals(3, metrics.getInt("ft.de.fs.breaker.ping.control.ServiceCaller.callTheService.circuitbreaker.callsSucceeded.total"));
        assertEquals(7, metrics.getInt("ft.de.fs.breaker.ping.control.ServiceCaller.callTheService.invocations.total"));
    }
}
