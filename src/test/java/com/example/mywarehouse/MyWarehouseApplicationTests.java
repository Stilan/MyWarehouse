package com.example.mywarehouse;

import com.example.mywarehouse.dto.*;
import com.example.mywarehouse.entity.Product;
import com.example.mywarehouse.entity.Stock;
import com.example.mywarehouse.mapper.MovingMapper;
import com.example.mywarehouse.mapper.ProductStockIdMapper;
import com.example.mywarehouse.service.ProductService;
import com.example.mywarehouse.service.StockService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MyWarehouseApplicationTests {

    @Autowired
    private ProductService productService;
    @Autowired
    private StockService stockService;
    @Autowired
    private MovingMapper movingMapper;
    @Autowired
    private ProductStockIdMapper productStockIdMapper;

    private UUID stockId1;
    private UUID stockId2;
    private ProductStockIdDto productStockIdDto;

    @BeforeEach
    void before() {
        StockDto stockDto1 = new StockDto();
        stockDto1.setName("Test1");
        StockDto stockDto2 = new StockDto();
        stockDto2.setName("Test2");
        Stock stock1 = stockService.createStock(stockDto1);
        Stock stock2 = stockService.createStock(stockDto2);
        stockId1 = stock1.getId();
        stockId2 = stock2.getId();
        EntranceProductDto entranceProductDto = new EntranceProductDto();
        entranceProductDto.setName("Milk");
        entranceProductDto.setArticle(3232);
        entranceProductDto.setLastPurchasePrice(23);
        entranceProductDto.setRemainingGoods(12);
        entranceProductDto.setStockId(stockId1);
        productStockIdDto = productService.createProduct(entranceProductDto);

    }

    @Test
    void contextLoads() {
        Product product = productStockIdMapper.toEntity(productStockIdDto);
        MovingProductDto movingProductDto = movingMapper.toDto(product);
        MovingProductStockDto movingProductStockDto = productService.moving(stockId2,movingProductDto);
        assertEquals(stockId2, movingProductStockDto.getStockId());

    }

    @AfterEach
    void after() {
        productService.deleteProduct(productStockIdDto.getId());
        stockService.deleteStock(stockId1);
        stockService.deleteStock(stockId2);
    }




}
