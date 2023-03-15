package com.carasent.library.controller;

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

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class CreateBookTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;


    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    @Test
    void createBookExample() throws Exception {
        Map<String, String> book = new LinkedHashMap<>();
        book.put("title", "Test Title");
        book.put("author", "Harry Poters");
        book.put("year_of_purchase", "2023");
        book.put("condition", "USED");

        mockMvc
                .perform(
                        post("/books").contentType(MediaType.APPLICATION_JSON).content(
                                objectMapper.writeValueAsString(book)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.title", is("Test Title")))
                .andExpect(jsonPath("$.author", is("Harry Poters")))
                .andExpect(jsonPath("$.condition", is("USED")))
                .andExpect(jsonPath("$.year_of_purchase", is(2023)));
    }

    @Test
    void bookBadRequest_NoTitle() throws Exception {
        Map<String, String> book = new LinkedHashMap<>();
        book.put("author", "Harry Poters");
        book.put("year_of_purchase", "2023");
        book.put("condition", "USED");

        mockMvc
                .perform(
                        post("/books").contentType(MediaType.APPLICATION_JSON).content(
                                objectMapper.writeValueAsString(book)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void bookBadRequest_NoAuthor() throws Exception {
        Map<String, String> book = new LinkedHashMap<>();
        book.put("title", "Test Title");
        book.put("year_of_purchase", "2023");
        book.put("condition", "USED");

        mockMvc
                .perform(
                        post("/books").contentType(MediaType.APPLICATION_JSON).content(
                                objectMapper.writeValueAsString(book)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void bookBadRequest_MissingYearOfPurchase() throws Exception {
        Map<String, String> book = new LinkedHashMap<>();
        book.put("title", "Test Title");
        book.put("author", "Harry Poters");
        book.put("condition", "USED");

        mockMvc
                .perform(
                        post("/books").contentType(MediaType.APPLICATION_JSON).content(
                                objectMapper.writeValueAsString(book)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void bookBadRequest_MissingCondition() throws Exception {
        Map<String, String> book = new LinkedHashMap<>();
        book.put("title", "Test Title");
        book.put("author", "Harry Poters");
        book.put("year_of_purchase", "2023");

        mockMvc
                .perform(
                        post("/books").contentType(MediaType.APPLICATION_JSON).content(
                                objectMapper.writeValueAsString(book)))
                .andExpect(status().isBadRequest());
    }
}