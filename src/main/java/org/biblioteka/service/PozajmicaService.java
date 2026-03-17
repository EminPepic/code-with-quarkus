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
}
