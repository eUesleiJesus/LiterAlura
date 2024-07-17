package com.LiterAlura.LiterAlura.repositories;

import com.LiterAlura.LiterAlura.modules.Author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Boolean existsByName(String name);

    Author findByName(String name);

    @Query("SELECT a FROM Author a WHERE a.deathDate >= :selectedYear AND :selectedYear >= a.birthDate")
    List<Author> findByDeathYear(int selectedYear);

    @Query("SELECT a FROM Author a WHERE a.name ILIKE %:search%")
    List<Author> findByNameContaining(String search);
}