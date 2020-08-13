package com.geekbrains.book.store.controllers;

import com.geekbrains.book.store.beans.Cart;
import com.geekbrains.book.store.entities.User;
import com.geekbrains.book.store.repositories.OrderItemRepository;
import com.geekbrains.book.store.repositories.OrderRepository;
import com.geekbrains.book.store.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class OrderController {
    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;
    private UserService userService;
    private Cart cart;

    @GetMapping("/order")
    public String createOrder(Principal principal){

        orderItemRepository.saveAll(cart.getOrderItemList());
        User user = userService.findByUsername(principal.getName()).get();
        cart.setUser(user);
        orderRepository.save(cart);
        return "confirm-page";
    }
}
