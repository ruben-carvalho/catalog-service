package com.ruben.catalogservice.Repository;

import com.ruben.catalogservice.Models.Format;
import com.ruben.catalogservice.Models.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ILanguageRepository extends JpaRepository<Language, Integer> {
    Language findByName(String name);
}

