package com.LiterAlura.LiterAlura;

import com.LiterAlura.LiterAlura.main.Main;

import com.LiterAlura.LiterAlura.repositories.AuthorRepository;
import com.LiterAlura.LiterAlura.repositories.BooksRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {
	@Autowired
	AuthorRepository authorRepository;
	@Autowired
	BooksRepository booksRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(authorRepository,booksRepository);
		main.run();
	}
}
