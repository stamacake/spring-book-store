package com.geekbrains.book.store;

import com.geekbrains.book.store.beans.Cart;
import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.services.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class CartTesting {
    @Autowired
    private Cart cart;

    @MockBean
    private BookService bookService;

    @Test
    public void addBook(){
        Mockito.doReturn( new Book(1L, "title", "description", new BigDecimal("777"), 1856)).when(bookService).findById(1L);
        cart.add(new Book());
        Assertions.assertEquals(1, cart.getOrderItemList().size());
    }

    @Test
    public void getBook(){
        Mockito.doReturn( new Book(1L, "title", "description", new BigDecimal("777"), 1856)).when(bookService).findById(1L);
        cart.add(new Book(1L, "title", "description", new BigDecimal("777"), 1856));
        Assertions.assertEquals("title", cart.getOrderItemList().get(0).getBook().getTitle());
    }

    @Test
    public void getBooks(){
        List<Book> books = Arrays.asList(
                new Book(1L, "t1", "d1", new BigDecimal("222"), 555),
                new Book(2L, "t2", "d2", new BigDecimal("333"), 666),
                new Book(3L, "t3", "d3", new BigDecimal("444"), 777)
        );
        Mockito.doReturn( books).when(bookService).findAll();
        cart.add(books.get(0));
        cart.add(books.get(1));
        cart.add(books.get(2));
        Assertions.assertEquals(3, cart.getOrderItemList().size());
    }

}
