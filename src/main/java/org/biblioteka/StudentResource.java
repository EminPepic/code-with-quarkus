package org.biblioteka;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/students")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudentResource {

    @Inject
    StudentService studentService;

    // Upis novog studenta
    @POST
    public void addStudent(Student student) {
        studentService.addStudent(student);
    }

    // Dohvat svih studenata
    @GET
    public List<Student> getStudents() {
        return studentService.getAllStudents();
    }
}
