package com.ruben.catalogservice.Repository;

import com.ruben.catalogservice.Models.Format;
import com.ruben.catalogservice.Models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IGenreRepository extends JpaRepository<Genre, Integer> {
    Genre findByName(String name);
}

