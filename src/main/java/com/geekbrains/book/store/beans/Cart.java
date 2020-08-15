package com.geekbrains.book.store.beans;

import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.entities.OrderItem;
import com.geekbrains.book.store.entities.User;
import lombok.Data;
//import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;


import javax.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
@Scope(value = "session",  proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {

    private List<OrderItem> orderItemList = new ArrayList<>();
    private BigDecimal price;

    public void add(Book book){
        int check  = 0;
        System.out.println("add cart start");
        if(orderItemList.size()>0) {
            for (OrderItem o : orderItemList) {

                if (o.getBook().equals(book)) {
                    check = 1;
                    o.setCount(o.getCount() + 1);
                    o.setPrice(book.getPrice().multiply(BigDecimal.valueOf(o.getCount())));
                    System.out.println("+1");
                }
            }
        }
            if(check==0){
                orderItemList.add(new OrderItem(book));
                System.out.println("added");
            }

    }


    public Cart(){}
}
