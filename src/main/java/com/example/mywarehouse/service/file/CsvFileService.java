package com.example.mywarehouse.service.file;

import com.example.mywarehouse.entity.Product;
import com.example.mywarehouse.utility.FileName;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CsvFileService implements FileService {

    private static final String ARTICLE = "Артикул";
    private static final String NAME = "Наименование";
    private static final String REMAINDER = "Остаток";
    private static final String SAVE_AS = "attachment; filename=";
    private static final String FILE_TYPE = ".csv";
    private static final String EXCEPTION = "fail to import data to CSV file: ";

    public static ByteArrayInputStream getByteArrayInputStream(List<Product> products) {
        final CSVFormat format = CSVFormat.DEFAULT.withHeader(ARTICLE, NAME, REMAINDER);
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out,true, StandardCharsets.UTF_8), format)) {
            for (Product product : products) {
                List<String> data = Arrays.asList(
                        String.valueOf(product.getArticle()),
                        product.getName(),
                        String.valueOf(product.getRemainingGoods())
                );
                csvPrinter.printRecord(data);
            }
            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException(EXCEPTION + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Resource> getFile(List<Product> products, String name) {
        InputStreamResource inputStreamResource = new InputStreamResource(CsvFileService.getByteArrayInputStream(products));
        String str = FileName.getFileName(name);
         return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, SAVE_AS + str + FILE_TYPE)
                .contentType(MediaType.APPLICATION_JSON)
                .body(inputStreamResource);
    }
}
