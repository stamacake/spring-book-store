package com.geekbrains.book.store.controllers;

import com.geekbrains.book.store.beans.Cart;
import com.geekbrains.book.store.entities.Order;
import com.geekbrains.book.store.entities.User;
import com.geekbrains.book.store.repositories.OrderItemRepository;
import com.geekbrains.book.store.repositories.OrderRepository;
import com.geekbrains.book.store.services.OrderService;
import com.geekbrains.book.store.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;

@Controller
@AllArgsConstructor
@Component
public class OrderController {
    private OrderService orderService;
    private OrderItemRepository orderItemRepository;
    private UserService userService;
    private Cart cart;


    @GetMapping("/order")
    public String createOrder(Principal principal){

        orderItemRepository.saveAll(cart.getOrderItemList());
        User user = userService.findByUsername(principal.getName()).get();
        Order order = new Order(user, cart.getOrderItemList());
        orderService.saveOrUpdate(order);
        cart.getOrderItemList().clear();
        return "confirm-page";
    }
}
