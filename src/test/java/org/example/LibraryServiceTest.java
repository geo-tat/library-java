package org.example;

import org.example.model.Book;
import org.example.service.LibraryService;
import org.example.storage.LibraryStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class LibraryServiceTest {
    @Mock
    private LibraryStorage libraryStorage;

    @InjectMocks
    private LibraryService libraryService;

    private Book validBook;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        validBook = new Book("Valid Author", "Valid Title", 2000, "Valid ISBN");
    }

    @Test
    void testAddBookValid() {
        libraryService.addBook(validBook);
        verify(libraryStorage, times(1)).addBook(validBook);
    }

    @Test
    void testAddInvalidBook() {
        Book invalidBook = Book.builder()
                .title("")
                .author("author")
                .year(1990)
                .isbn("fail")
                .build();

        libraryService.addBook(invalidBook);
        verify(libraryStorage, never()).addBook(invalidBook);
    }

    @Test
    void testDeleteBookValid() {
        when(libraryStorage.getByIsbn("Valid ISBN")).thenReturn(validBook);
        libraryService.deleteBook("Valid ISBN");
        verify(libraryStorage, times(1)).deleteBook("Valid ISBN");
    }

    @Test
    void testDeleteBookNotFound() {
        String nonExistingIsbn = "fail";
        when(libraryStorage.getByIsbn("Invalid ISBN")).thenReturn(null);
        libraryService.deleteBook("Invalid ISBN");

        verify(libraryStorage, never()).deleteBook(nonExistingIsbn);
    }

    @Test
    void testSearchBookByTitle() {
        List<Book> books = new ArrayList<>();
        books.add(validBook);
        when(libraryStorage.findByTitle("Valid Title")).thenReturn(books);
        libraryService.searchBookByTitle("Valid Title");
        verify(libraryStorage, times(1)).findByTitle("Valid Title");
    }

    @Test
    void testSearchBookByAuthor() {
        List<Book> books = new ArrayList<>();
        books.add(validBook);
        when(libraryStorage.findByAuthor("Valid Author")).thenReturn(books);
        libraryService.searchBookByAuthor("Valid Author");
        verify(libraryStorage, times(1)).findByAuthor("Valid Author");
    }

    @Test
    void testSearchBookByYear() {
        List<Book> books = new ArrayList<>();
        books.add(validBook);
        when(libraryStorage.findByYear(2000)).thenReturn(books);
        libraryService.searchBookByYear(2000);
        verify(libraryStorage, times(1)).findByYear(2000);
    }

    @Test
    void testFindAllBooks() {
        List<Book> books = new ArrayList<>();
        books.add(validBook);
        when(libraryStorage.findAll()).thenReturn(books);
        libraryService.findAllBooks();
        verify(libraryStorage, times(1)).findAll();
    }
}
