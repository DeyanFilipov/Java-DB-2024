package org.bookshopsystem.data.repositories;

import org.bookshopsystem.data.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    //FROM Authors a JOIN a.books b ORDER BY b DESC
    Set<Author> findAllByBooksReleaseDateBefore(LocalDate releaseDate);

    //FROM Authors a ORDER BY a.books.size DESC
    @Query("FROM Author a ORDER BY SIZE(a.books) DESC")
    Set<Author> findAllByOrderByBooksSizeDesc();
}
