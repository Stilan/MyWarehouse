package com.example.mywarehouse.service;

import com.example.mywarehouse.dto.LeftoversProductDto;
import com.example.mywarehouse.dto.ProductDocDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FileService {

    private final DocumentService documentService;

    public ByteArrayInputStream getAllCsvProduct(String name) {
        List<ProductDocDto> products = documentService.getAllProduct(name);
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.ALL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
            for (ProductDocDto product : products) {
                List<String> data = Arrays.asList(
                        String.valueOf(product.getArticle()),
                        product.getName(),
                        String.valueOf(product.getLastPurchasePrice())
                );
                csvPrinter.printRecord(data);
            }
            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }

    public ByteArrayInputStream getAllLeftoversCsvProduct(String name) {
        List<LeftoversProductDto> products = documentService.getAllLeftoversProduct(name);
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.ALL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
            for (LeftoversProductDto product : products) {
                List<String> data = Arrays.asList(
                        String.valueOf(product.getArticle()),
                        product.getName(),
                        String.valueOf(product.getRemainingGoods())
                );
                csvPrinter.printRecord(data);
            }
            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }
}
