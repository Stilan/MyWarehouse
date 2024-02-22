package com.example.mywarehouse.utility;

import com.example.mywarehouse.dto.ProductDocDto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class CsvFileProductHelper {

    private CsvFileProductHelper() {
    }

    public static ByteArrayInputStream productToCsl(List<ProductDocDto> list) {
        final CSVFormat format = CSVFormat.TDF.withHeader("артикул","наименование","цены закупки и продажи");
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out, true, StandardCharsets.UTF_8), format)) {
            for (ProductDocDto product : list) {
                List<String> data = Arrays.asList(
                        String.valueOf(product.getArticle()),
                        product.getName(),
                        String.valueOf(product.getLastPurchasePrice())
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
