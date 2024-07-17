package com.LiterAlura.LiterAlura.repositories;

import com.LiterAlura.LiterAlura.modules.Books;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BooksRepository extends JpaRepository<Books,Long> {

    boolean existsByName(String name);

    @Query("SELECT DISTINCT b.language FROM Book b ORDER BY b.language")
    List<String> findDistinctLanguages();

    @Query("SELECT b FROM Book b WHERE language = :selectedLanguage")
    List<Books> findByLanguage(String selectedLanguage);

    Books findByName(String name);

    @Query("SELECT b FROM Book b WHERE b.author.name ILIKE %:search%")
    List<Books> findBooksByAuthor(String search);
}

