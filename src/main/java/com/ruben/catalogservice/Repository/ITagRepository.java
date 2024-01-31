package com.ruben.catalogservice.Repository;

import com.ruben.catalogservice.Models.Format;
import com.ruben.catalogservice.Models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ITagRepository extends JpaRepository<Tag, Integer> {
    Tag findByName(String name);
}

