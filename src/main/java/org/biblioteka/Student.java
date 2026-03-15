package org.biblioteka;

import java.util.List;

public class Student {

    private Long id;
    private String ime;

    private BiblioteckaKartica biblioteckaKartica; // OneToOne

    private List<Knjiga> knjige; // ManyToMany
}
