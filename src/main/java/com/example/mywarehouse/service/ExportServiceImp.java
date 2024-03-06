package com.example.mywarehouse.service;

import com.example.mywarehouse.entity.Product;
import com.example.mywarehouse.enums.FileType;
import com.example.mywarehouse.service.file.FileService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ExportServiceImp {

   private final ProductService productService;
   private final List<FileService> services;

    public ExportServiceImp(ProductService productService, List<FileService> services) {
        this.productService = productService;
        this.services = services;
    }

    public ResponseEntity<Resource> getFile(String name, FileType type) {
        List<Product> products = productService.getFindAllByName(name);
        return services.stream()
                .filter(s -> s.type().equals(type))
                .findFirst()
                .map(s -> s.getFile(products, name))
                .orElseThrow(() -> new IllegalArgumentException("not support type"));
   }

}
