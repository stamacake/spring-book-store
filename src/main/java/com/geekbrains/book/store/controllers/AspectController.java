package com.geekbrains.book.store.controllers;

import com.geekbrains.book.store.configs.AppLoggingAspect;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/showlogs")
@AllArgsConstructor
public class AspectController {
    private AppLoggingAspect appLoggingAspect;

    @GetMapping
    @ResponseBody
    public Map<String, Integer> getLogs() {
        Map<String, Integer> logs = new HashMap<>();
        logs.put("BookService", appLoggingAspect.getBookService());
        logs.put("OrderService", appLoggingAspect.getOrderService());
        logs.put("UserService", appLoggingAspect.getUserService());
        return logs;
    }
}