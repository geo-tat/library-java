package org.example.storage;

import org.example.model.Book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryStorage {
    private final Map<String, Book> books = new HashMap<>();

    public void addBook(Book book) {
             books.put(book.getIsbn(), book);
    }

    public void deleteBook(String isbn) {
        books.remove(isbn);
    }

    public List<Book> findByTitle(String title) {
        return books.values().stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .toList();
    }

    public List<Book> findByYear(int year) {
        return books.values().stream()
                .filter(book -> book.getYear()== year)
                .toList();
    }

    public List<Book> findByAuthor(String author) {
        return books.values().stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .toList();
    }

    public List<Book> findAll() {
        return books.values().stream().toList();
    }

   public Book getByIsbn(String isbn) {
         return books.get(isbn);
    }
}
