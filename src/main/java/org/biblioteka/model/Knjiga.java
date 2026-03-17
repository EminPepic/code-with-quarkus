package org.biblioteka.model;

import java.util.List;

public class Knjiga {

    private Long id;
    private String naziv;

    private Autor autor; 

    private List<Pozajmica> pozajmice; 

    private List<Student> studenti; 

    private List<Kategorija> kategorije; 
}
