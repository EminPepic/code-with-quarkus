package org.biblioteka.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Knjiga {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "knjiga_seq")
    @SequenceGenerator(name = "knjiga_seq", sequenceName = "knjiga_seq", allocationSize = 1)
    private Long id;

    private String naziv;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @JsonIgnore
    @OneToMany(mappedBy = "knjiga", fetch = FetchType.LAZY)
    private List<Pozajmica> pozajmice = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "knjiga_kategorija",
        joinColumns = @JoinColumn(name = "knjiga_id"),
        inverseJoinColumns = @JoinColumn(name = "kategorija_id")
    )
    private List<Kategorija> kategorije = new ArrayList<>();

    public Knjiga() {
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

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public List<Pozajmica> getPozajmice() {
        return pozajmice;
    }

    public void setPozajmice(List<Pozajmica> pozajmice) {
        this.pozajmice = pozajmice;
    }

    public List<Kategorija> getKategorije() {
        return kategorije;
    }

    public void setKategorije(List<Kategorija> kategorije) {
        this.kategorije = kategorije;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Knjiga knjiga)) {
            return false;
        }
        return Objects.equals(id, knjiga.id) && Objects.equals(naziv, knjiga.naziv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, naziv);
    }
}
