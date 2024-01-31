package com.ruben.catalogservice.Service.Implementation;

import com.ruben.catalogservice.Models.Author;
import com.ruben.catalogservice.Models.Book;
import com.ruben.catalogservice.Repository.IAuthorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {
    private final IAuthorRepository authorRepository;

    public AuthorService(IAuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Optional<Author> getAuthorById(int id){
        return this.authorRepository.findById(id);
    }
}
