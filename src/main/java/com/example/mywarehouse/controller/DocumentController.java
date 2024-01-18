package com.example.mywarehouse.controller;

import com.example.mywarehouse.dto.EntranceProductDto;
import com.example.mywarehouse.dto.LeftoversProductDto;
import com.example.mywarehouse.dto.MovingProductDto;
import com.example.mywarehouse.dto.ProductDocDto;
import com.example.mywarehouse.dto.SaleProductDto;
import com.example.mywarehouse.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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


    @PostMapping("/createEntrance")
    public ResponseEntity<List<EntranceProductDto>> createEntrance(@RequestBody List<EntranceProductDto> entranceDto) {
        List<EntranceProductDto> entranceProductDtoList = documentService.getEntrance(entranceDto);
        return new ResponseEntity<>(entranceProductDtoList, HttpStatus.OK);
    }

    @PostMapping("/createMoving/{newStockId}")
    public ResponseEntity<List<MovingProductDto>> createMoving(@PathVariable UUID newStockId, @RequestBody List<MovingProductDto> movingProductDto) {
        List<MovingProductDto> movingProductDtoList = documentService.getMoving(newStockId, movingProductDto);
        return new ResponseEntity<>(movingProductDtoList, HttpStatus.OK);
    }

    @PostMapping( "/createSale/{stockId}")
    public ResponseEntity<List<SaleProductDto>> createSale(@PathVariable UUID stockId, @RequestBody List<SaleProductDto> saleDto) {
        List<SaleProductDto> saleProductDtoList = documentService.getSale(stockId, saleDto);
        return new ResponseEntity<>(saleProductDtoList, HttpStatus.OK);
    }

    @GetMapping("/All")
    public ResponseEntity<List<ProductDocDto>> getAllProduct(@RequestParam("name") Optional<String> str) {
        String name = str.orElseGet(() -> "String is null");
        List<ProductDocDto> productDocDtoList = documentService.getAllProduct(name);
        return new ResponseEntity<>(productDocDtoList, HttpStatus.OK);
    }

    @GetMapping("/allLeftoversProduct")
    public ResponseEntity<List<LeftoversProductDto>> getAllLeftoversProduct(@RequestParam("name") Optional<String> str) {
        String name = str.orElseGet(() -> "String is null");
        List<LeftoversProductDto> leftoversProductDtoList = documentService.getAllLeftoversProduct(name);
        return new ResponseEntity<>(leftoversProductDtoList, HttpStatus.OK);
    }


}
