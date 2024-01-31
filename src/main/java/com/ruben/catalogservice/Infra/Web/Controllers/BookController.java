package com.ruben.catalogservice.Infra.Web.Controllers;

import com.ruben.catalogservice.CrossCutting.Requests.DeleteBookRequest;
import com.ruben.catalogservice.Infra.JobRunnr.CatalogJobScheduler;
import com.ruben.catalogservice.CrossCutting.Requests.CreateBookRequest;
import com.ruben.catalogservice.CrossCutting.Requests.GetAllBooksRequest;
import com.ruben.catalogservice.Service.DTO.BookDTO;
import com.ruben.catalogservice.Service.Interface.IBookService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/v1/api/books")
public class BookController {
    private final IBookService bookService;

    public BookController(IBookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/{isbn}")
    @PreAuthorize("hasRole('catalog-admin') || hasRole('catalog-user') || hasAuthority('book.read')")
    public ResponseEntity<BookDTO> getBookByIsbn(@RequestParam String isbn){
        var bookResponse = bookService.getBookByIsbn(isbn);

        return ResponseEntity.ok(bookResponse);
    }

    @GetMapping("/{title}")
    @PreAuthorize("hasRole('catalog-admin') || hasRole('catalog-user') || hasAuthority('book.read')")
    public ResponseEntity<BookDTO> getBookByTitle(@RequestParam String title){
        var bookResponse = bookService.getBookByTitle(title);

        return ResponseEntity.ok(bookResponse);
    }

    @GetMapping("/{synopsis}")
    @PreAuthorize("hasRole('catalog-admin') || hasRole('catalog-user') || hasAuthority('book.read')")
    public ResponseEntity<BookDTO> getBookBySynopsis(@RequestParam String synopsis){
        var bookResponse = bookService.getBookBySynopsis(synopsis);

        return ResponseEntity.ok(bookResponse);
    }

    @GetMapping("/getAvailableBooks")
    @PreAuthorize("hasRole('catalog-admin') || hasRole('catalog-user') || hasAuthority('book.read')")
    public ResponseEntity<Set<BookDTO>> getBooksAvailable(){
        var bookResponse = bookService.getBooksAvailable();

        return ResponseEntity.ok(bookResponse);
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('catalog-admin') || hasRole('catalog-user') || hasAuthority('book.read')")
    public ResponseEntity<BookDTO> getBook(@RequestParam int id){
        var bookResponse = bookService.getBookById(id);

        return ResponseEntity.ok(bookResponse);
    }

    @GetMapping("/getAllBooks")
    @PreAuthorize("hasRole('catalog-admin') || hasRole('catalog-user') || hasAuthority('book.read')")
    public ResponseEntity<List<BookDTO>> getAllBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String language,
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) String format,
            @RequestParam(required = false) Date releaseDate,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) boolean isSeries,
            Pageable pageable
            ){

        var request = new GetAllBooksRequest(
                title,
                language,
                isbn,
                format,
                releaseDate,
                genre,
                isSeries);

        var bookResponse = bookService.getAllBooks(request, pageable);

        return ResponseEntity.ok(bookResponse);
    }

    @PostMapping
    @PreAuthorize("hasRole('catalog-admin')")
    public ResponseEntity<String> createBook(@RequestBody CreateBookRequest bookDTO) {
        var scheduler = new CatalogJobScheduler();
        scheduler.SchedulePrint(5);

        try {
            bookService.saveBook(bookDTO);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('catalog-admin')")
    public ResponseEntity<String> deleteBook(@RequestParam int id) {
        bookService.deleteBook(new DeleteBookRequest(id));

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    public class ResourceNotFoundException extends RuntimeException {}
    @ControllerAdvice
    public class ResourceNotFoundExceptionHandler
    {
        @ExceptionHandler(ResourceNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public void handleResourceNotFound() {}
    }

    @ControllerAdvice
    public class EntityNotFoundExceptionHandler
    {
        @ExceptionHandler(EntityNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public void handleResourceNotFound() {}
    }
}
