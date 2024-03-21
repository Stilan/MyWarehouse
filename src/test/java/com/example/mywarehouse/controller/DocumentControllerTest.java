package com.example.mywarehouse.controller;

import com.example.mywarehouse.dto.LeftoversProductDto;
import com.example.mywarehouse.dto.MovingProductStockDto;
import com.example.mywarehouse.dto.ProductDocDto;
import com.example.mywarehouse.dto.ProductStockIdDto;
import com.example.mywarehouse.dto.SaleProductDto;
import com.example.mywarehouse.service.DocumentService;
import com.example.mywarehouse.service.ExportServiceImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DocumentController.class)
class DocumentControllerTest {

    private static final String URL = "/api/documents";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DocumentService documentService;

    @MockBean
    private ExportServiceImp exportServiceImp;


    @Test
    void createEntrance() throws Exception {
        ProductStockIdDto entranceProductDto1 = new ProductStockIdDto();
        entranceProductDto1.setName("Name");
        entranceProductDto1.setArticle(12);
        List<ProductStockIdDto> list = new ArrayList<>();
        list.add(entranceProductDto1);
        when(documentService.getEntrance(anyList())).thenReturn(list);
        mvc.perform(post(URL + "/createEntrance")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                [
                                  {
                                    "name": "Name",
                                    "article": 12,
                                    "stockId": "23b12a03-21e6-4600-8a6b-e879f2ad1916",
                                    "lastPurchasePrice": 2323,
                                    "remainingGoods": 12
                                  }
                                ]
                                """))
                .andExpect(jsonPath("$.[0].name").value("Name"));
    }


    @Test
    void createMoving() throws Exception {
        MovingProductStockDto movingProductDto = new MovingProductStockDto();
        movingProductDto.setId(UUID.fromString("e2826a87-06c6-48b0-8f54-048f5f079ce0"));
        movingProductDto.setName("Name");
        movingProductDto.setArticle(12323);
        List<MovingProductStockDto> list = new ArrayList<>();
        list.add(movingProductDto);
        when(documentService.getMoving(any(), anyList())).thenReturn(list);
        mvc.perform(put(URL + "/createMoving/" + UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c23"))
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        [
                          {
                            "id": "e2826a87-06c6-48b0-8f54-048f5f079ce0",
                            "name": "Name",
                            "article": 12323
                          }
                        ]
                        """))
                .andExpect(jsonPath("$.[0].name").value("Name"));
    }

    @Test
    void createSale() throws Exception {
        SaleProductDto saleProductDto = new SaleProductDto();
        saleProductDto.setId(UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c34"));
        saleProductDto.setName("Name");
        saleProductDto.setArticle(12);
        saleProductDto.setLastSalePrice(23);
        saleProductDto.setRemainingGoods(34);
        List<SaleProductDto> list = new ArrayList<>();
        list.add(saleProductDto);
        when(documentService.getSale(anyList())).thenReturn(list);
        mvc.perform(put(URL + "/createSale")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                               [
                                  {
                                    "id": "db9d6441-c3f2-4d8d-83fb-c07298610c34",
                                    "name": "2",
                                    "lastSalePrice": 323,
                                    "article": 12323,
                                    "remainingGoods": 1,
                                    "stockId": "e5179fa0-ca6e-422c-92cf-0db05b1975ea"
                                   }
                               ]
                                """))
                .andExpect(jsonPath("$.[0].name").value("Name"));
    }

    @Test
    void getAllProduct() throws Exception {
        ProductDocDto productDocDto =  new ProductDocDto();
        productDocDto.setName("Name");
        productDocDto.setArticle(12);
        productDocDto.setLastPurchasePrice(34);
        List<ProductDocDto> productDocDtoList = new ArrayList<>();
        productDocDtoList.add(productDocDto);
        when(documentService.getAllProduct(any())).thenReturn(productDocDtoList);
        mvc.perform(get(URL + "/All" + "?name=Name"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name").value("Name"));
    }

    @Test
    void getAllLeftoversProduct() throws Exception {
        LeftoversProductDto leftoversProductDto = new LeftoversProductDto();
        leftoversProductDto.setName("Name");
        leftoversProductDto.setArticle(12);
        leftoversProductDto.setRemainingGoods(34);
        List<LeftoversProductDto> leftoversProductDtoList = new ArrayList<>();
        leftoversProductDtoList.add(leftoversProductDto);
        when(documentService.getAllLeftoversProduct(any())).thenReturn(leftoversProductDtoList);
        mvc.perform(get(URL + "/allLeftoversProduct" + "?name=test3.txt"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name").value("Name"));

    }
}