package com.ruben.catalogservice.Repository;

import com.ruben.catalogservice.Models.Author;
import com.ruben.catalogservice.Models.Book;
import com.ruben.catalogservice.Models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthorRepository extends JpaRepository<Author, Integer> {
    Author findByName(String name);
}
