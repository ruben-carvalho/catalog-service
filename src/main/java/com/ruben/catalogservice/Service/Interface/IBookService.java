package com.ruben.catalogservice.Service.Interface;

import com.ruben.catalogservice.CrossCutting.Requests.CreateBookRequest;
import com.ruben.catalogservice.CrossCutting.Requests.DeleteBookRequest;
import com.ruben.catalogservice.CrossCutting.Requests.GetAllBooksRequest;
import com.ruben.catalogservice.Service.DTO.BookDTO;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Set;

public interface IBookService {

    BookDTO getBookById(int id);
    BookDTO getBookByTitle(String title);
    BookDTO getBookByIsbn(String isbn);
    BookDTO getBookBySynopsis(String synopsis);
    List<BookDTO> getAllBooks(GetAllBooksRequest request, Pageable pageable);
    Set<BookDTO> getBooksAvailable();
    void saveBook(CreateBookRequest request);
    void deleteBook(DeleteBookRequest request);
}
