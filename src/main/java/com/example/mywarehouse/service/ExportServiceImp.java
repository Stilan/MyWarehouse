package com.example.mywarehouse.service;

import com.example.mywarehouse.entity.Product;
import com.example.mywarehouse.service.file.CsvFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ExportServiceImp {

   private final ProductService productService;
   private final CsvFileService csvFileService;

   public ResponseEntity<Resource> getFile(String name) {
        List<Product> products = productService.getFindAllByName(name);
        return csvFileService.getFile(products, name);
   }

}
