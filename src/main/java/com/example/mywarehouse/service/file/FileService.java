package com.example.mywarehouse.service.file;

import com.example.mywarehouse.entity.Product;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FileService {

    ResponseEntity<Resource> getFile(List<Product> products, String name);
}
