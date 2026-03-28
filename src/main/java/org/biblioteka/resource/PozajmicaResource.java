package org.biblioteka.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import org.biblioteka.model.Pozajmica;
import org.biblioteka.service.PozajmicaService;

@Path("/pozajmica")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PozajmicaResource {

    @Inject
    PozajmicaService pozajmicaService;

    @POST
    @Path("/addPozajmica")
    public Pozajmica addPozajmica(Pozajmica pozajmica) {
        return pozajmicaService.createPozajmica(pozajmica);
    }

    @GET
    @Path("/getAllPozajmice")
    public Response getAllPozajmice() {
        List<Pozajmica> pozajmice = pozajmicaService.getAllPozajmice();
        return Response.ok().entity(pozajmice).build();
    }

    @GET
    @Path("/byStudent/{id}")
    public Response getPozajmiceByStudentId(@PathParam("id") Long id) {
        List<Pozajmica> pozajmice = pozajmicaService.getPozajmiceByStudentId(id);
        return Response.ok().entity(pozajmice).build();
    }

    @GET
    @Path("/byDatum")
    public Response getPozajmiceByDatum(@QueryParam("datum") String datum) {
        List<Pozajmica> pozajmice = pozajmicaService.getPozajmiceByDatum(datum);
        return Response.ok().entity(pozajmice).build();
    }
}
