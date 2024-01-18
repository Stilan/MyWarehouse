package com.example.mywarehouse.service;

import com.example.mywarehouse.dto.EntranceProductDto;
import com.example.mywarehouse.dto.LeftoversProductDto;
import com.example.mywarehouse.dto.MovingProductDto;
import com.example.mywarehouse.dto.ProductDocDto;
import com.example.mywarehouse.dto.SaleProductDto;
import com.example.mywarehouse.entity.Product;
import com.example.mywarehouse.entity.Stock;
import com.example.mywarehouse.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DocumentService {

    private final ProductService productService;
    private final StockService stockService;
    private final ProductMapper productMapper;

    public List<EntranceProductDto> getEntrance(List<EntranceProductDto> entranceDto) {
        return entranceDto.stream()
                .map(productService::createProduct)
                .collect(Collectors.toList());
    }

    public List<MovingProductDto> getMoving(UUID id, List<MovingProductDto> movingProductDtoList) {
        return movingProductDtoList.stream()
                .map(movingProductDto -> productService.moving(id, movingProductDto))
                .collect(Collectors.toList());
    }

    public List<SaleProductDto> getSale(UUID id, List<SaleProductDto> saleProductDtoList) {
         return saleProductDtoList.stream()
                  .map(saleProductDto -> productService.saleProduct(id, saleProductDto))
                  .collect(Collectors.toList());
    }

    public List<ProductDocDto> getAllProduct(String name) {
        List<Product> products = productService.findAllByName(name);
        if (name.length() == 0){
           products = productService.findAll();
        }
        return products.stream().map(productMapper :: toDocDto).collect(Collectors.toList());
    }

    public List<LeftoversProductDto> getAllLeftoversProduct(String name) {
        Stock stock = stockService.getStockOfName(name);
        List<Product> products = productService.getAllProductOfStock(stock.getId());
        return products.stream().map(productMapper :: toLeftoversProduct).collect(Collectors.toList());
    }
}
