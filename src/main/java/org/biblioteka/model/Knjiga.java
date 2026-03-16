package org.biblioteka.model;

import java.util.List;

public class Knjiga {

    private Long id;
    private String naziv;

    private Autor autor; // ManyToOne

    private List<Pozajmica> pozajmice; // OneToMany

    private List<Student> studenti; // ManyToMany

    private List<Kategorija> kategorije; // ManyToMany
}
