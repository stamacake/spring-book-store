package com.geekbrains.book.store.services;

import com.geekbrains.book.store.beans.Cart;
import com.geekbrains.book.store.entities.OrderItem;
import com.geekbrains.book.store.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public Cart saveOrUpdate(Cart cart){
        return orderRepository.save(cart);
    }
}
