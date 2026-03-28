package org.biblioteka.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import org.biblioteka.exception.StudentException;
import org.biblioteka.model.Phone;
import org.biblioteka.model.Student;
import org.biblioteka.service.StudentService;

@Path("/student")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudentResource {

    @Inject
    StudentService studentService;

    @POST
    @Path("/addStudent")
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
    public Response getStudentByName(@QueryParam("name") String name) {
        List<Student> students = studentService.getStudentByName(name);
        return Response.ok().entity(students).build();
    }

    @GET
    @Path("/getPhonesByStudentId")
    public Response getPhonesByStudentId(@QueryParam("id") Long id) {
        List<Phone> phones = studentService.getPhonesByStudentId(id);
        return Response.ok().entity(phones).build();
    }
}
