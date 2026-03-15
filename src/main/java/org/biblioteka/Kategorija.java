package org.biblioteka;

import java.util.List;

public class Kategorija {

    private Long id;
    private String naziv;

    private List<Knjiga> knjige; // ManyToMany
}
