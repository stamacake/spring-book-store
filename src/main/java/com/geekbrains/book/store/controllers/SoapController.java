package com.geekbrains.book.store.controllers;

import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.services.BookService;
import com.stamacake.books.GetBookRequest;
import com.stamacake.books.GetBookResponse;
import com.stamacake.books.GetBooksResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;

@Endpoint
public class SoapController {
    private static final String NAMESPACE_URI = "http://stamacake.com/books";

    private BookService bookService;

    @Autowired
    public SoapController(BookService bookService){
        this.bookService = bookService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBookRequest")
    @ResponsePayload
    public GetBookResponse getBook(@RequestPayload GetBookRequest request){
        System.out.println("xxxx");
        GetBookResponse response = new GetBookResponse();
        Book book = bookService.findById((long) request.getId());
        com.stamacake.books.Book book2 = new com.stamacake.books.Book();
        book2.setId(book.getId().intValue());
        book2.setDescription(book.getDescription());
        book2.setPrice(book.getPrice().intValue());
        book2.setPublishYear(book.getPublishYear());
        book2.setTitle(book.getTitle());
        response.setBook(book2);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBooksRequest")
    @ResponsePayload
    public GetBooksResponse getBooks(){
        System.out.println("xxxx");
        GetBooksResponse response = new GetBooksResponse();
        List<Book> books = bookService.findAll();
        for(Book b:books){
            com.stamacake.books.Book tmp = new com.stamacake.books.Book();
            tmp.setTitle(b.getTitle());
            tmp.setPublishYear(b.getPublishYear());
            tmp.setPrice(b.getPrice().intValue());
            tmp.setDescription(b.getDescription());
            tmp.setId(b.getId().intValue());
            response.getBooks().add(tmp);
        }
        return response;
    }


}
