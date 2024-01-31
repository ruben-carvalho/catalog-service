package com.ruben.catalogservice.Repository.Implementation;

import com.ruben.catalogservice.Models.Book;
import com.ruben.catalogservice.Models.Enums.AvailabilityStatus;
import com.ruben.catalogservice.Repository.IAvailabilityRepository;
import com.ruben.catalogservice.Repository.IBookRepository;
import com.ruben.catalogservice.Service.Mappers.IBookMapper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Set;

public abstract class BookRepository implements IBookRepository {
   private final IBookMapper bookMapper;
   private final IAvailabilityRepository availabilityRepository;

    public BookRepository(IBookMapper bookMapper,
                          IAvailabilityRepository availabilityRepository) {
        this.bookMapper = bookMapper;
        this.availabilityRepository = availabilityRepository;
    }


}
