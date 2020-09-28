package de.fs.breaker.ping;

import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 *
 * @author XLKAFR
 */
@RegisterRestClient
public interface BreakerClient {

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("breaker/resources/sample")
    Response callService(String uri);
    
    @GET
    @Path("metrics/application")
    @Produces(MediaType.APPLICATION_JSON)
    JsonObject metrics();
}
