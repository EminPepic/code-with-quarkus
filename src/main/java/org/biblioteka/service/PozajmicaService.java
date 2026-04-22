package org.biblioteka.service;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.List;
import org.biblioteka.model.Knjiga;
import org.biblioteka.model.Pozajmica;
import org.biblioteka.model.Student;

@Dependent
public class PozajmicaService {

    @Inject
    EntityManager em;

    @Transactional
    public Pozajmica createPozajmica(Pozajmica pozajmica) {
        if (pozajmica != null) {
            if (pozajmica.getKnjiga() != null && pozajmica.getKnjiga().getId() != null) {
                Knjiga knjigaRef = em.getReference(Knjiga.class, pozajmica.getKnjiga().getId());
                pozajmica.setKnjiga(knjigaRef);
            }
            if (pozajmica.getStudent() != null && pozajmica.getStudent().getId() != null) {
                Student studentRef = em.getReference(Student.class, pozajmica.getStudent().getId());
                pozajmica.setStudent(studentRef);
            }
        }

        Pozajmica saved = em.merge(pozajmica);

        return em.createQuery(
                "select p from Pozajmica p left join fetch p.knjiga where p.id = :id",
                Pozajmica.class)
            .setParameter("id", saved.getId())
            .getSingleResult();
    }

    @Transactional
    public List<Pozajmica> getAllPozajmice() {
        return em.createNamedQuery(Pozajmica.GET_ALL_POZAJMICE, Pozajmica.class).getResultList();
    }

    @Transactional
    public List<Pozajmica> getPozajmiceByStudentId(Long id) {
        return em.createNamedQuery(Pozajmica.GET_POZAJMICE_BY_STUDENT_ID, Pozajmica.class)
            .setParameter("id", id)
            .getResultList();
    }

    @Transactional
    public List<Pozajmica> getPozajmiceByDatum(String datum) {
        return em.createNamedQuery(Pozajmica.GET_POZAJMICE_BY_DATUM, Pozajmica.class)
            .setParameter("datum", datum)
            .getResultList();
    }
}
