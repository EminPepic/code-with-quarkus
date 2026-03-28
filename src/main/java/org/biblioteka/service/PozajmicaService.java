package org.biblioteka.service;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.List;
import org.biblioteka.model.Pozajmica;

@Dependent
public class PozajmicaService {

    @Inject
    EntityManager em;

    @Transactional
    public Pozajmica createPozajmica(Pozajmica pozajmica) {
        return em.merge(pozajmica);
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
