package com.example.springintro.service;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import com.example.springintro.repository.BookInfo;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    List<String> findAllTitlesByAgeRestriction(AgeRestriction ageRestriction);

    List<String> findAllTitlesByEditionAndCopies(EditionType type, int copies);

    List<Book> findAllBooksWithPriceOutsideOf(int lowerBound, int upperBound);

    List<String> findTitlesForBooksNotPublishedIn(int year);

    List<Book> findAllReleasedBefore(LocalDate date);

    List<String> findTitlesContaining(String needle);

    List<String> findAllTitlesForAuthorNameStartingWith(String lastNameStart);

    int findTitleCountLongerThan(int minLength);

    BookInfo findInfoByTitle(String title);

    int addCopiesToBooksAfter(String date, int amount);

    int deleteWithCopiesLessThan(int amount);

    int callUspTotalBooks(String firstName, String lastName);
}
