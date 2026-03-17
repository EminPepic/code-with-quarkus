package org.biblioteka.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Transient;
import java.util.Objects;

@Entity
@NamedQuery(name = Pozajmica.GET_ALL_POZAJMICE, query = "Select p from Pozajmica p")
public class Pozajmica {

    public static final String GET_ALL_POZAJMICE = "GetAllPozajmice";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pozajmica_seq")
    @SequenceGenerator(name = "pozajmica_seq", sequenceName = "pozajmica_seq", allocationSize = 1)
    public Long id;

    public String datum;

    @Transient
    public Knjiga knjiga;

    public Pozajmica() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public void setKnjiga(Knjiga knjiga) {
        this.knjiga = knjiga;
    }

    public Long getId() {
        return id;
    }

    public String getDatum() {
        return datum;
    }

    public Knjiga getKnjiga() {
        return knjiga;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pozajmica pozajmica = (Pozajmica) o;
        return Objects.equals(id, pozajmica.id)
            && Objects.equals(datum, pozajmica.datum)
            && Objects.equals(knjiga, pozajmica.knjiga);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, datum, knjiga);
    }
}
