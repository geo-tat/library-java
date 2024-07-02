package org.example;

import org.example.model.Book;
import org.example.service.LibraryService;
import org.example.storage.LibraryStorage;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LibraryApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final LibraryStorage libraryStorage = new LibraryStorage();
    private static final LibraryService libraryService = new LibraryService(libraryStorage);

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Search");
            System.out.println("4. Display All Books");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addBookMenu();
                    break;
                case 2:
                    deleteBookMenu();
                    break;
                case 3:
                    searchMenu();
                    break;
                case 4:
                    libraryService.findAllBooks();
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addBookMenu() {
        try {
            System.out.print("Enter title: ");
            String title = scanner.nextLine();
            System.out.print("Enter author: ");
            String author = scanner.nextLine();
            System.out.print("Enter year: ");
            int year = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter ISBN: ");
            String isbn = scanner.nextLine();

            Book book = Book.builder()
                    .author(author)
                    .title(title)
                    .year(year)
                    .isbn(isbn)
                    .build();
            libraryService.addBook(book);
        } catch (
                InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
            scanner.nextLine();
        }
    }

    private static void deleteBookMenu() {
        System.out.print("Enter ISBN of the book to remove: ");
        String isbn = scanner.nextLine();
        libraryService.deleteBook(isbn);
    }

    private static void searchMenu() {
        while (true) {
            System.out.println("1. Search Book by Title");
            System.out.println("2. Search Book by Author");
            System.out.println("3. Search Book by Year");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice2 = scanner.nextInt();
            scanner.nextLine();

            switch (choice2) {
                case 1:
                    System.out.print("Enter title: ");
                   String title = scanner.nextLine();
                    libraryService.searchBookByTitle(title);
                    break;
                case 2:
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    libraryService.searchBookByAuthor(author);
                    break;
                case 3:
                    try {
                        System.out.print("Enter year: ");
                        int year = scanner.nextInt();
                        libraryService.searchBookByYear(year);
                        break;
                    }  catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please try again.");
                        scanner.nextLine();
                    }
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
