package com.LiterAlura.LiterAlura.modules;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private int birthDate;
    private int deathDate;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Books> books = new ArrayList<>();

    public Author(IDAuthor data) {
        this.name = data.authorName();
        this.birthDate = data.birthYear();
        this.deathDate = data.deathYear();
    }

    public Long getId() {
        return id;
    }

    public void
     setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(int birthDate) {
        this.birthDate = birthDate;
    }

    public int getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(int deathDate) {
        this.deathDate = deathDate;
    }

    public List<Books> getBooks() {
        return books;
    }

    public void setBooks(List<Books> books) {
        books.forEach(l -> l.setAuthor(this));
        this.books = books;
    }


    @Override
    public String toString() {
        return "---------------------------------------"+
                "\nName: " + name +
                "\nBirth Date: " + birthDate +
                "\nDeath Date: " + deathDate+
                "\n---------------------------------------";
    }
}