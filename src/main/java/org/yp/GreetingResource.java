package org.yp;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/ping")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Tag(name = "Ping", description = "Echo")
    public String hello() {
        return "pong";
    }
}