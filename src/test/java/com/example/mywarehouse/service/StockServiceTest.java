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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

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
//        StockDto stockDto = new StockDto();
//        stockDto.setName("111");

//        Mockito.when(stockRepository.save(any(Stock.class))).thenReturn(stockMapper.toEntity(stockDto));
    }

    @Test
    void getStockDtoById() {
    }

    @Test
    void updateStock() {
    }

    @Test
    void deleteStock() {
    }

    @Test
    void getStockOfName() {
    }

    @Test
    void getStockById() {
    }
}