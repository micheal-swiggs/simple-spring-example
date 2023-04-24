package com.carasent.library.web.controller;

import com.carasent.library.model.Book;
import com.carasent.library.service.BookService;
import com.carasent.library.web.dto.BookPartialUpdate;
import com.carasent.library.web.dto.BookRequest;
import com.carasent.library.web.dto.BookResponse;
import com.carasent.library.web.dto.PageResponse;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.carasent.library.web.dto.BookMapper.mapToEntity;
import static com.carasent.library.web.dto.BookMapper.mapToResponse;
import static com.carasent.library.web.dto.PageMapper.mapToResponse;


@RestController
@RequestMapping("books")
public class BookController {

    @Autowired
    BookService bookService;

    @RolesAllowed({"ADMIN"})
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponse createBook(@Valid @RequestBody BookRequest newBook) {
        Book book = bookService.save(mapToEntity(newBook));
        return mapToResponse(book);
    }

    @RolesAllowed({"ADMIN", "USER"})
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public PageResponse<BookResponse> getBooks(@RequestParam(name = "page", defaultValue = "0") int pageNumber,
                                               @RequestParam(name = "size", defaultValue = "10") int pageSize) {
        Page<Book> pageBook = bookService.findAll(Pageable.ofSize(pageSize).withPage(pageNumber));
        return mapToResponse(pageBook);
    }

    @RolesAllowed({"ADMIN", "USER"})
    @PatchMapping("/{book-id}")
    @ResponseStatus(HttpStatus.OK)
    public BookResponse patchBook(@PathVariable("book-id") Integer bookId,
                                  @RequestBody BookPartialUpdate patchUpdate) {

        Book book = bookService.partiallyUpdateBook(bookId, patchUpdate);
        return mapToResponse(book);
    }

    @RolesAllowed({"ADMIN", "USER"})
    @GetMapping("/overdue")
    @ResponseStatus(HttpStatus.OK)
    public PageResponse<BookResponse> getOverdueBooks(@RequestParam(name = "page", defaultValue = "0") int pageNumber,
                                                      @RequestParam(name = "size", defaultValue = "10") int pageSize) {
        Pageable pageable = Pageable.ofSize(pageSize).withPage(pageNumber);
        Page<Book> overdueBooks = bookService.findOverdueBooks(pageable);
        return mapToResponse(overdueBooks);
    }
}
