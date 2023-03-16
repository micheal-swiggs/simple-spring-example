package com.carasent.library.service;

import com.carasent.library.model.Book;
import com.carasent.library.repository.BookRepository;
import com.carasent.library.web.dto.BookPartialUpdate;
import com.carasent.library.web.dto.EmptyDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

@Service
public class BookService {

    final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book save(Book newBook) {
        return bookRepository.save(newBook);
    }

    public Page<Book> findOverdueBooks(Pageable pageable) {
        return bookRepository.findByDueBackByBefore(new Date(), pageable);
    }


    public Book partiallyUpdateBook(Integer bookId, BookPartialUpdate bookUpdate){

        Optional<Book> result = bookRepository.findById(bookId);
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no such book for id:" + bookId);
        }

        Book book = result.get();
        if (EmptyDate.VAL.equals(bookUpdate.getBorrowedAt())) {
            return book;
        } else if (bookUpdate.getBorrowedAt() == null) {
            // book has been returned
            book.setBorrowedAt(null);
            book.setDueBackBy(null);
            return bookRepository.save(book);
        } else {
            book.setBorrowedAt(bookUpdate.getBorrowedAt());
            // We simply add 1 day to get the due back date
            Date dueBackBy = new Date(bookUpdate.getBorrowedAt().toInstant().plus(1, ChronoUnit.DAYS).toEpochMilli());
            book.setDueBackBy(dueBackBy);
            return bookRepository.save(book);
        }
    }

    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }
}
