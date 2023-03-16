package com.carasent.library.controller;

import com.carasent.library.model.Book;
import com.carasent.library.repository.BookRepository;
import com.carasent.library.service.BookService;
import com.carasent.library.web.dto.BookPartialUpdate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class OverdueBooksTest extends LibraryWithBooksTestBase {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookService bookService;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    int bookId;
    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        bookRepository.deleteAll();
        populateBooks(bookRepository);
        BookPartialUpdate bookPartialUpdate = new BookPartialUpdate();
        bookPartialUpdate.setBorrowedAt(new Date(0));
        Book book = bookService.findAll(Pageable.ofSize(1)).getContent().get(0);
        bookId = book.getId().intValue();
        bookService.partiallyUpdateBook(bookId, bookPartialUpdate);
    }

    @Test
    @WithMockUser(roles={"ADMIN"})
    void getOverdueBooks() throws Exception {
        mockMvc
                .perform(
                        get("/books/overdue").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number", is(0)))
                .andExpect(jsonPath("$.total_elements", is(1)))
                .andExpect(jsonPath("$.content.length()", is(1)))
                .andExpect(jsonPath("$.content[0].id", is(bookId)));
    }
}
