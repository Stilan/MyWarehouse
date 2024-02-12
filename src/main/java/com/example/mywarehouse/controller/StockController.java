package com.example.mywarehouse.controller;

import com.example.mywarehouse.dto.StockDto;
import com.example.mywarehouse.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/stock")
public class StockController {

    private final StockService stockService;

    @PostMapping("/create")
    public ResponseEntity<StockDto> createStock(@RequestBody StockDto stockDto) {
        StockDto createStockDto = stockService.createStock(stockDto);
        return new ResponseEntity<>(createStockDto, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<StockDto> getStockById(@PathVariable  UUID id) {
        StockDto stockDto = stockService.getStockDtoById(id);
        return ResponseEntity.ok(stockDto);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<StockDto> updateStock(@PathVariable UUID id, @RequestBody StockDto stockDto) {
        StockDto updateStockDto = stockService.updateStock(id, stockDto);
        return new ResponseEntity<>(updateStockDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<StockDto> deleteStock(@PathVariable UUID id) {
        StockDto deleteStockDto = stockService.deleteStock(id);
        return new ResponseEntity<>(deleteStockDto, HttpStatus.OK);
    }
}
