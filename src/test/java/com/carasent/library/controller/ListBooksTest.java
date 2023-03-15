package com.carasent.library.controller;

import com.carasent.library.model.Book;
import com.carasent.library.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ListBooksTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;


    private boolean booksPopulated = false;
    public void populateBooks(){

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
    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        populateBooks();
    }

    @Test
    void getBooks() throws Exception {

        mockMvc
                .perform(
                        get("/books").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(30)));
    }
}
