package com.carasent.library.controller;

import com.carasent.library.repository.BookRepository;
import com.carasent.testcontainers.EnablePostgresContainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.security.test.context.support.WithMockUser;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@EnablePostgresContainer
public class ListBooksTest extends LibraryWithBooksTestBase {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        populateBooks(bookRepository);
    }
    @Test
    @WithMockUser
    void getBooks() throws Exception {
        mockMvc
                .perform(
                        get("/books").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number", is(0)))
                .andExpect(jsonPath("$.total_elements", is(30)))
                .andExpect(jsonPath("$.total_pages", is(3)))
                .andExpect(jsonPath("$.size", is(10)))
                .andExpect(jsonPath("$.content.length()", is(10)));
    }

    @Test
    @WithMockUser
    void getBookPages() throws Exception {
        mockMvc
                .perform(
                        get("/books?page=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number", is(1)));

        mockMvc
                .perform(
                        get("/books?page=2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number", is(2)));

        mockMvc
                .perform(
                        get("/books?page=3").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number", is(3)));

        mockMvc
                .perform(
                        get("/books?page=4").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()", is(0)));
    }
}
