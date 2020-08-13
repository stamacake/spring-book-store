package com.geekbrains.book.store.controllers;

import com.geekbrains.book.store.beans.Cart;
import com.geekbrains.book.store.entities.OrderItem;
import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.entities.User;
import com.geekbrains.book.store.repositories.OrderItemRepository;
import com.geekbrains.book.store.services.BookService;
import com.geekbrains.book.store.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class CartController {
    private Cart cart;
    private BookService bookService;
    private UserService userService;
    private OrderItemRepository orderItemRepository;

    @GetMapping("/tocart")
    public String addToCart(@RequestParam(name = "id") Long id){
        System.out.println("add");
        System.out.println(cart);
        OrderItem orderItem = null;
            for (OrderItem o : cart.getOrderItemList()) {
                System.out.println(o.getId()+"  id");
                if (o.getId() == id-1) {
                    System.out.println("add "+o.getId());
                    o.setCount(o.getCount() + 1);
                    orderItem = o;

                }

                System.out.println(o.getPrice());
            }
        System.out.println("id::"+id);
        if (orderItem==null ){
            System.out.println("null");
            OrderItem orderItem1 =new OrderItem(id-1, 1, bookService.findById(id).getPrice().longValue());
            cart.getOrderItemList().add(orderItem1);
        }



        System.out.println("end");
        return "redirect:/books";
    }

    @GetMapping("/cart")
    public String showCart(Principal principal, Model model){
        model.addAttribute("books", cart.getOrderItemList());
        User user = userService.findByUsername(principal.getName()).get();
        model.addAttribute("userid", user.getId());

        List<Book> books = bookService.findAll();
        model.addAttribute("allbooks", books);
        return "cart-page";
    }
}
