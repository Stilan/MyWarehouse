package com.example.mywarehouse.controller;

import com.example.mywarehouse.dto.*;
import com.example.mywarehouse.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/documents")
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping("/createEntrance")
    public ResponseEntity<EntranceDto> createEntrance(@RequestBody EntranceDto entranceDto) {
        return new ResponseEntity<>(documentService.createDocumentEntrance(entranceDto), HttpStatus.OK);
    }

    @PostMapping("/createMoving")
    public ResponseEntity<MovingDto> createMoving(@RequestBody MovingDto movingDto) {
        return new ResponseEntity<>(documentService.createDocumentMoving(movingDto), HttpStatus.OK);
    }

    @PostMapping("/createSale")
    public ResponseEntity<SaleDto> createSale(@RequestBody SaleDto saleDto) {
        return new ResponseEntity<>(documentService.createDocumentSale(saleDto), HttpStatus.OK);
    }

    @GetMapping("/All")
    public ResponseEntity<List<ProductDocDto>> getAllProduct(@RequestParam("name") String str) {
       return new ResponseEntity<>(documentService.getAllProduct(str), HttpStatus.OK);
    }



}
