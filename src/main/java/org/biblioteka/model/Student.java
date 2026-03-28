package org.biblioteka.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
    @NamedQuery(name = Student.GET_ALL_STUDENTS, query = "Select s from Student s"),
    @NamedQuery(name = Student.GET_STUDENT_BY_NAME, query = "Select s from Student s where s.ime = :imeS")
})
public class Student {

    public static final String GET_ALL_STUDENTS = "GetAllStudents";
    public static final String GET_STUDENT_BY_NAME = "GetStudentByName";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
    @SequenceGenerator(name = "student_seq", sequenceName = "student_seq", allocationSize = 1)
    private Long id;

    private String ime;
    private String prezime;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private List<Phone> phones = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "adresa_id")
    private Adresa adresa;

    public Student() {
    }

    public Student(Long id, String ime, String prezime) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public Adresa getAdresa() {
        return adresa;
    }

    public void setAdresa(Adresa adresa) {
        this.adresa = adresa;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Student student)) {
            return false;
        }
        return Objects.equals(id, student.id)
            && Objects.equals(ime, student.ime)
            && Objects.equals(prezime, student.prezime)
            && Objects.equals(phones, student.phones)
            && Objects.equals(adresa, student.adresa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ime, prezime, phones, adresa);
    }
}
