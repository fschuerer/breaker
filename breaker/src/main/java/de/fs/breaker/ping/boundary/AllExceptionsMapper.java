package de.fs.breaker.ping.boundary;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AllExceptionsMapper implements ExceptionMapper<Exception>{

    @Override
    public Response toResponse(Exception exception) {
        return Response.serverError().entity(exception.getClass().getSimpleName()).build();
    }
}
