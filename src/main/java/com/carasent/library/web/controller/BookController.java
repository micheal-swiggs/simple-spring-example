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

import java.time.temporal.ChronoUnit;
import java.util.Date;
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

        Optional<Book> result = bookRepository.findById(bookId);
        if(result.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no such book for id:"+bookId);
        }

        Book book = result.get();
        if(EmptyDate.VAL.equals(patchUpdate.getBorrowedAt())){
            return book;
        } else if (patchUpdate.getBorrowedAt() == null){
            // book has been returned
            book.setBorrowedAt(null);
            book.setDueBackBy(null);
            return bookRepository.save(book);
        } else {
            book.setBorrowedAt(patchUpdate.getBorrowedAt());
            // We simply add 1 day to get the due back date
            Date dueBackBy = new Date(patchUpdate.getBorrowedAt().toInstant().plus(1, ChronoUnit.DAYS).toEpochMilli());
            book.setDueBackBy(dueBackBy);
            return bookRepository.save(book);
        }
    }
}
