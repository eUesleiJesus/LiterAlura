package com.LiterAlura.LiterAlura.main;

import com.LiterAlura.LiterAlura.modules.Author;
import com.LiterAlura.LiterAlura.modules.IDAuthor;
import com.LiterAlura.LiterAlura.modules.Books;
import com.LiterAlura.LiterAlura.modules.IDBook;

import com.LiterAlura.LiterAlura.repositories.AuthorRepository;
import com.LiterAlura.LiterAlura.repositories.BooksRepository;

import com.LiterAlura.LiterAlura.services.ConverterData;
import com.LiterAlura.LiterAlura.services.APIRequest;



import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private Scanner sc = new Scanner(System.in);
    private APIRequest request = new APIRequest();
    private AuthorRepository authorRepository;
    private BooksRepository booksRepository;
    private List<Books> Books = new ArrayList<>();
    private ConverterData converter = new ConverterData();
    private final String ADDRESS = "https://gutendex.com/books?search=";

    public Main(AuthorRepository authorRepository, BooksRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.booksRepository = booksRepository;
    }

    public void run(){
        String menu = """
                **********************************************
                        1 - Search book by title
                        2 - Search authors by name
                        3 - List registered books
                        4 - List registered authors
                        5 - Search authors by name
                     
                
                                0 - Exit
                **********************************************
                """;
        var option = -1;
        while (option != 0){
            System.out.println(menu);
            option = sc.nextInt();
            sc.nextLine();

            switch (option){
                case 1:
                    searchNewBook();
                    break;
                case 2:
                    searchAuthorByName();
                    break;
                case 3:
                    searchRegisteredBooks();
                    break;
                case 4:
                    searchRegisteredAuthors();
                    break;
                case 5:
                    searchAuthorsByYear();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("\n\n***Invalid Option***\n\n");
            }
        }


    }

    private void searchNewBook() {
        System.out.println("\nWhat book do you want to search for?");
        var userSearch = sc.nextLine();
        var data = request.consume(ADDRESS+ userSearch.replace(" ","%20"));
        saveToDb(data);
    }

    private void saveToDb(String data){
        try{
            Books books = new Books(converter.getData(data, IDBook.class));
            Author author = new Author(converter.getData(data, IDAuthor.class));
            Author IDAuthor = null;
            Books IDBook = null;
            if (!authorRepository.existsByName(author.getName())){
                authorRepository.save(author);
                IDAuthor = author;
            }else{
                IDAuthor = authorRepository.findByName(author.getName());
            }
            if (!booksRepository.existsByName(Books.getName())){
                Books.setAuthor(authorDb);
                booksRepository.save(Books);
                IDBook = Books;
            }else{
                IDBook = booksRepository.findByName(books.getName());
            }
            System.out.println(IDBook);
        }catch (NullPointerException e){
            System.out.println("\n\n*** Book not found ***\n\n");
        }

    }


    private void searchRegisteredBooks() {
        var dbSearches = booksRepository.findAll();
        if(!dbSearches.isEmpty()){
            System.out.println("\nBooks registered in the database: ");
            dbSearches.forEach(System.out::println);
        }else{
            System.out.println("\nNo books found in the database!");
        }
    }

    private void searchRegisteredAuthors() {
        var dbSearch = authorRepository.findAll();
        if(!dbSearch.isEmpty()){
            System.out.println("\nAuthors registered in the database:");
            dbSearch.forEach(System.out::println);
        }else{
            System.out.println("\nNo authors found in the database!");
        }
    }

    private void searchAuthorsByYear() {
        System.out.println("\nWhat year do you want to search for?");
        int selectedYear = sc.nextInt();
        sc.nextLine(); // Consume newline character

        List<Author> searchAuthorsInDb = authorRepository.findByDeathYear(selectedYear);
        if (!searchAuthorsInDb.isEmpty()) {
            System.out.println("\n\nAuthors alive in the year: " + selectedYear);
            searchAuthorsInDb.forEach(System.out::println);
        } else {
            System.out.println("\nNo authors found for this year!");
        }
    }

    private void searchAuthorByName() {
        System.out.println("What is the author's name?");
        String search = sc.nextLine();
        List<Author> author = authorRepository.findByNameContaining(search);
        if (!author.isEmpty()) {
            author.forEach(System.out::println);
        } else {
            System.out.println("*** Author not found! ***");
        }
    }

}