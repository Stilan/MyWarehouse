package com.example.mywarehouse.controller;

import com.example.mywarehouse.dto.ProductDto;
import com.example.mywarehouse.mapper.ProductMapper;
import com.example.mywarehouse.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/product")
public class ProductController {


    private final ProductService productService;
    private final ProductMapper productMapper;

    @PostMapping("/create")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        ProductDto createProductDto = productService.create(productDto);
        return new ResponseEntity<>(createProductDto, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable UUID id) {
        ProductDto productDto = productMapper.toDto(productService.getProductById(id));
        return ResponseEntity.ok(productDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable UUID id) {
        ProductDto deleteProductDto = productService.deleteProduct(id);
        return new ResponseEntity<>(deleteProductDto, HttpStatus.OK);
    }

    /**
     * Добавить метод update
     */
}
