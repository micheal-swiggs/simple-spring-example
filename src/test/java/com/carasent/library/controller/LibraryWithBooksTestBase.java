package com.carasent.library.controller;

import com.carasent.library.model.Book;
import com.carasent.library.repository.BookRepository;

public class LibraryWithBooksTestBase {

    protected Integer mostRecentId;

    public void populateBooks(BookRepository bookRepository){

        bookRepository.deleteAll();
        for(short i=0; i<30; i++){
            Book book = new Book();
            book.setAuthor("Author"+i);
            book.setTitle("Title"+i);
            book.setCondition("NEW");
            book.setYearOfPurchase(i);
            Book savedBook = bookRepository.save(book);
            mostRecentId = savedBook.getId().intValue();
        }

    }
}
