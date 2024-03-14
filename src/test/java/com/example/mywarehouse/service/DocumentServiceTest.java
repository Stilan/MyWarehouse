package com.example.mywarehouse.service;

import com.example.mywarehouse.dto.EntranceProductDto;
import com.example.mywarehouse.dto.LeftoversProductDto;
import com.example.mywarehouse.dto.MovingProductDto;
import com.example.mywarehouse.dto.MovingProductStockDto;
import com.example.mywarehouse.dto.ProductDocDto;
import com.example.mywarehouse.dto.ProductStockIdDto;
import com.example.mywarehouse.dto.SaleProductDto;
import com.example.mywarehouse.entity.Product;
import com.example.mywarehouse.entity.Stock;
import com.example.mywarehouse.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DocumentServiceTest {

    @InjectMocks
    private DocumentService documentService;
    @Mock
    private ProductService productService;
    @Mock
    private StockService stockService;
    @Mock
    private ProductMapper productMapper;
    @Mock
    private StockProductQuantityService stockProductQuantityService;

    @Test
    void getEntrance() {
        EntranceProductDto product1 = new EntranceProductDto();
        product1.setName("Name1");
        product1.setRemainingGoods(12);
        product1.setStockId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c34"));
        EntranceProductDto product2 = new EntranceProductDto();
        product2.setName("Name2");
        product2.setRemainingGoods(12);
        product2.setStockId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c34"));
        ProductStockIdDto product3 = new ProductStockIdDto();
        product3.setName("Name3");
        product3.setRemainingGoods(12);
        product3.setStockId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c34"));
        List<EntranceProductDto> productStockIdDtos = new ArrayList<>();
        productStockIdDtos.add(product1);
        productStockIdDtos.add(product2);
        Mockito.when(productService.createProduct(product1)).thenReturn(product3);
        Mockito.when(stockProductQuantityService.createStockProduct(product3)).thenReturn(product3);
        List<ProductStockIdDto> result = documentService.getEntrance(productStockIdDtos);
        assertEquals("Name3", result.get(0).getName());
    }

    @Test
    void getMoving() {
        MovingProductDto movingProductDto = new MovingProductDto();
        movingProductDto.setId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c12"));
        movingProductDto.setName("Name1");
        movingProductDto.setArticle(12);
        movingProductDto.setRemainingGoods(23);
        MovingProductStockDto movingProductStockDto = new MovingProductStockDto();
        movingProductStockDto.setId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c12"));
        movingProductStockDto.setName("Name1");
        movingProductStockDto.setArticle(12);
        movingProductStockDto.setRemainingGoods(23);
        List<MovingProductDto> movingProductDtoList = new ArrayList<>();
        movingProductDtoList.add(movingProductDto);
        Mockito.when(productService.moving(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c12"), movingProductDto))
                .thenReturn(movingProductStockDto);
        List<MovingProductStockDto> result = documentService
                .getMoving(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c12"), movingProductDtoList);
        assertEquals("Name1", result.get(0).getName());
    }

    @Test
    void getSale() {
        SaleProductDto saleProductDto1 = new SaleProductDto();
        saleProductDto1.setId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c12"));
        saleProductDto1.setName("Name1");
        saleProductDto1.setRemainingGoods(12);
        SaleProductDto saleProductDto2 = new SaleProductDto();
        saleProductDto2.setId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c12"));
        saleProductDto2.setName("Name2");
        saleProductDto2.setRemainingGoods(12);
        Mockito.when(productService.saleProduct(saleProductDto1)).thenReturn(saleProductDto1);
        List<SaleProductDto> saleProductDtoList = new ArrayList<>();
        saleProductDtoList.add(saleProductDto1);
        saleProductDtoList.add(saleProductDto2);
        List<SaleProductDto> result = documentService.getSale(saleProductDtoList);
        assertEquals("Name1", result.get(0).getName());

    }

    @Test
    void getAllProduct() {
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product();
        product1.setId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c12"));
        product1.setName("Name1");
        product1.setRemainingGoods(12);
        product1.setArticle(23);
        product1.setStockId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c34"));
        Product product2 = new Product();
        product2.setId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c12"));
        product2.setName("Name2");
        product2.setRemainingGoods(12);
        product2.setArticle(3);
        product2.setStockId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c34"));
        Product product3 = new Product();
        product3.setId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c13"));
        product3.setName("Name2");
        product3.setRemainingGoods(2);
        product3.setArticle(2);
        product3.setStockId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c34"));
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        ProductDocDto productDocDto = new ProductDocDto();
        productDocDto.setArticle(2);
        productDocDto.setName("Name2");
        productDocDto.setLastPurchasePrice(12);
        Mockito.when(productService.getFindAllByName("Name2")).thenReturn(productList);
        Mockito.when(productMapper.toDocDto(product1)).thenReturn(productDocDto);
        List<ProductDocDto> result = documentService.getAllProduct("Name2");
        assertEquals(2, result.get(0).getArticle());

    }

    @Test
    void getAllLeftoversProduct() {
        Stock stock1 = new Stock();
        stock1.setId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c34"));
        stock1.setName("Name");
        Product product1 = new Product();
        product1.setId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c12"));
        product1.setName("Name1");
        product1.setRemainingGoods(12);
        product1.setArticle(23);
        product1.setStockId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c34"));
        Product product2 = new Product();
        product2.setId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c12"));
        product2.setName("Name2");
        product2.setRemainingGoods(12);
        product2.setArticle(3);
        product2.setStockId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c34"));
        LeftoversProductDto leftoversProductDto = new LeftoversProductDto();
        leftoversProductDto.setArticle(2);
        leftoversProductDto.setName("Name2");
        leftoversProductDto.setRemainingGoods(12);
        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        Mockito.when(stockService.getStockOfName("Name")).thenReturn(stock1);
        Mockito.when(productService.getAllProductOfStock(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c34")))
                .thenReturn(productList);
        Mockito.when(productMapper.toLeftoversProduct(product1)).thenReturn(leftoversProductDto);
        List<LeftoversProductDto> result = documentService.getAllLeftoversProduct("Name");
        assertEquals(2, result.get(0).getArticle());

    }
}