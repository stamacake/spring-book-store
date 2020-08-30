package com.geekbrains.book.store;

import com.geekbrains.book.store.configs.SecurityConfig;
import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.services.BookService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class RestTesting {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @Test
    public void getBooks() throws Exception {
        List<Book> books = Arrays.asList(
                new Book(1L, "t1", "d1", new BigDecimal("222"), 555),
                new Book(2L, "t2", "d2", new BigDecimal("333"), 666),
                new Book(3L, "t3", "d3", new BigDecimal("444"), 777)
        );


        given(bookService.findAll()).willReturn(books);
        mvc.perform(get("/api/v1/books")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(3)));

    }

    @Test
    public void getBook() throws Exception {
        List<Book> books = Arrays.asList(
                new Book(1L, "t1", "d1", new BigDecimal("222"), 555),
                new Book(2L, "t2", "d2", new BigDecimal("333"), 666),
                new Book(3L, "t3", "d3", new BigDecimal("444"), 777)
        );


        given(bookService.findById(1L)).willReturn(books.get(0));
        mvc.perform(get("/api/v1/books/1")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title",is(books.get(0).getTitle())));

    }

    @Test
    public void addBook() throws Exception {
        String bookInJson = "{\"id\":\"1\"}";

        mvc.perform(post("/api/v1/books")
                .content(bookInJson)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());

    }


}
