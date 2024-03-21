package com.example.mywarehouse.service.file;

import com.example.mywarehouse.entity.Product;
import com.example.mywarehouse.enums.FileType;
import com.example.mywarehouse.utility.FileName;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Properties;

@Service
@RequiredArgsConstructor
@Transactional
public class HtmlFileService implements FileService {

    private static final String ARTICLE = "Артикул";
    private static final String NAME = "Наименование";
    private static final String REMAINDER = "Остаток";
    private static final String SAVE_AS = "attachment; filename=";
    private static final String EXCEPTION = "fail to import data to HTML file: ";
    private static final String FILE_TYPE = ".html";


    public static ByteArrayInputStream getByteArrayInputStream(List<Product> products) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (PrintWriter printWriter = new PrintWriter(out,true, StandardCharsets.UTF_8)) {
            Properties property = new Properties();
            FileInputStream fis = new FileInputStream("src/main/resources/db/html.properties");
            for (Product product : products) {
                printWriter.write( property.getProperty("test.1") + property.getProperty("test.2")
                        + property.getProperty("test.3") + ARTICLE + property.getProperty("test.4") + NAME + property.getProperty("test.4") + REMAINDER + property.getProperty("test.5")
                        + property.getProperty("test.6") + product.getArticle() + property.getProperty("test.7") + product.getName() + property.getProperty("test.7") + product.getRemainingGoods() + property.getProperty("test.8")
                        + property.getProperty("test.9"));
                        ;
            }
        } catch (RuntimeException | FileNotFoundException e) {
            throw new RuntimeException(EXCEPTION + e.getMessage());
        }
        return new ByteArrayInputStream(out.toByteArray());
    }
    @Override
    public ResponseEntity<Resource> getFile(List<Product> products, String name) {
        InputStreamResource inputStreamResource = new InputStreamResource(HtmlFileService.getByteArrayInputStream(products));
        String str = FileName.getFileName(name);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, SAVE_AS + str + FILE_TYPE)
                .contentType(MediaType.APPLICATION_JSON)
                .body(inputStreamResource);
    }

    @Override
    public FileType type() {
        return FileType.HTML;
    }
}
