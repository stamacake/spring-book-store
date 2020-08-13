package com.geekbrains.book.store.services;


import com.geekbrains.book.store.entities.OrderItem;
import com.geekbrains.book.store.repositories.BookRepository;
import com.geekbrains.book.store.repositories.OrderItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {
    private OrderItemRepository orderItemRepository;

    @Autowired
    public void setOrderItemRepository(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }
    public OrderItem saveOrUpdate(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
}
