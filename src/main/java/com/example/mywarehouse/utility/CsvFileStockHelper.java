package com.example.mywarehouse.utility;

import com.example.mywarehouse.dto.LeftoversProductDto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class CsvFileStockHelper {

    private CsvFileStockHelper() {
    }

    public static ByteArrayInputStream productToCsl(List<LeftoversProductDto> products) {
        final CSVFormat format = CSVFormat.DEFAULT.withHeader("артикул","наименование","остаток");
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out,true, StandardCharsets.UTF_8), format)) {
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
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }

}
