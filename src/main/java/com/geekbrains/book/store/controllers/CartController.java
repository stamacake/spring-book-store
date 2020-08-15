package com.geekbrains.book.store.controllers;

import com.geekbrains.book.store.beans.Cart;
import com.geekbrains.book.store.entities.OrderItem;
import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.entities.User;
import com.geekbrains.book.store.repositories.OrderItemRepository;
import com.geekbrains.book.store.services.BookService;
import com.geekbrains.book.store.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class CartController {
    private Cart cart;
    private BookService bookService;
    private UserService userService;

    @GetMapping("/tocart")
    public String addToCart(@RequestParam(name = "id") Long id){
        System.out.println("add");
        cart.add(bookService.findById(id));
        System.out.println("end");
        return "redirect:/books";
    }

    @GetMapping("/cart")
    public String showCart(Principal principal, Model model){
        model.addAttribute("books", cart.getOrderItemList());
        System.out.println("Books: "+cart.getOrderItemList());
        User user = userService.findByUsername(principal.getName()).get();
        model.addAttribute("userid", user.getId());

        List<Book> books = bookService.findAll();
        model.addAttribute("allbooks", books);
        return "cart-page";
    }
}
