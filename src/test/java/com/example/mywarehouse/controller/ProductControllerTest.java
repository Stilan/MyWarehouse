package com.example.mywarehouse.controller;

import com.example.mywarehouse.dto.ProductDto;
import com.example.mywarehouse.entity.Product;
import com.example.mywarehouse.mapper.ProductMapper;
import com.example.mywarehouse.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    private static final String URL = "/api/product";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private ProductMapper productMapper;

    @Test
    void createProduct() throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setName("Name");
        productDto.setArticle(12);
        productDto.setLastSalePrice(34);
        productDto.setLastPurchasePrice(45);
        productDto.setRemainingGoods(32);
        when(productService.create(any())).thenReturn(productDto);
        mvc.perform(post(URL + "/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Name",
                                  "article": 2343543,
                                  "lastPurchasePrice": 123,
                                  "lastSalePrice": 234,
                                  "remainingGoods": 12
                                }
                                """))
                .andExpect(jsonPath("$.name").value("Name"));

    }

    @Test
    void getProductById() throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setName("Name");
        productDto.setArticle(12);
        productDto.setLastSalePrice(34);
        productDto.setLastPurchasePrice(45);
        productDto.setRemainingGoods(32);
        Product product = new Product();
        product.setName("Name");
        product.setArticle(12);
        product.setLastSalePrice(34);
        product.setLastPurchasePrice(45);
        product.setRemainingGoods(32);
        when(productService.getProductById(any())).thenReturn(product);
        when(productMapper.toDto(product)).thenReturn(productDto);
        mvc.perform(get(URL + "/" + UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c23")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Name"));
    }

    @Test
    void deleteProduct() throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setName("Name");
        productDto.setArticle(12);
        productDto.setLastSalePrice(34);
        productDto.setLastPurchasePrice(45);
        productDto.setRemainingGoods(32);
        when(productService.deleteProduct(any())).thenReturn(productDto);
        mvc.perform(delete(URL + "/delete/" + UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c23")))
                .andExpect(status().isOk());

    }
}