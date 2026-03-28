package org.biblioteka.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.biblioteka.model.Knjiga;
import org.biblioteka.service.KnjigaService;

@Path("/knjiga")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class KnjigaResource {

    @Inject
    KnjigaService knjigaService;

    @POST
    @Path("/addKnjiga")
    public Response addKnjiga(Knjiga knjiga) {
        Knjiga saved = knjigaService.createKnjiga(knjiga);
        return Response.ok().entity(saved).build();
    }
}
