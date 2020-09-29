package de.fs.breaker.ping.boundary;

import de.fs.breaker.ping.control.ServiceCaller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class RequestRouter implements ContainerRequestFilter {
    
    @Inject
    ServiceCaller serviceCaller;
    
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if (requestContext.hasEntity()) {
            InputStream entityStream = requestContext.getEntityStream();
            String uri = new BufferedReader(new InputStreamReader(entityStream)).readLine();
            serviceCaller.prepareService(URI.create(uri));
            Response response = serviceCaller.callTheService();
            requestContext.abortWith(response);
        }
    }
}
