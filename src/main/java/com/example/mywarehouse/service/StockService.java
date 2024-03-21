package com.example.mywarehouse.service;

import com.example.mywarehouse.dto.StockDto;
import com.example.mywarehouse.entity.Stock;
import com.example.mywarehouse.exception.ResourceNotFoundException;
import com.example.mywarehouse.mapper.StockMapper;
import com.example.mywarehouse.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class StockService {

    private final StockRepository stockRepository;
    private final StockMapper stockMapper;

    public Stock createStock(StockDto stockDto) {
        Stock stock = stockMapper.toEntity(stockDto);
        return stockRepository.save(stock);
    }

    public StockDto getStockDtoById(UUID id) {
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

    public Stock getStockById(UUID id) {
        Stock stock = stockRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock not found"));
        return stock;
    }
}
