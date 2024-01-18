package com.example.mywarehouse.dto;

import lombok.Data;
import java.util.UUID;
//        Поступление (Номер, Склад, Список товаров).
//        Заводится при поступлении товара. Содержит список товаров,
//        их количество и закупочные цены.
//        В документе указывается склад, на который поступают товары.
@Data
public class EntranceProductDto {

    private String name;

    private Integer article;

    private UUID stockId;

    private Integer lastPurchasePrice;

    private Integer remainingGoods;

}
