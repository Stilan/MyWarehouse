package com.example.mywarehouse.service;

import com.example.mywarehouse.dto.LeftoversProductDto;
import com.example.mywarehouse.utility.CsvFileStockHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StockFileService implements FileService {

    private final DocumentService documentService;

    public ByteArrayInputStream getCsvProduct(String name) {
        List<LeftoversProductDto> products = documentService.getAllLeftoversProduct(name);
        return CsvFileStockHelper.productToCsl(products);
    }
}
