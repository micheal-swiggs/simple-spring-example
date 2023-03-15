package com.carasent.library.controller;

import com.carasent.library.model.Book;
import com.carasent.library.repository.BookRepository;

public class LibraryWithBooksTestBase {


    protected boolean booksPopulated = false;
    public void populateBooks(BookRepository bookRepository){

        if (booksPopulated) return;
        for(short i=0; i<30; i++){
            Book book = new Book();
            book.setAuthor("Author"+i);
            book.setTitle("Title"+i);
            book.setCondition("NEW");
            book.setYearOfPurchase(i);
            bookRepository.save(book);
        }

        booksPopulated = true;
    }
}
