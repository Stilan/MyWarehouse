package com.example.mywarehouse.service;

import com.example.mywarehouse.dto.EntranceProductDto;
import com.example.mywarehouse.dto.MovingProductDto;
import com.example.mywarehouse.dto.MovingProductStockDto;
import com.example.mywarehouse.dto.ProductDto;
import com.example.mywarehouse.dto.ProductStockIdDto;
import com.example.mywarehouse.dto.SaleProductDto;
import com.example.mywarehouse.entity.Product;
import com.example.mywarehouse.mapper.EntranceMapper;
import com.example.mywarehouse.mapper.MovingProductStockMapper;
import com.example.mywarehouse.mapper.ProductMapper;
import com.example.mywarehouse.mapper.ProductStockIdMapper;
import com.example.mywarehouse.mapper.SaleMapper;
import com.example.mywarehouse.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final EntranceMapper entranceMapper;
    private final StockService stockService;
    private final SaleMapper saleMapper;
    private final ProductMapper productMapper;
    private final ProductStockIdMapper productStockIdMapper;
    private final MovingProductStockMapper movingProductStockMapper;

    public ProductStockIdDto createProduct(EntranceProductDto productDto) {
        Product product = entranceMapper.toEntity(productDto);
        productRepository.save(product);
        productStockIdMapper.toDto(product);
        return productStockIdMapper.toDto(product);
    }

    public ProductDto create(ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);
        productRepository.save(product);
        return productMapper.toDto(product);
    }

    public Product getProductById(UUID id) {
        return productRepository.findByIdAndIsDeletedFalse(id).orElseThrow();
    }

    public MovingProductStockDto moving(UUID newStockId, MovingProductDto movingProductDto) {
        Product product = productRepository.findByIdAndIsDeletedFalse(movingProductDto.getId()).orElseThrow();
        if (!product.getStockId().equals(newStockId) && stockService.getStockDtoById(newStockId) != null) {
            product.setStockId(newStockId);
            product.setRemainingGoods(movingProductDto.getRemainingGoods());
            productRepository.save(product);
        }
        return movingProductStockMapper.toDto(product);
    }

    public SaleProductDto saleProduct(SaleProductDto saleProductDto) {
        Product product = productRepository.findByIdAndIsDeletedFalse(saleProductDto.getId()).orElseThrow();
        product.setLastSalePrice(saleProductDto.getLastSalePrice());
        product.setRemainingGoods(product.getRemainingGoods() - saleProductDto.getRemainingGoods());
        return saleMapper.toDto(product);
    }

    public ProductDto deleteProduct(UUID id) {
        Product product = productRepository.getReferenceById(id);
        product.setDeleted(true);
        return productMapper.toDto(product);
    }

    public List<Product> getAllProductOfStock(UUID id) {
        List<Product> productList = productRepository.findAllByIsDeletedFalse();
        return productList.stream()
                .filter(p -> p.getStockId().equals(id))
                .collect(Collectors.toList());
    }

    public List<Product> getFindAllByName(String name) {
        return productRepository.findAllByName(name);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }
}

