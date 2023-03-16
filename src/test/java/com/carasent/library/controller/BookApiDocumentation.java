package com.carasent.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(RestDocumentationExtension.class)
public class BookApiDocumentation {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;


    @BeforeEach
    public void setUp(RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(restDocumentation)).build();
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
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
                .andDo(document("book-create",
                        requestFields(
                                fieldWithPath("title").description("The title of the book"),
                                fieldWithPath("author").description("The author of the book"),
                                fieldWithPath("year_of_purchase").description("The year when the book was purchased"),
                                fieldWithPath("condition").description("The current condition of the book NEW or USED"))));
    }
}
