package com.example.mywarehouse.controller;

import com.example.mywarehouse.dto.EntranceProductDto;
import com.example.mywarehouse.dto.LeftoversProductDto;
import com.example.mywarehouse.dto.MovingProductDto;
import com.example.mywarehouse.dto.MovingProductStockDto;
import com.example.mywarehouse.dto.ProductDocDto;
import com.example.mywarehouse.dto.ProductStockIdDto;
import com.example.mywarehouse.dto.SaleProductDto;
import com.example.mywarehouse.service.DocumentService;
import com.example.mywarehouse.service.ProductFileService;
import com.example.mywarehouse.service.StockFileService;
import com.example.mywarehouse.utility.FileName;
import com.example.mywarehouse.utility.StringHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/documents")
public class DocumentController {

    private final DocumentService documentService;
    private final ProductFileService productFileService;
    private final StockFileService stockFileService;


    @PostMapping("/createEntrance")
    public ResponseEntity<List<ProductStockIdDto>> createEntrance(@RequestBody List<EntranceProductDto> entranceDto) {
        List<ProductStockIdDto> entranceProductDtoList = documentService.getEntrance(entranceDto);

        return new ResponseEntity<>(entranceProductDtoList, HttpStatus.CREATED);
    }

    @PutMapping("/createMoving/{newStockId}")
    public ResponseEntity<List<MovingProductStockDto>> createMoving(@PathVariable UUID newStockId, @RequestBody List<MovingProductDto> movingProductDto) {
        List<MovingProductStockDto> movingProductDtoList = documentService.getMoving(newStockId, movingProductDto);
        return new ResponseEntity<>(movingProductDtoList, HttpStatus.CREATED);
    }

    @PutMapping( "/createSale")
    public ResponseEntity<List<SaleProductDto>> createSale(@RequestBody List<SaleProductDto> saleDto) {
        List<SaleProductDto> saleProductDtoList = documentService.getSale(saleDto);
        return new ResponseEntity<>(saleProductDtoList, HttpStatus.CREATED);
    }

    @GetMapping("/All")
    public ResponseEntity<List<ProductDocDto>> getAllProduct(@RequestParam(value = "name", required = false) Optional<String> str) {
        String name = str.orElseGet(() -> "String is null");
        List<ProductDocDto> productDocDtoList = documentService.getAllProduct(name);
        return new ResponseEntity<>(productDocDtoList, HttpStatus.OK);
    }

    @GetMapping("/allLeftoversProduct")
    public ResponseEntity<List<LeftoversProductDto>> getAllLeftoversProduct(@RequestParam(value = "name", required = false) Optional<String> str) {
        String name = str.orElseGet(() -> "String is null");
        List<LeftoversProductDto> leftoversProductDtoList = documentService.getAllLeftoversProduct(name);
        return new ResponseEntity<>(leftoversProductDtoList, HttpStatus.OK);
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> getFileAllProduct(@RequestParam(value = "name", required = false) Optional<String> str) {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + FileName.getFileName(StringHelper.getString(str)) + ".csv")
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(new InputStreamResource(productFileService.getCsvProduct(StringHelper.getString(str))));
    }

    @GetMapping("/downloadStockProduct")
    public ResponseEntity<Resource> getFileAllLeftoversProduct(@RequestParam(value = "name", required = false) Optional<String> str) {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + FileName.getFileName(StringHelper.getString(str)) + ".csv")
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(new InputStreamResource(stockFileService.getCsvProduct(StringHelper.getString(str))));
    }


}
