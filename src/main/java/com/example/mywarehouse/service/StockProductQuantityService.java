package com.example.mywarehouse.service;

import com.example.mywarehouse.dto.ProductStockIdDto;
import com.example.mywarehouse.entity.StockProductQuantity;
import com.example.mywarehouse.repository.StockProductQuantityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class StockProductQuantityService {

    private final StockProductQuantityRepository stockProductQuantityRepository;
    private final StockService stockService;
    private final ProductService productService;

    public ProductStockIdDto createStockProduct(ProductStockIdDto productDto) {
        StockProductQuantity stockProductQuantity = new StockProductQuantity();
        stockProductQuantity.setStockId(stockService.getStockById(productDto.getStockId()));
        stockProductQuantity.setProductId(productService.getProductById(productDto.getId()));
        stockProductQuantity.setQuantity(productDto.getRemainingGoods());
        stockProductQuantityRepository.save(stockProductQuantity);
        return productDto;
    }


}
