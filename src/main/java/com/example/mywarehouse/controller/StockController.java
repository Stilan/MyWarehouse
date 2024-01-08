package com.example.mywarehouse.controller;

import com.example.mywarehouse.dto.StockDto;
import com.example.mywarehouse.entity.Stock;
import com.example.mywarehouse.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/stock")
public class StockController {

    private final StockService stockService;

    @PostMapping("/create")
    public ResponseEntity<StockDto> createStock(@RequestBody StockDto stockDto) {
        return new ResponseEntity<>(stockService.createStock(stockDto), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<StockDto> getTemplateById(@PathVariable  UUID id) {
        StockDto stockDto = stockService.getStockById(id);
        return ResponseEntity.ok(stockDto);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<StockDto> updateStock(@PathVariable UUID id, @RequestBody StockDto stockDto) {
        return new ResponseEntity<>(stockService.updateStock(id, stockDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<StockDto> deleteStock(@PathVariable UUID id) {
        return new ResponseEntity<>(stockService.deleteStock(id), HttpStatus.OK);
    }
}
