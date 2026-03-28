package org.biblioteka.service;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.biblioteka.model.Autor;
import org.biblioteka.model.Knjiga;

@Dependent
public class KnjigaService {

    @Inject
    EntityManager em;

    @Transactional
    public Knjiga createKnjiga(Knjiga knjiga) {
        if (knjiga != null && knjiga.getAutor() != null && knjiga.getAutor().getId() != null) {
            Autor autorRef = em.getReference(Autor.class, knjiga.getAutor().getId());
            knjiga.setAutor(autorRef);
        }
        return em.merge(knjiga);
    }
}
