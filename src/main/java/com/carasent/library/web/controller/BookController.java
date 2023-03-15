package com.carasent.library.web.controller;

import com.carasent.library.model.Book;
import com.carasent.library.repository.BookRepository;
import com.carasent.library.web.dto.BookPartialUpdate;
import com.carasent.library.web.dto.EmptyDate;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


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

    @PatchMapping("/{book-id}")
    @ResponseStatus(HttpStatus.OK)
    public Book patchBook(@PathVariable("book-id") Integer bookId,
                          @RequestBody BookPartialUpdate patchUpdate){

        if(EmptyDate.VAL.equals(patchUpdate.getBorrowedAt())){
            Optional<Book> result = bookRepository.findById(bookId);
            if(result.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no such book for id:"+bookId);
            }
            return result.get();
        }
        return null;
    }
}
