package com.LiterAlura.LiterAlura.modules;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String language;
    @ManyToOne
    private Author author;

    public Books(IDBook data) {
        this.name = data.bookName();
        this.language = String.join(",", data.languages());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }


    @Override
    public String toString() {
        return "\n---------------------------------------"+
                "\nName: " + name +
                "\nLanguage: " + language +
                "\nAuthor: " + author.getName() ;
    }
}