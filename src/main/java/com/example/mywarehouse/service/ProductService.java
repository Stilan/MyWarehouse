package com.example.mywarehouse.service;

import com.example.mywarehouse.dto.ProductDocDto;
import com.example.mywarehouse.dto.ProductDto;
import com.example.mywarehouse.dto.StockDto;
import com.example.mywarehouse.entity.Product;
import com.example.mywarehouse.entity.Stock;
import com.example.mywarehouse.mapper.ProductMapper;
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
    private final ProductMapper productMapper;

    public ProductDto createProduct(ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);
        Product savedStock = productRepository.save(product);
        return productMapper.toDto(savedStock);
    }

    public ProductDto getStockById(UUID id) {
        Product product = productRepository.findByIdAndIsDeletedFalse(id).orElseThrow();
        return productMapper.toDto(product);
    }

    public ProductDto updateStock(UUID id, ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);
        product.setId(id);
        product.setName(productDto.getName());
        product.setArticle(productDto.getArticle());
        product.setLastPurchasePrice(productDto.getLastPurchasePrice());
        product.setLastSalePrice(productDto.getLastSalePrice());
        product.setRemainingGoods(productDto.getRemainingGoods());
        Product updateProduct = productRepository.save(product);
        return productMapper.toDto(updateProduct);
    }

    public ProductDto deleteStock(UUID id) {
        Product product = productRepository.getReferenceById(id);
        product.setDeleted(true);
        return productMapper.toDto(product);
    }

}
