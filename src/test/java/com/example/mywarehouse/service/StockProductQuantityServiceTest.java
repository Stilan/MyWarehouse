package com.example.mywarehouse.service;

import com.example.mywarehouse.dto.ProductStockIdDto;
import com.example.mywarehouse.entity.Product;
import com.example.mywarehouse.entity.Stock;
import com.example.mywarehouse.entity.StockProductQuantity;
import com.example.mywarehouse.repository.StockProductQuantityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class StockProductQuantityServiceTest {

    @InjectMocks
    private StockProductQuantityService stockProductQuantityService;
    @Mock
    private StockService stockService;
    @Mock
    private ProductService productService;
    @Mock
    private StockProductQuantityRepository stockProductQuantityRepository;



    @Test
    void createStockProduct() {
        Stock stock = new Stock();
        stock.setName("Name");
        Product product = new Product();
        product.setName("Milk");
        StockProductQuantity stockProductQuantity = new StockProductQuantity();
        stockProductQuantity.setQuantity(12);
        stockProductQuantity.setStockId(stock);
        stockProductQuantity.setProductId(product);
        ProductStockIdDto productDocDto = new ProductStockIdDto();
        productDocDto.setName("Milk");
        productDocDto.setArticle(123);
        productDocDto.setLastPurchasePrice(12);
        Mockito.when(stockService.getStockById(productDocDto.getStockId())).thenReturn(stock);
        Mockito.when(productService.getProductById(productDocDto.getId())).thenReturn(product);
        Mockito.when(stockProductQuantityRepository.save(any())).thenReturn(stockProductQuantity);
        ProductStockIdDto result = stockProductQuantityService.createStockProduct(productDocDto);
        assertEquals("Milk" , result.getName());

    }

}