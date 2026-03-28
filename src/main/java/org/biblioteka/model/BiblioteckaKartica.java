package org.biblioteka.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import java.util.Objects;

@Entity
public class BiblioteckaKartica {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "kartica_seq")
    @SequenceGenerator(name = "kartica_seq", sequenceName = "kartica_seq", allocationSize = 1)
    private Long id;

    private String broj;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", unique = true)
    private Student student;

    public BiblioteckaKartica() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBroj() {
        return broj;
    }

    public void setBroj(String broj) {
        this.broj = broj;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BiblioteckaKartica kartica)) {
            return false;
        }
        return Objects.equals(id, kartica.id) && Objects.equals(broj, kartica.broj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, broj);
    }
}
