package org.biblioteka.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
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
    public Student addStudent(Student student) {
        return studentService.createStudent(student);
    }
}
