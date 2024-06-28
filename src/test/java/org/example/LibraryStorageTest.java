package org.example;

import org.example.model.Book;
import org.example.storage.LibraryStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibraryStorageTest {
    private LibraryStorage libraryStorage;
    private Book book1;
    private Book book2;
    private Book book3;

    @BeforeEach
    void setUp() {
        libraryStorage = new LibraryStorage();
        book1 = Book.builder()
                .title("Title1")
                .author("Author1")
                .year(1990)
                .isbn("ISBN1")
                .build();
        book2 = Book.builder()
                .title("Title2")
                .author("Author2")
                .year(1990)
                .isbn("ISBN2")
                .build();
        book3 = Book.builder()
                .title("Title3")
                .author("Author1")
                .year(1991)
                .isbn("ISBN3")
                .build();

        libraryStorage.addBook(book1);
        libraryStorage.addBook(book2);
        libraryStorage.addBook(book3);
    }

    @Test
    void testAddBook() {
        Book book = new Book("Author3", "Title4", 2003, "ISBN4");
        libraryStorage.addBook(book);
        assertEquals(book, libraryStorage.getByIsbn("ISBN4"));
    }

    @Test
    void testDeleteBook() {
        libraryStorage.deleteBook("ISBN1");
        assertNull(libraryStorage.getByIsbn("ISBN1"));
    }

    @Test
    void testFindByTitle() {
        List<Book> books = libraryStorage.findByTitle("Title3");
        assertEquals(1, books.size());
        assertEquals(book3, books.get(0));
    }

    @Test
    void testFindByYear() {
        List<Book> books = libraryStorage.findByYear(1991);
        assertEquals(1, books.size());
        assertEquals(book3, books.get(0));
    }

    @Test
    void testFindByAuthor() {
        List<Book> books = libraryStorage.findByAuthor("Author1");
        assertEquals(2, books.size());
        assertTrue(books.contains(book1));
        assertTrue(books.contains(book3));
    }

    @Test
    void testFindAll() {
        List<Book> books = libraryStorage.findAll();
        assertEquals(3, books.size());
        assertTrue(books.contains(book1));
        assertTrue(books.contains(book2));
        assertTrue(books.contains(book3));
    }

    @Test
    void testGetByIsbn() {
        Book book = libraryStorage.getByIsbn("ISBN1");
        assertEquals(book1, book);
    }
}
