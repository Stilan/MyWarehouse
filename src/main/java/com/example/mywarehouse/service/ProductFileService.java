package com.example.mywarehouse.service;

import com.example.mywarehouse.dto.ProductDocDto;
import com.example.mywarehouse.utility.CsvFileProductHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductFileService implements FileService {

    private final DocumentService documentService;

    public ByteArrayInputStream getCsvProduct(String name) {
        List<ProductDocDto> products = documentService.getAllProduct(name);
        return CsvFileProductHelper.productToCsl(products);
    }
}
