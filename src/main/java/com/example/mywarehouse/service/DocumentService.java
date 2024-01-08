package com.example.mywarehouse.service;

import com.example.mywarehouse.dto.*;
import com.example.mywarehouse.entity.Document;
import com.example.mywarehouse.entity.Product;
import com.example.mywarehouse.mapper.EntranceMapper;
import com.example.mywarehouse.mapper.MovingMapper;
import com.example.mywarehouse.mapper.ProductMapper;
import com.example.mywarehouse.mapper.SaleMapper;
import com.example.mywarehouse.repository.DocumentRepository;
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
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final ProductRepository productRepository;
    private final EntranceMapper entranceMapper;
    private final MovingMapper movingMapper;
    private final SaleMapper saleMapper;
    private final ProductMapper productMapper;


    public EntranceDto createDocumentEntrance(EntranceDto entranceDto) {
        Document document = entranceMapper.toEntity(entranceDto);
        document.setType(Document.Type.ENTRANCE);
        Document savedEntrance = documentRepository.save(document);
        return entranceMapper.toDto(savedEntrance);
    }

    public MovingDto createDocumentMoving(MovingDto movingDto) {
        Document document = movingMapper.toEntity(movingDto);
        document.setType(Document.Type.MOVING);
        Document savedMoving = documentRepository.save(document);
        return movingMapper.toDto(savedMoving);
    }

    public SaleDto createDocumentSale(SaleDto saleDto) {
        Document document = saleMapper.toEntity(saleDto);
        document.setType(Document.Type.SALE);
        Document savedSale = documentRepository.save(document);
        return saleMapper.toDto(savedSale);
    }

    public List<ProductDocDto> getAllProduct(String name) {
        List<Product> products = productRepository.findAllByName(name);
        if (name.length() == 0){
           products = productRepository.findAll();
        }
        return products.stream().map(productMapper :: toDocDto).collect(Collectors.toList());

    }
}
