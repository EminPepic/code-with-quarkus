package org.biblioteka.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Kategorija {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "kategorija_seq")
    @SequenceGenerator(name = "kategorija_seq", sequenceName = "kategorija_seq", allocationSize = 1)
    private Long id;

    private String naziv;

    @JsonIgnore
    @ManyToMany(mappedBy = "kategorije", fetch = FetchType.LAZY)
    private List<Knjiga> knjige = new ArrayList<>();

    public Kategorija() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public List<Knjiga> getKnjige() {
        return knjige;
    }

    public void setKnjige(List<Knjiga> knjige) {
        this.knjige = knjige;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Kategorija kategorija)) {
            return false;
        }
        return Objects.equals(id, kategorija.id) && Objects.equals(naziv, kategorija.naziv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, naziv);
    }
}
