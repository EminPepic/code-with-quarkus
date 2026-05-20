package org.biblioteka.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.biblioteka.model.Knjiga;
import org.biblioteka.model.UploadedFile;
import org.biblioteka.service.KnjigaService;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

@Path("/knjiga")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class KnjigaResource {

    @Inject
    KnjigaService knjigaService;

    @ConfigProperty(name = "app.upload.dir", defaultValue = "uploads")
    String uploadDir;

    @POST
    @Path("/addKnjiga")
    public Response addKnjiga(Knjiga knjiga) {
        Knjiga saved = knjigaService.createKnjiga(knjiga);
        return Response.ok().entity(saved).build();
    }

    public static class UploadForm {
        @RestForm("name")
        public String name;

        @RestForm("file")
        public FileUpload file;
    }

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(@QueryParam("id") Long knjigaId, @BeanParam UploadForm form) {
        if (knjigaId == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Missing query param: id").build();
        }
        if (form == null || form.file == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Missing multipart field: file").build();
        }

        java.nio.file.Path dir = Paths.get(uploadDir);
        String fileName = (form.name == null || form.name.isBlank()) ? form.file.fileName() : form.name;
        java.nio.file.Path target = dir.resolve(fileName).normalize();
        boolean alreadyExisted = Files.exists(target);

        try {
            Knjiga updated = knjigaService.attachFileToKnjiga(knjigaId, form.name, form.file, dir);
            if (updated == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Knjiga not found for id=" + knjigaId).build();
            }
            return Response.status(alreadyExisted ? Response.Status.OK : Response.Status.CREATED).entity(updated).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (IOException e) {
            return Response.serverError().entity("Failed to store file").build();
        }
    }

    @GET
    @Path("/getKnjigaById")
    public Response getKnjigaById(@QueryParam("id") Long id) {
        if (id == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Missing query param: id").build();
        }
        Knjiga knjiga = knjigaService.getKnjigaWithUploadedFiles(id);
        if (knjiga == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Knjiga not found for id=" + id).build();
        }
        for (UploadedFile uploadedFile : knjiga.getUploadedFiles()) {
            if (uploadedFile.getFilename() != null) {
                uploadedFile.setFile(new File(uploadedFile.getFilename()));
            }
        }
        return Response.ok(knjiga).build();
    }
}
