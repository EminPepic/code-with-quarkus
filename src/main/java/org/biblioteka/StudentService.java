package org.biblioteka;

import java.util.ArrayList;
import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StudentService {

    private List<Student> students = new ArrayList<>();

    // Upis novog studenta
    public void addStudent(Student student){
        students.add(student);
    }

    // Dohvat svih studenata
    public List<Student> getAllStudents(){
        return students;
    }
}
