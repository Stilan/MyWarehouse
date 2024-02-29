package com.example.mywarehouse.service;

import com.example.mywarehouse.dto.StockDto;
import com.example.mywarehouse.entity.Stock;
import com.example.mywarehouse.mapper.StockMapper;
import com.example.mywarehouse.repository.StockRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class StockServiceTest {

    @InjectMocks
    private StockService stockService;

    @Mock
    private StockRepository stockRepository;

    @Mock
    private StockMapper stockMapper;

    @Test
    void createStock() {
        StockDto stockDto = new StockDto();
        stockDto.setName("111");
        Stock stock = new Stock();
        stock.setName("111");
        Mockito.when(stockRepository.save(any())).thenReturn(stock).getMock();
        Mockito.when(stockMapper.toEntity(stockDto)).thenReturn(stock).getMock();
        Mockito.when(stockMapper.toDto(stock)).thenReturn(stockDto);
        StockDto dto = stockService.createStock(stockDto);
        assertThat(dto).isNotNull();
    }

    @Test
    void getStockDtoById() {
        Stock stock = new Stock();
        stock.setId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c34"));
        stock.setName("111");
        Mockito.when(stockRepository.findByIdAndIsDeletedFalse(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c34")))
                .thenReturn(Optional.of(stock));
        StockDto dto = stockService.getStockDtoById(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c34"));
        assertEquals("111", stock.getName());
    }

    @Test
    void updateStock() {
        StockDto stockDto = new StockDto();
        stockDto.setName("111");
        Stock stock = new Stock();
        stock.setName("111");
        Mockito.when(stockMapper.toEntity(stockDto)).thenReturn(stock).getMock();
        stockDto.setName("222");
        Mockito.when(stockRepository.save(any())).thenReturn(stock).getMock();
        Mockito.when(stockMapper.toDto(stock)).thenReturn(stockDto);
        StockDto dto = stockService.createStock(stockDto);
        assertEquals("222", dto.getName());
    }

    @Test
    void deleteStock() {
        Stock stock = new Stock();
        stock.setId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c34"));
        stock.setName("111");
        Mockito.when(stockRepository.getReferenceById(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c34")))
                .thenReturn(stock);
        StockDto stockDto = new StockDto();
        Mockito.when(stockMapper.toDto(stock)).thenReturn(stockDto);
        stockDto = stockService.deleteStock(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c34"));
        assertNull(stockDto.getName());

    }

    @Test
    void getStockOfName() {
        Stock stock = new Stock();
        stock.setName("111");
        Mockito.when(stockRepository.findByName("111")).thenReturn(stock);
        Stock result = stockService.getStockOfName("111");
        assertEquals("111", result.getName());
    }

    @Test
    void getStockById() {
        Stock stock = new Stock();
        stock.setId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c34"));
        stock.setName("111");
        Mockito.when(stockRepository.findByIdAndIsDeletedFalse(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c34")))
                .thenReturn(Optional.of(stock));
        Stock result = stockService.getStockById(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c34"));
        assertEquals("111", result.getName());
    }
}