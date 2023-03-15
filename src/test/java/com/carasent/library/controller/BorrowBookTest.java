package com.carasent.library.controller;

import com.carasent.library.model.Book;
import com.carasent.library.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class BorrowBookTest extends LibraryWithBooksTestBase {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext context;

    @Autowired
    BookRepository bookRepository;

    private MockMvc mockMvc;


    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        populateBooks(bookRepository);
    }


    @Test
    void borrowBook() throws Exception {

        Map<String, String> patchUpdate = new LinkedHashMap<>();
        patchUpdate.put("borrowed_at", "2023-03-15 13:00:00");

        Book book = bookRepository.findById(1).get();
        mockMvc
                .perform(
                        patch("/books/"+book.getId()).contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(patchUpdate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.author", is(book.getAuthor())))
                .andExpect(jsonPath("$.title", is(book.getTitle())))
                .andExpect(jsonPath("$.condition", is(book.getCondition())))
                .andExpect(jsonPath("$.year_of_purchase", is(book.getYearOfPurchase())))
                .andExpect(jsonPath("$.borrowed_at", is("2023-03-15 13:00:00")))
                .andExpect(jsonPath("$.due_back_by", is("2023-03-16 13:00:00")));
    }

    @Test
    void returnBook() throws Exception {

        Map<String, String> patchUpdate = new LinkedHashMap<>();
        patchUpdate.put("borrowed_at", null);

        Book book = bookRepository.findById(1).get();
        mockMvc
                .perform(
                        patch("/books/"+book.getId()).contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(patchUpdate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.author", is(book.getAuthor())))
                .andExpect(jsonPath("$.title", is(book.getTitle())))
                .andExpect(jsonPath("$.condition", is(book.getCondition())))
                .andExpect(jsonPath("$.year_of_purchase", is(book.getYearOfPurchase())))
                .andExpect(jsonPath("$.borrowed_at", nullValue()))
                .andExpect(jsonPath("$.due_back_by", nullValue()));
    }

    @Test
    void noChangeUpdate() throws Exception {

        Map<String, String> patchUpdate = new LinkedHashMap<>();

        Book book = bookRepository.findById(1).get();
        mockMvc
                .perform(
                        patch("/books/"+book.getId()).contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(patchUpdate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.author", is(book.getAuthor())))
                .andExpect(jsonPath("$.title", is(book.getTitle())))
                .andExpect(jsonPath("$.condition", is(book.getCondition())))
                .andExpect(jsonPath("$.year_of_purchase", is(book.getYearOfPurchase())))
                .andExpect(jsonPath("$.borrowed_at", nullValue()))
                .andExpect(jsonPath("$.due_back_by", nullValue()));
    }
}
