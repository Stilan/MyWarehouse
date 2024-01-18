package com.example.mywarehouse.service;

import com.example.mywarehouse.dto.EntranceProductDto;
import com.example.mywarehouse.dto.MovingProductDto;
import com.example.mywarehouse.dto.ProductDto;
import com.example.mywarehouse.dto.SaleProductDto;
import com.example.mywarehouse.entity.Product;
import com.example.mywarehouse.mapper.EntranceMapper;
import com.example.mywarehouse.mapper.MovingMapper;
import com.example.mywarehouse.mapper.ProductMapper;
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
    private final MovingMapper movingMapper;
    private final SaleMapper saleMapper;
    private final ProductMapper productMapper;

    public EntranceProductDto createProduct(EntranceProductDto productDto) {
        Product product = entranceMapper.toEntity(productDto);
        Product savedStock = productRepository.save(product);
        return entranceMapper.toDto(savedStock);
    }

    public ProductDto create(ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);
        Product savedStock = productRepository.save(product);
        return productMapper.toDto(savedStock);
    }

    public Product getProductById(UUID id) {
        return productRepository.findByIdAndIsDeletedFalse(id).orElseThrow();
    }

    public MovingProductDto moving(UUID newStockId, MovingProductDto movingProductDto) {
        Product product = productRepository.findByIdAndIsDeletedFalse(movingProductDto.getId()).orElseThrow();
        product.setStockId(newStockId);
        product.setRemainingGoods(movingProductDto.getRemainingGoods());
        productRepository.save(product);
        return movingMapper.toDto(product);
    }

    public SaleProductDto saleProduct(UUID stockId, SaleProductDto saleProductDto) {
        List<Product> products = getAllProductOfStock(stockId);
        Product product = productRepository.findByIdAndIsDeletedFalse(saleProductDto.getId()).orElseThrow();
        products.stream()
                .filter(p -> p.getId().equals(product.getId()))
                .findFirst()
                .ifPresent(p -> {
                    p.setRemainingGoods(p.getRemainingGoods() - saleProductDto.getRemainingGoods());
                    p.setLastSalePrice(saleProductDto.getLastSalePrice());
                    productRepository.save(p);
                });

        return saleMapper.toDto(product);
    }

    public ProductDto deleteStock(UUID id) {
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

    public List<Product> findAllByName(String name) {
        return productRepository.findAllByName(name);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }
}

