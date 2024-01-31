package com.ruben.catalogservice.Repository;

import com.ruben.catalogservice.Models.Format;
import com.ruben.catalogservice.Models.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IPublisherRepository extends JpaRepository<Publisher, Integer> {
    Publisher findByName(String name);
}

