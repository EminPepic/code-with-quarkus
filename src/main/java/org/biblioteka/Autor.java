package org.biblioteka;

import java.util.List;

public class Autor {

    private Long id;
    private String ime;

    private List<Knjiga> knjige; // OneToMany
}
