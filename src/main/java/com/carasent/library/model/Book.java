package com.carasent.library.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity(name="book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

}
