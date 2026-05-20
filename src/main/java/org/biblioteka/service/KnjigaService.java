package org.biblioteka.service;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import org.biblioteka.model.Autor;
import org.biblioteka.model.Knjiga;
import org.biblioteka.model.UploadedFile;
import org.jboss.resteasy.reactive.multipart.FileUpload;

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

    @Transactional
    public Knjiga getKnjigaWithUploadedFiles(Long id) {
        if (id == null) {
            return null;
        }
        return em.createQuery(
                "select distinct k from Knjiga k left join fetch k.uploadedFiles where k.id = :id",
                Knjiga.class
            )
            .setParameter("id", id)
            .getResultStream()
            .findFirst()
            .orElse(null);
    }

    @Transactional
    public UploadedFile findUploadedFileByFilename(String filename) {
        if (filename == null) {
            return null;
        }
        try {
            return em.createQuery(
                    "from UploadedFile uf where uf.filename = :filename",
                    UploadedFile.class
                )
                .setParameter("filename", filename)
                .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public Knjiga attachFileToKnjiga(Long knjigaId, String providedName, FileUpload upload, Path uploadDir)
        throws IOException {
        if (knjigaId == null || upload == null || uploadDir == null) {
            return null;
        }

        Knjiga knjiga = getKnjigaWithUploadedFiles(knjigaId);
        if (knjiga == null) {
            return null;
        }

        Files.createDirectories(uploadDir);

        String safeName = (providedName == null || providedName.isBlank())
            ? upload.fileName()
            : providedName;
        Path target = uploadDir.resolve(safeName).normalize();

        if (!target.startsWith(uploadDir.normalize())) {
            throw new IllegalArgumentException("Invalid filename/path");
        }

        if (!Files.exists(target)) {
            Files.copy(upload.uploadedFile(), target, StandardCopyOption.REPLACE_EXISTING);
        }

        String storedPath = target.toAbsolutePath().toString();
        UploadedFile uploadedFile = findUploadedFileByFilename(storedPath);
        if (uploadedFile == null) {
            uploadedFile = new UploadedFile(storedPath);
            em.persist(uploadedFile);
        }
        uploadedFile.setFile(target.toFile());

        if (knjiga.getUploadedFiles().stream().noneMatch(f -> storedPath.equals(f.getFilename()))) {
            knjiga.getUploadedFiles().add(uploadedFile);
        }

        return em.merge(knjiga);
    }
}
