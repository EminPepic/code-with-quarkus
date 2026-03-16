package org.biblioteka.service;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.biblioteka.model.Student;

@Dependent
public class StudentService {

    @Inject
    EntityManager em;

    @Transactional
    public Student createStudent(Student student) {
        return em.merge(student);
    }
}
