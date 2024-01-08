package com.example.mywarehouse.controller;

import com.example.mywarehouse.dto.ProductDto;
import com.example.mywarehouse.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        return new ResponseEntity<>(productService.createProduct(productDto), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable UUID id) {
        ProductDto productDto = productService.getStockById(id);
        return ResponseEntity.ok(productDto);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable UUID id, @RequestBody ProductDto productDto) {
        return new ResponseEntity<>(productService.updateStock(id, productDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable UUID id) {
        return new ResponseEntity<>(productService.deleteStock(id), HttpStatus.OK);
    }
}
