package com.ruben.catalogservice.Repository;

import com.ruben.catalogservice.Models.Availability;
import com.ruben.catalogservice.Models.Format;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IAvailabilityRepository extends JpaRepository<Availability, Integer> {
    Availability findByStatus(String status);
}

