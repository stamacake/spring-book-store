package com.geekbrains.book.store.beans;

import com.geekbrains.book.store.entities.OrderItem;
import com.geekbrains.book.store.entities.User;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Component
//@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
@Entity
@Table(name = "orders")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private List<OrderItem> orderItemList = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
