package org.biblioteka.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.biblioteka.exception.StudentException;
import org.biblioteka.model.Phone;
import org.biblioteka.model.Student;
import org.biblioteka.service.StudentService;
import io.quarkus.security.identity.SecurityIdentity;

@Path("/student")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudentResource {

    @Inject
    StudentService studentService;

    @Inject
    SecurityIdentity securityIdentity;

    @POST
    @Path("/addStudent")
    @RolesAllowed("Admin")
    public Response addStudent(Student student) {
        try {
            studentService.createStudent(student);
        } catch (StudentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.ok().build();
    }

    @GET
    @Path("/getAllStudents")
    @Transactional
    public Response getAllStudents() {
        List<Student> students;
        try {
            students = studentService.getAllStudents();
        } catch (StudentException e) {
            return Response.status(Response.Status.NO_CONTENT).entity(e.getMessage()).build();
        }
        return Response.ok().entity(students).build();
    }

    @GET
    @Path("/getStudentByName")
    @Transactional
    public Response getStudentByName(@QueryParam("name") String name) {
        List<Student> students = studentService.getStudentByName(name);
        return Response.ok().entity(students).build();
    }

    @GET
    @Path("/getPhonesByStudentId")
    @Transactional
    public Response getPhonesByStudentId(@QueryParam("id") Long id) {
        List<Phone> phones = studentService.getPhonesByStudentId(id);
        return Response.ok().entity(phones).build();
    }
}
