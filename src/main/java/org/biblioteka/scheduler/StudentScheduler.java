package org.biblioteka.scheduler;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.logging.Logger;

@ApplicationScoped
public class StudentScheduler {

    private static final Logger LOG = Logger.getLogger(StudentScheduler.class.getName());

    @Inject
    EntityManager em;

    @Scheduled(every = "1h")
    @Transactional
    public void logStats() {
        Long students = em.createQuery("select count(s) from Student s", Long.class).getSingleResult();
        Long pozajmice = em.createQuery("select count(p) from Pozajmica p", Long.class).getSingleResult();
        LOG.info("Statistika: studenti=" + students + ", pozajmice=" + pozajmice);
    }
}
