package com.carasent.library.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.Getter;

import java.util.Date;

@Getter
public class BookResponse {

    public final long id;
    public final String title;
    public final String author;
    public final String condition;
    public final Date dueBackBy;
    public final Short yearOfPurchase;

    public final Date borrowedAt;


    public BookResponse(long id, String title, String author, String condition, Date dueBackBy, Short yearOfPurchase, Date borrowedAt) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.condition = condition;
        this.dueBackBy = dueBackBy;
        this.yearOfPurchase = yearOfPurchase;
        this.borrowedAt = borrowedAt;
    }

    @JsonGetter("due_back_by")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public Date getDueBackBy(){
        return dueBackBy;
    }

    @JsonGetter("borrowed_at")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    Date getBorrowedAtJson(){
        return borrowedAt;
    }
}
