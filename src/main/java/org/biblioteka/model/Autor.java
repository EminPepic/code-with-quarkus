package org.biblioteka.model;

import java.util.List;

public class Autor {

    private Long id;
    private String ime;

    private List<Knjiga> knjige; // OneToMany
}
