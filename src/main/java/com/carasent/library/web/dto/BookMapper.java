package com.carasent.library.web.dto;

import com.carasent.library.model.Book;

public class BookMapper {
    public static BookResponse mapToResponse(Book book) {
        return new BookResponse(book.getId(),
                                book.getTitle(),
                                book.getAuthor(),
                                book.getCondition(),
                                book.getDueBackBy(),
                                book.getYearOfPurchase(),
                                book.getBorrowedAt());
    }

    public static Book mapToEntity(BookRequest bookRequest){
        Book book = new Book();
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setYearOfPurchase(bookRequest.getYearOfPurchase());
        book.setCondition(bookRequest.getCondition());
        return book;
    }
}
