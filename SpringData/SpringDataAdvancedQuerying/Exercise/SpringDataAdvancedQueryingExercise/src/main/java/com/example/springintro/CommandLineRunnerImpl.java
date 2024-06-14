package com.example.springintro;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import com.example.springintro.repository.BookInfo;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();
//        ex1
//        printBooksByAgeRestriction();
//        ex2
//        printGoldenBooksWithLessThan5000Copies();
//        ex3
//        printBooksWithPriceOutOfRange();
//        ex4
//        printBooksNotIssuedAt();
//        ex5
//        printBookInfoForBooksReleasedBefore();
//        ex6
//        printAuthorsEndingIn();
//        ex7
//        printBookTitleContaining();
//        ex8
//        findAllBooksByLastNameStarting();
//        ex9
//        findStatsForTitleLength();
//        ex10
//        printTotalBookCopiesForAuthor();
//        ex11
//        printBookProjection();
//        ex12
        Scanner scanner = new Scanner(System.in);
        String date = scanner.nextLine();
        int amount = Integer.parseInt(scanner.nextLine());
        int booksUpdated = this.bookService.addCopiesToBooksAfter(date, amount);
        System.out.printf("%s books are released after %s, so total of %d book copies were added", booksUpdated, date, amount);
//        ex13
        Scanner scanner = new Scanner(System.in);
        int amount = Integer.parseInt(scanner.nextLine());
        this.bookService.deleteWithCopiesLessThan(amount);
//        ex14
        Scanner scanner = new Scanner(System.in);
        String[] names = scanner.nextLine().split(" ");
        String firstName = names[0];
        String lastName = names[1];
        int count = this.bookService.callUspTotalBooks(firstName, lastName);
        System.out.printf("%s %s has written %d books%n", firstName, lastName, count);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }

    private void printBooksByAgeRestriction() {
        Scanner scanner = new Scanner(System.in);
        String restriction = scanner.nextLine();

        try {

            AgeRestriction ageRestriction =
                    AgeRestriction.valueOf(restriction.toUpperCase());

            List<String> titles = bookService.findAllTitlesByAgeRestriction(ageRestriction);

            titles.forEach(System.out::println);
        } catch (IllegalArgumentException ex) {
            System.out.println("Wrong age category");
            return;
        }
    }

    private void printGoldenBooksWithLessThan5000Copies() {
        List<String> titles = bookService.findAllTitlesByEditionAndCopies(EditionType.GOLD, 5000);
        titles.forEach(System.out::println);
    }

    private void printBooksWithPriceOutOfRange() {
        List<Book> books = bookService.findAllBooksWithPriceOutsideOf(5, 40);
        books.forEach(b -> System.out.printf("%s $%.2f\n", b.getTitle(), b.getPrice()));
    }

    private void printBooksNotIssuedAt() {
        List<String> titles = bookService.findTitlesForBooksNotPublishedIn(2000);
        titles.forEach(System.out::println);
    }

    private void printBookInfoForBooksReleasedBefore() {
        Scanner scanner = new Scanner(System.in);
        String beforeDate = scanner.nextLine();
        LocalDate parsedDate =
                LocalDate.parse(beforeDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        List<Book> books = bookService.findAllReleasedBefore(parsedDate);

        books.forEach(b -> System.out.printf("%s %s %.2f\n",
                b.getTitle(),
                b.getEditionType(),
                b.getPrice()));
    }

    private void printAuthorsEndingIn() {
        Scanner scanner = new Scanner(System.in);
        String ending = scanner.nextLine();

        List<String> names = authorService.findAllNamesEndingIn(ending);

        names.forEach(System.out::println);
    }

    private void printBookTitleContaining() {
        Scanner scanner = new Scanner(System.in);
        String needle = scanner.nextLine();

        List<String> titles = bookService.findTitlesContaining(needle);

        titles.forEach(System.out::println);
    }

    private void findAllBooksByLastNameStarting() {
        Scanner scanner = new Scanner(System.in);
        String lastNameStart = scanner.nextLine();

        List<String> titles = bookService.findAllTitlesForAuthorNameStartingWith(lastNameStart);

        titles.forEach(System.out::println);
    }

    private void findStatsForTitleLength() {
        Scanner scanner = new Scanner(System.in);
        int minLength = Integer.parseInt(scanner.nextLine());

        int count = bookService.findTitleCountLongerThan(minLength);

        System.out.printf("There are %d books with longer titles than %d symbols.", count, minLength);
    }

    private void printTotalBookCopiesForAuthor() {
        Scanner scanner = new Scanner(System.in);
        String[] authorName = scanner.nextLine().split(" ");

        int count = authorService.getTotalCopiesCountFor(authorName[0], authorName[1]);

        System.out.printf("%s %s %d",
                authorName[0], authorName[1], count);
    }


    private void printBookProjection() {
        Scanner scanner = new Scanner(System.in);
        String title = scanner.nextLine();

        BookInfo info = bookService.findInfoByTitle(title);

        System.out.printf("%s %s %s $%.2f", info.getTitle(), info.getEditionType(), info.getAgeRestriction(), info.getPrice());
    }
}
