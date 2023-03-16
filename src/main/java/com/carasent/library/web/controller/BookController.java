package com.carasent.library.web.controller;

import com.carasent.library.model.Book;
import com.carasent.library.service.BookService;
import com.carasent.library.web.dto.BookPartialUpdate;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("books")
public class BookController {

    @Autowired
    BookService bookService;

    @RolesAllowed({"ADMIN"})
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@Valid @RequestBody Book newBook) {
        return bookService.save(newBook);
    }

    @RolesAllowed({"ADMIN", "USER"})
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public Page<Book> getBooks(@RequestParam(name = "page", defaultValue = "0") int pageNumber,
                               @RequestParam(name = "size", defaultValue = "10") int pageSize) {
        return bookService.findAll(Pageable.ofSize(pageSize).withPage(pageNumber));
    }

    @RolesAllowed({"ADMIN", "USER"})
    @PatchMapping("/{book-id}")
    @ResponseStatus(HttpStatus.OK)
    public Book patchBook(@PathVariable("book-id") Integer bookId,
                          @RequestBody BookPartialUpdate patchUpdate) {

        return bookService.partiallyUpdateBook(bookId, patchUpdate);
    }

    @RolesAllowed({"ADMIN", "USER"})
    @GetMapping("/overdue")
    @ResponseStatus(HttpStatus.OK)
    public Page<Book> getOverdueBooks(@RequestParam(name = "page", defaultValue = "0") int pageNumber,
                                      @RequestParam(name = "size", defaultValue = "10") int pageSize) {
        Pageable pageable = Pageable.ofSize(pageSize).withPage(pageNumber);
        return bookService.findOverdueBooks(pageable);
    }
}
