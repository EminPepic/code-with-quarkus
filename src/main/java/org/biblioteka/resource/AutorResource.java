package org.biblioteka.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import org.biblioteka.model.Autor;
import org.biblioteka.model.Knjiga;
import org.biblioteka.service.AutorService;

@Path("/autor")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AutorResource {

    @Inject
    AutorService autorService;

    @GET
    @Path("/{id}/knjige")
    public Response getKnjigeByAutorId(@PathParam("id") Long id) {
        List<Knjiga> knjige = autorService.getKnjigeByAutorId(id);
        return Response.ok().entity(knjige).build();
    }

    @POST
    @Path("/addAutor")
    public Response addAutor(Autor autor) {
        Autor saved = autorService.createAutor(autor);
        return Response.ok().entity(saved).build();
    }
}
