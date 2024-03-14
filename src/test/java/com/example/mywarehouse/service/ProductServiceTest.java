package com.example.mywarehouse.service;

import com.example.mywarehouse.dto.EntranceProductDto;
import com.example.mywarehouse.dto.MovingProductDto;
import com.example.mywarehouse.dto.MovingProductStockDto;
import com.example.mywarehouse.dto.ProductDto;
import com.example.mywarehouse.dto.ProductStockIdDto;
import com.example.mywarehouse.dto.SaleProductDto;
import com.example.mywarehouse.entity.Product;
import com.example.mywarehouse.mapper.EntranceMapper;
import com.example.mywarehouse.mapper.MovingProductStockMapper;
import com.example.mywarehouse.mapper.ProductMapper;
import com.example.mywarehouse.mapper.ProductStockIdMapper;
import com.example.mywarehouse.mapper.SaleMapper;
import com.example.mywarehouse.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private EntranceMapper entranceMapper;

    @Mock
    private ProductStockIdMapper productStockIdMapper;

    @Mock
    private ProductMapper productMapper;

    @Mock
    private SaleMapper saleMapper;

    @Mock
    private MovingProductStockMapper movingProductStockMapper;

    @Test
    void createProduct() {
        ProductStockIdDto productDocDto = new ProductStockIdDto();
        productDocDto.setName("Name");
        productDocDto.setArticle(123);
        productDocDto.setLastPurchasePrice(12);
        EntranceProductDto entranceProductDto = new EntranceProductDto();
        entranceProductDto.setName("Name");
        entranceProductDto.setArticle(123);
        entranceProductDto.setRemainingGoods(12);
        Product product = new Product();
        product.setName("Name");
        product.setArticle(123);
        product.setRemainingGoods(12);
        Mockito.when(productRepository.save(any())).thenReturn(product);
        Mockito.when(entranceMapper.toEntity(entranceProductDto)).thenReturn(product);
        Mockito.when(productStockIdMapper.toDto(product)).thenReturn(productDocDto);
        ProductStockIdDto dto = productService.createProduct(entranceProductDto);
        Assertions.assertEquals("Name",dto.getName());
    }

    @Test
    void create() {
        ProductDto productDto = new ProductDto();
        productDto.setName("Name");
        productDto.setArticle(123);
        productDto.setRemainingGoods(12);
        Product product = new Product();
        product.setName("Name");
        product.setArticle(123);
        product.setRemainingGoods(12);
        Mockito.when(productRepository.save(any())).thenReturn(product);
        Mockito.when(productMapper.toEntity(productDto)).thenReturn(product);
        Mockito.when(productMapper.toDto(product)).thenReturn(productDto);
        ProductDto productDtoResult = productService.create(productDto);
        assertThat(productDtoResult).isNotNull();
    }

    @Test
    void getProductById() {
        Product product = new Product();
        product.setId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c12"));
        product.setName("Name");
        Mockito.when(productRepository
                .findByIdAndIsDeletedFalse(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c12")))
                .thenReturn(java.util.Optional.of(product));
        Product result = productService.getProductById(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c12"));
        assertEquals("Name", result.getName());
    }



    @Test
    void moving() {
        MovingProductDto movingProductDto = new MovingProductDto();
        movingProductDto.setId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c12"));
        movingProductDto.setName("Name");
        movingProductDto.setArticle(12);
        movingProductDto.setRemainingGoods(23);
        Product product = new Product();
        product.setId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c12"));
        product.setName("Name");
        product.setStockId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c23"));
        MovingProductStockDto movingProductStockDto = new MovingProductStockDto();
        movingProductStockDto.setId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c12"));
        movingProductStockDto.setName("Name");
        movingProductStockDto.setArticle(12);
        movingProductStockDto.setRemainingGoods(23);
        movingProductStockDto.setStockId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c23"));
        Mockito.when(productRepository
                        .findByIdAndIsDeletedFalse(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c12")))
                        .thenReturn(java.util.Optional.of(product));
        Mockito.when(movingProductStockMapper.toDto(product)).thenReturn(movingProductStockDto);
        MovingProductStockDto result = productService.moving(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c23"),
                movingProductDto);
        assertEquals("Name", result.getName());

    }

    @Test
    void saleProduct() {
        SaleProductDto saleProductDto = new SaleProductDto();
        saleProductDto.setId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c12"));
        saleProductDto.setName("Name");
        saleProductDto.setRemainingGoods(12);
        Product product = new Product();
        product.setId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c12"));
        product.setName("Name");
        product.setRemainingGoods(12);
        Mockito.when(productRepository
                .findByIdAndIsDeletedFalse(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c12")))
                .thenReturn(java.util.Optional.of(product));
        Mockito.when(saleMapper.toDto(product)).thenReturn(saleProductDto);
        SaleProductDto result = productService.saleProduct(saleProductDto);
        assertEquals("Name", result.getName());
    }

    @Test
    void deleteStock() {
        ProductDto productDto = new ProductDto();
        productDto.setName("Name");
        productDto.setArticle(123);
        productDto.setRemainingGoods(12);
        Product product = new Product();
        product.setId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c12"));
        product.setName("Name");
        product.setRemainingGoods(12);
        Mockito.when(productRepository
                .getReferenceById(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c12")))
                .thenReturn(product);
        Mockito.when(productMapper.toDto(product)).thenReturn(productDto);
        ProductDto result = productService.deleteStock(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c12"));
        assertEquals("Name", result.getName());
    }

    @Test
    void getAllProductOfStock() {
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product();
        product1.setId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c12"));
        product1.setName("Name1");
        product1.setRemainingGoods(12);
        product1.setStockId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c34"));
        Product product2 = new Product();
        product2.setId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c12"));
        product2.setName("Name2");
        product2.setRemainingGoods(12);
        product2.setStockId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c34"));
        Product product3 = new Product();
        product3.setId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c13"));
        product3.setName("Name3");
        product3.setRemainingGoods(12);
        product3.setStockId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c34"));
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        Mockito.when(productRepository.findAllByIsDeletedFalse()).thenReturn(productList);
        List<Product> result = productService.getAllProductOfStock(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c34"));
        assertEquals("Name1", result.get(0).getName());
    }

    @Test
    void getFindAllByName() {
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product();
        product1.setId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c12"));
        product1.setName("Name1");
        product1.setRemainingGoods(12);
        product1.setStockId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c34"));
        Product product2 = new Product();
        product2.setId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c12"));
        product2.setName("Name2");
        product2.setRemainingGoods(12);
        product2.setStockId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c34"));
        Product product3 = new Product();
        product3.setId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c13"));
        product3.setName("Name3");
        product3.setRemainingGoods(12);
        product3.setStockId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c34"));
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        Mockito.when(productRepository.findAllByName("Name1")).thenReturn(productList);
        List<Product> result = productService.getFindAllByName("Name1");
        assertEquals("Name1", result.get(0).getName());
        assertEquals(12, result.get(0).getRemainingGoods());
    }

    @Test
    void findAll() {
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product();
        product1.setId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c12"));
        product1.setName("Name1");
        product1.setRemainingGoods(12);
        product1.setStockId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c34"));
        Product product2 = new Product();
        product2.setId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c12"));
        product2.setName("Name2");
        product2.setRemainingGoods(12);
        product2.setStockId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c34"));
        Product product3 = new Product();
        product3.setId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c13"));
        product3.setName("Name3");
        product3.setRemainingGoods(12);
        product3.setStockId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c34"));
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        Mockito.when(productRepository.findAll()).thenReturn(productList);
        List<Product> result = productService.findAll();
        assertEquals("Name1", result.get(0).getName());
        assertEquals("Name2", result.get(1).getName());
        assertEquals("Name3", result.get(2).getName());
    }

}