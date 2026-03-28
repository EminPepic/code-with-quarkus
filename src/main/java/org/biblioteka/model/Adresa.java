package org.biblioteka.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import java.util.Objects;

@Entity
public class Adresa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adresa_seq")
    @SequenceGenerator(name = "adresa_seq", sequenceName = "adresa_seq", allocationSize = 1)
    private Long id;

    private String ulica;
    private String grad;

    public Adresa() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Adresa adresa)) {
            return false;
        }
        return Objects.equals(id, adresa.id)
            && Objects.equals(ulica, adresa.ulica)
            && Objects.equals(grad, adresa.grad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ulica, grad);
    }
}
