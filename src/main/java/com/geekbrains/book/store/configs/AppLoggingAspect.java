package com.geekbrains.book.store.configs;

import lombok.Getter;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@Getter
public class AppLoggingAspect {

        int bookService = 0;
        int orderService = 0;
        int userService = 0;

        @Before("execution(public * com.geekbrains.book.store.services.BookService.*(..))")
        public void beforeBookServiceMethod() {
            bookService++;
        }

        @Before("execution(public * com.geekbrains.book.store.services.OrderService.*(..))")
        public void beforeOrderServiceMethod() {
            orderService++;
        }

        @Before("execution(public * com.geekbrains.book.store.services.UserService.*(..))")
        public void beforeUserServiceMethod() {
            userService++;
        }
}
