package com.example.springintro.repository;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType type, int copies);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal priceLow, BigDecimal priceHigh);

    List<Book> findAllByReleaseDateLessThanOrReleaseDateGreaterThan(LocalDate start, LocalDate end);

    List<Book> findAllByReleaseDateLessThan(LocalDate date);

    List<Book> findAllByTitleContaining(String needle);

    List<Book> findALlByAuthorLastNameStartingWith(String lastName);

    @Query("SELECT COUNT(b) FROM Book b WHERE LENGTH(b.title) > :min")
    int countByTitleLengthGreaterThan(int min);

    BookInfo findByTitle(String title);

    @Modifying
    @Query("UPDATE Book AS b SET b.copies = b.copies + :cnt WHERE b.releaseDate > :date")
    Integer increaseCopiesForBooksReleasedAfterDate(@Param("date") LocalDate startDate, @Param("cnt") int copiesToAdd);

    @Modifying
    @Query("DELETE FROM Book AS b WHERE b.copies < :copies")
    Integer removeBooksWithCopiesLessThan(@Param("copies") int minCopies);
}
