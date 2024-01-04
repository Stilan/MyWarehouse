package com.example.mywarehouse.controller;

import com.example.mywarehouse.entity.Product;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("api/product")
public class ProductController {

    @GetMapping("/{id}")
    public Product get(@PathVariable("id") UUID id) {
        return new Product();
    }
}
