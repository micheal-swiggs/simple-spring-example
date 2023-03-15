package com.carasent.library.web.controller;

import com.carasent.library.model.Book;
import com.carasent.library.repository.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("books")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@Valid @RequestBody Book newBook){
        return bookRepository.save(newBook);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Book> getBooks(){
        Iterable<Book> all = bookRepository.findAll();
        return all;
    }
}
