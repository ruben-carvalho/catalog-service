package com.ruben.catalogservice.Repository;

import com.ruben.catalogservice.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;


@Repository
public interface IBookRepository extends JpaRepository<Book, Integer> {

    public Book findByTitle(String title);

    public Book findByOriginalTitle(String originalTitle);

    public Book findByIsbn(String isbn);

    public Book findBySynopsis(String synopsis);

    @Query("SELECT b FROM Book b WHERE b.availability.id = :availabilityId")
    Set<Book> findAllAvailableBooks(@Param("availabilityId") int availabilityId);

}
