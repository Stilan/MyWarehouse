package com.example.mywarehouse.service.file;

import com.example.mywarehouse.entity.Product;
import com.example.mywarehouse.enums.FileType;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class HtmlFileService implements FileService {

    private static final String SAVE_AS = "attachment; filename=";
    private static final String FILE_TYPE = ".html";

    @Override
    public ResponseEntity<Resource> getFile(List<Product> products, String name) {
        return null;

    }

    @Override
    public FileType type() {
        return FileType.HTML;
    }
}
