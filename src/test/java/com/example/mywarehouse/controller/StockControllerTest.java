package com.example.mywarehouse.controller;

import com.example.mywarehouse.dto.StockDto;
import com.example.mywarehouse.entity.Stock;
import com.example.mywarehouse.service.StockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StockController.class)
class StockControllerTest {

    private static final String URL = "/api/stock";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StockService stockService;

    @Test
    void createStock() throws Exception {
        Stock stock = new Stock();
        stock.setName("Name");
        when(stockService.createStock(any())).thenReturn(stock);
        mvc.perform(post(URL + "/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                               {
                                    "name": "Name"
                                }
                                """))
                .andExpect(jsonPath("$.name").value("Name"));
    }

    @Test
    void getStockById() throws Exception {
        StockDto stockDto = new StockDto();
        stockDto.setName("Name");
        when(stockService.getStockDtoById(any())).thenReturn(stockDto);
        mvc.perform(get(URL + "/" + UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c34")))
                .andExpect(jsonPath("$.name").value("Name"));
    }

    @Test
    void updateStock() throws Exception {
        StockDto stockDto = new StockDto();
        stockDto.setName("Name");
        when(stockService.updateStock(any(), any())).thenReturn(stockDto);
        mvc.perform(put(URL + "/update/" + UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c23"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""" 
                          {
                            "name": "Name"
                          }   
                        """))
                .andExpect(jsonPath("$.name").value("Name"));
    }

    @Test
    void deleteStock() throws Exception {
        StockDto stockDto = new StockDto();
        stockDto.setName("Name");
        when(stockService.deleteStock(any())).thenReturn(stockDto);
        mvc.perform(delete(URL + "/delete/" + UUID.fromString("db9d6441-c3f2-4d8d-83fb-c07298610c23")))
                .andExpect(status().isOk());
    }
}