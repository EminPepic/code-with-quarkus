package org.biblioteka.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import java.util.Objects;

@Entity
@NamedQueries({
    @NamedQuery(name = Pozajmica.GET_ALL_POZAJMICE, query = "Select p from Pozajmica p"),
    @NamedQuery(name = Pozajmica.GET_POZAJMICE_BY_STUDENT_ID, query = "Select p from Pozajmica p where p.student.id = :id"),
    @NamedQuery(name = Pozajmica.GET_POZAJMICE_BY_DATUM, query = "Select p from Pozajmica p where p.datum = :datum")
})
public class Pozajmica {

    public static final String GET_ALL_POZAJMICE = "GetAllPozajmice";
    public static final String GET_POZAJMICE_BY_STUDENT_ID = "GetPozajmiceByStudentId";
    public static final String GET_POZAJMICE_BY_DATUM = "GetPozajmiceByDatum";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pozajmica_seq")
    @SequenceGenerator(name = "pozajmica_seq", sequenceName = "pozajmica_seq", allocationSize = 1)
    private Long id;

    private String datum;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "knjiga_id")
    private Knjiga knjiga;

    public Pozajmica() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Knjiga getKnjiga() {
        return knjiga;
    }

    public void setKnjiga(Knjiga knjiga) {
        this.knjiga = knjiga;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pozajmica pozajmica)) {
            return false;
        }
        return Objects.equals(id, pozajmica.id)
            && Objects.equals(datum, pozajmica.datum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, datum);
    }
}
