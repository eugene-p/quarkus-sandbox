package org.yp;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.yp.models.Event;

@Path("/events")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventsResource {
    private Set<Event> events = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    public EventsResource() {
        events.add(new Event("Event 1", "Happening @ 1"));
        events.add(new Event("Event 2", "Happening @ 2"));
    }

    @GET
    @Tag(name = "Event", description = "List events")
    public Response list() {
        return Response.ok(events).build();
    }

    @GET
    @Path("{id}")
    @Tag(name = "Event", description = "Get event by id")
    public Response get(@PathParam("id") Integer id) {
        Optional<Event> evt = events.stream()
            .filter(e -> e.id.equals(id))
            .findFirst();

        if (!evt.isPresent()) {
            return Response.status(404).build();
        }

        return Response.ok(evt.get()).build();
    }

    @POST
    @Tag(name = "Event", description = "Create event")
    public Response post(Event event) {
        System.out.println(event);
        Event evt = new Event(event.name, event.type);
        events.add(evt);
        return Response.ok(evt).status(201).build();
    }

    @DELETE
    @Path("{id}")
    @Tag(name = "Event", description = "Delete event")
    public Response delete(@PathParam("id") Integer id) {

        if (events.removeIf(evt -> evt.id.equals(id))) {
            return Response.status(204).build();
        }

        return Response.status(404).build();
    }
}
