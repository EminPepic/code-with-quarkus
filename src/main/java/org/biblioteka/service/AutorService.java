package org.biblioteka.service;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.List;
import org.biblioteka.model.Autor;
import org.biblioteka.model.Knjiga;

@Dependent
public class AutorService {

    @Inject
    EntityManager em;

    @Transactional
    public List<Knjiga> getKnjigeByAutorId(Long id) {
        return em.createQuery(
                "select k from Knjiga k where k.autor.id = :id",
                Knjiga.class)
            .setParameter("id", id)
            .getResultList();
    }

    @Transactional
    public Autor createAutor(Autor autor) {
        return em.merge(autor);
    }
}
