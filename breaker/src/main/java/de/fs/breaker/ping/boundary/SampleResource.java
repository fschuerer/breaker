package de.fs.breaker.ping.boundary;

import de.fs.breaker.ping.control.ServiceCaller;
import java.net.URI;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("sample")
public class SampleResource {

    @Inject
    ServiceCaller serviceCaller;

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public Response callOtherService(String uri) {
        serviceCaller.prepareService(URI.create(uri));
        return serviceCaller.callTheService();
    }
}
