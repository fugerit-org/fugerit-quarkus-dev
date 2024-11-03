package org.fugerit.java.demo;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.fugerit.java.demo.fop.ExampleFOP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;

@Path("/hello")
public class GreetingResource {

    private static Logger logger = LoggerFactory.getLogger( GreetingResource.class );

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }

    @GET
    @Produces("application/pdf")
    @Path("/pdf")
    public Response helloPdf() {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            ExampleFOP.writePdfExample( out );
            return Response.ok( out.toByteArray() ).build();
        } catch (Exception e) {
            String message = String.format( "ERROR : %s", e );
            logger.error( message, e );
            return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).build();
        }
    }

}
