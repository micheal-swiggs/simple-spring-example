package com.carasent.library.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequest {

    @NotNull
    private String title;

    @NotNull
    private String author;

    @NotNull
    private Short yearOfPurchase;

    @NotNull
    private String condition;

}
