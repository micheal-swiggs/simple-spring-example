package com.carasent.library.controller;

import com.carasent.library.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
public class BookController {

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book newBook){
        return newBook;
    }
}
