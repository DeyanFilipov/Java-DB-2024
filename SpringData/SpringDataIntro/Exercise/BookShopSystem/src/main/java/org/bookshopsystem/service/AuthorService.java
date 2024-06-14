package org.bookshopsystem.service;

import org.bookshopsystem.data.entities.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {

    void seedAuthors() throws IOException;

    Author getRandomAuthor();

    List<String> getAllAuthorsFirstAndLastNameForBooksBeforeDateYear1990();

    List<String> getAllAuthorsDescBooks();
}
