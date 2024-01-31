package com.ruben.catalogservice.Service.Implementation;

import br.com.fluentvalidator.Validator;
import br.com.fluentvalidator.context.ValidationResult;
import com.ruben.catalogservice.CrossCutting.Requests.CreateBookRequest;
import com.ruben.catalogservice.CrossCutting.Requests.DeleteBookRequest;
import com.ruben.catalogservice.Models.Book;
import com.ruben.catalogservice.Models.Enums.AvailabilityStatus;
import com.ruben.catalogservice.Repository.*;
import com.ruben.catalogservice.Service.DTO.BookDTO;
import com.ruben.catalogservice.CrossCutting.Requests.GetAllBooksRequest;
import com.ruben.catalogservice.Service.Interface.IBookService;
import com.ruben.catalogservice.Service.Mappers.IBookMapper;
import com.ruben.catalogservice.Service.Validators.Exceptions.BookValidationException;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.*;

@Service
public class BookService implements IBookService {
    private final IBookRepository bookRepository;
    private final IAvailabilityRepository availabilityRepository;
    private final IBookMapper bookMapper;
    private final Validator<CreateBookRequest> validator;
    Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    public BookService(
            IBookRepository bookRepository,
            IAvailabilityRepository availabilityRepository,
            IBookMapper bookMapper,
            Validator<CreateBookRequest> validator) {

        this.bookRepository = bookRepository;
        this.availabilityRepository = availabilityRepository;
        this.bookMapper = bookMapper;
        this.validator = validator;
    }

    public BookDTO getBookById(int id){
        var book = this.bookRepository.findById(id);
        if (book.isPresent()) {
            return bookMapper.toDto(book.get());
        } else {
            throw new EntityNotFoundException("Book with ID " + id + " not found");
        }
    }

    public BookDTO getBookByTitle(String title){
        var book = this.bookRepository.findByTitle(title);
        if (book != null) {
            return bookMapper.toDto(book);
        } else {
            throw new EntityNotFoundException("Book with title " + title + " not found");
        }
    }

    public BookDTO getBookByIsbn(String isbn){
        var book = this.bookRepository.findByIsbn(isbn);
        if (book != null) {
            return bookMapper.toDto(book);
        } else {
            throw new EntityNotFoundException("Book with isbn " + isbn + " not found");
        }
    }

    public BookDTO getBookBySynopsis(String synopsis){
        var book = this.bookRepository.findBySynopsis(synopsis);
        if (book != null) {
            return bookMapper.toDto(book);
        } else {
            throw new EntityNotFoundException("Book with synopsis " + synopsis + " not found");
        }
    }

    public Set<BookDTO> getBooksAvailable(){
        var availableStatusId = this.availabilityRepository.findByStatus(AvailabilityStatus.Available.getAbbreviation()).getId();
        var availableBooks = this.bookRepository.findAllAvailableBooks(availableStatusId);

        if (availableBooks != null && !availableBooks.isEmpty()) {
            return bookMapper.toDto(availableBooks);
        } else {
            return new HashSet<>();
        }
    }

    public void deleteBook(DeleteBookRequest request){
        var optionalBook = this.bookRepository.findById(request.getBookId());

        if(optionalBook.isPresent()){
            var book = optionalBook.get();
            this.updateBookAvailability(book, AvailabilityStatus.Unavailable.getAbbreviation());
            this.bookRepository.saveAndFlush(book);
        }
        else {
            logger.warn("Book with ID"+ request.getBookId() + "does not exist, therefore cannot be deleted");
        }
    }

    public void updateBookAvailability(Book book, String availability){
        var unavailableStatus = this.availabilityRepository.findByStatus(availability);
        book.setAvailability(unavailableStatus);
    }

    public List<BookDTO> getAllBooks(GetAllBooksRequest request, Pageable pageable){
            return Collections.emptyList();
    }


    public void saveBook(CreateBookRequest request){
        final ValidationResult result = this.validator.validate(request);

        if(!result.isValid()){
            result.isInvalidThrow(BookValidationException.class);
        }

        var book = bookMapper.toEntity(request);

        this.bookRepository.saveAndFlush(book);
    }
}
