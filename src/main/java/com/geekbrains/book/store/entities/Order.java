package com.geekbrains.book.store.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.MERGE)
    private List<OrderItem> orderItems;

    @Column(name = "price")
    private BigDecimal price;

    public Order() {
    }

    public Order(User user, List<OrderItem> orderItems){
        this.user = user;
        this.orderItems = orderItems;
        BigDecimal totalPrice = BigDecimal.valueOf(0);
        for(OrderItem o:orderItems){
            totalPrice.add(o.getPrice());
        }
        price = totalPrice;
    }
}
