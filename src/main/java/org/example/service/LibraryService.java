package org.example.service;

import org.example.exception.EntityNotFoundException;
import org.example.exception.ValidationException;
import org.example.model.Book;
import org.example.storage.LibraryStorage;

import java.time.Year;
import java.util.List;


public class LibraryService {
    private final LibraryStorage storage;

    public LibraryService(LibraryStorage storage) {
        this.storage = storage;
    }

    public void addBook(Book book) {
        try {
            validation(book);
            storage.addBook(book);
            System.out.println("Book added");
        } catch (ValidationException e) {
            System.out.println("Validation Error: " + e.getMessage());
        }
    }

    public void deleteBook(String isbn) {
        try {
            if (storage.getByIsbn(isbn) == null) {
                throw new EntityNotFoundException("Book with ISBN= " + isbn + " not in library");
            } else {
                storage.deleteBook(isbn);
                System.out.println("Book removed");
            }
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());

        }
    }

    public void searchBookByTitle(String title) {

        List<Book> foundByTitle = storage.findByTitle(title);
        if (foundByTitle.isEmpty()) {
            System.out.println("There is no books with title: " + title);
        }
        foundByTitle.forEach(System.out::println);
    }

    public void searchBookByAuthor(String author) {
        List<Book> foundByAuthor = storage.findByAuthor(author);
        if (foundByAuthor.isEmpty()) {
            System.out.println("There is no books with author: " + author);
        }
        foundByAuthor.forEach(System.out::println);
    }

    public void searchBookByYear(int year) {
        List<Book> foundByYear = storage.findByYear(year);
        if (foundByYear.isEmpty()) {
            System.out.println("There is no books with author: " + year);
        }
        foundByYear.forEach(System.out::println);
    }

    public void findAllBooks() {
        List<Book> foundAll = storage.findAll();
        if (foundAll.isEmpty()) {
            System.out.println("Library is empty");
        }
        foundAll.forEach(System.out::println);
    }

    private void validation(Book book) {
        if (book.getAuthor().isBlank()) {
            throw new ValidationException("Property Author must not be empty.");
        }

        if (book.getIsbn().isBlank()) {
            throw new ValidationException("Property ISBN must not be empty.");
        }

        if (book.getYear() > Year.now().getValue()) {
            throw new ValidationException("Year must be in the past.");
        }

        if (book.getTitle().isBlank()) {
            throw new ValidationException("Property Title must not be empty.");
        }
        if (storage.getByIsbn(book.getIsbn()) != null) {
            throw new ValidationException("Book with ISBN = " + book.getIsbn() + " is already in Library");
        }
    }
}
