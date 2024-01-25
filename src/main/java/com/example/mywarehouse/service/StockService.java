package com.example.mywarehouse.service;

import com.example.mywarehouse.dto.StockDto;
import com.example.mywarehouse.entity.Stock;
import com.example.mywarehouse.mapper.StockMapper;
import com.example.mywarehouse.repository.ProductRepository;
import com.example.mywarehouse.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
@RequiredArgsConstructor
@Transactional
public class StockService {

    public final StockRepository stockRepository;
    public final ProductRepository productRepository;
    public final StockMapper stockMapper;

    public StockDto createStock(StockDto stockDto) {
        Stock stock = stockMapper.toEntity(stockDto);
        Stock stock1 = stockRepository.save(stock);
        return stockMapper.toDto(stock1);
    }

    public StockDto getStockById(UUID id) {
        Stock stock = stockRepository.findByIdAndIsDeletedFalse(id).orElseThrow();
        return stockMapper.toDto(stock);
    }

    public StockDto updateStock(UUID id, StockDto stockDto) {
        Stock stock = stockMapper.toEntity(stockDto);
        stock.setId(id);
        stock.setName(stockDto.getName());
        Stock updateStock = stockRepository.save(stock);
        return stockMapper.toDto(updateStock);
    }

    public StockDto deleteStock(UUID id) {
        Stock stock = stockRepository.getReferenceById(id);
        stock.setDeleted(true);
        return stockMapper.toDto(stock);
    }

    public Stock getStockOfName(String name) {
        return stockRepository.findByName(name);
    }


}
