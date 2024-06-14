package org.bookshopsystem;

import org.bookshopsystem.data.repositories.AuthorRepository;
import org.bookshopsystem.data.repositories.BookRepository;
import org.bookshopsystem.service.AuthorService;
import org.bookshopsystem.service.BookService;
import org.bookshopsystem.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final AuthorService authorService;
    private final BookService bookService;
    private final CategoryService categoryService;

    public CommandLineRunnerImpl(AuthorService authorService, BookService bookService, CategoryService categoryService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();
//        getAllBooksAfter2000Year();
//        getAuthorsFirstLastNameBeforeBooks1990();
//        getAllAuthorsByBooksDesc();
        printBooksByGeorgePowell();
    }

    private void seedData() throws IOException {
        this.categoryService.seedCategories();
        this.authorService.seedAuthors();
        this.bookService.seedBooks();
    }

    private void getAllBooksAfter2000Year() {
        this.bookService.findAllBooksAfterYear2000()
                .forEach(System.out::println);
    }

    private void getAuthorsFirstLastNameBeforeBooks1990() {
        this.authorService.getAllAuthorsFirstAndLastNameForBooksBeforeDateYear1990()
                .forEach(System.out::println);
    }

    private void getAllAuthorsByBooksDesc() {
        this.authorService.getAllAuthorsDescBooks()
                .forEach(System.out::println);
    }

    private void printBooksByGeorgePowell() {
        this.bookService.findAllBooksByGeorgePowellOrdered()
                .forEach(System.out::println);
    }

}
