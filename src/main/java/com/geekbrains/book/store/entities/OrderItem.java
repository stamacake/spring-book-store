package com.geekbrains.book.store.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.*;
import java.math.BigDecimal;


@Data
@Entity
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "count")
    private long count;

    @Column(name = "price")
    private long price;

    public OrderItem(long id, long count, long price) {
        this.id = id;
        this.count = count;
        this.price = price;
    }

    public OrderItem(){}
}
