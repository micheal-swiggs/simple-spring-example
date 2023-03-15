package com.carasent.library.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name="book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable=false)
    private String author;

    @Column(name = "year_of_purchase", nullable=false)
    private Short yearOfPurchase;

    @Column(name = "condition", nullable = false)
    private String condition;

}
