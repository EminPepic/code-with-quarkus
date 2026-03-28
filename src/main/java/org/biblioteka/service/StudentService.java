package org.biblioteka.service;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.List;
import org.biblioteka.exception.StudentException;
import org.biblioteka.model.Phone;
import org.biblioteka.model.Student;

@Dependent
public class StudentService {

    @Inject
    EntityManager em;

    @Transactional
    public Student createStudent(Student student) throws StudentException {
        if (student == null) {
            throw new StudentException("Student nije proslijedjen");
        }
        if (student.getIme() == null || student.getIme().isEmpty()) {
            throw new StudentException("Ime je prazno");
        }
        if (student.getPrezime() == null || student.getPrezime().isEmpty()) {
            throw new StudentException("Prezime je prazno");
        }

        return em.merge(student);
    }

    @Transactional
    public List<Student> getAllStudents() throws StudentException {
        List<Student> students =
            em.createNamedQuery(Student.GET_ALL_STUDENTS, Student.class).getResultList();
        if (students.isEmpty()) {
            throw new StudentException("Nema studenata.");
        }
        return students;
    }

    @Transactional
    public List<Student> getStudentByName(String ime) {
        return em.createNamedQuery(Student.GET_STUDENT_BY_NAME, Student.class)
            .setParameter("imeS", ime)
            .getResultList();
    }

    @Transactional
    public List<Phone> getPhonesByStudentId(Long id) {
        return em.createNamedQuery(Phone.GET_ALL_PHONES_FOR_STUDENT_ID, Phone.class)
            .setParameter("id", id)
            .getResultList();
    }
}
