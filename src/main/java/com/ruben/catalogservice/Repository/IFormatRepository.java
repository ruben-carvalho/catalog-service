package com.ruben.catalogservice.Repository;

import com.ruben.catalogservice.Models.Format;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IFormatRepository extends JpaRepository<Format, Integer> {
    Format findByType(String type);
}
