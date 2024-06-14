package com.example.springintro.repository;

import com.example.springintro.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT a FROM Author a ORDER BY SIZE(a.books) DESC")
    List<Author> findAllByBooksSizeDESC();

    List<Author> findAllByFirstNameEndingWith(String end);

    @Query("SELECT SUM(b.copies) " +
            "FROM Author a " +
            "JOIN a.books b " +
            " WHERE a.firstName = :firstName AND a.lastName = :lastName")

    int countBookCopiesByAuthorName(String firstName, String lastName);

    @Procedure(name = "udp_find_books_by_author")
    Integer getAuthorBooksCount(@Param("first_name") String first_name, @Param("last_name") String last_name);
}
