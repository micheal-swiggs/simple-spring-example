package com.carasent.library.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Column(name = "author", nullable=false)
    private String author;

    @NotNull
    @Column(name = "year_of_purchase", nullable=false)
    private Short yearOfPurchase;

    @NotNull
    @Column(name = "condition", nullable = false)
    private String condition;

    @Column(name = "borrowed_at")
    Date borrowedAt;

    @Column(name = "due_back_by")
    Date dueBackBy;


    @JsonGetter("borrowed_at")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    Date getBorrowedAtJson(){
        return borrowedAt;
    }

    @JsonGetter("due_back_by")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    Date getDueBackBy(){
        return dueBackBy;
    }

    @JsonSetter("due_back_by")
    void setDueBackByJson(Object value){
        //this stops overriding of dueBackBy when deserializing rest calls
    }

}
