package com.example.mywarehouse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id = UUID.randomUUID();
    @Column(nullable = false)
    private String name;
//    Если параметр fetch принимает значение LAZY, то при загрузке родительской сущности,
//    дочерняя сущность загружена не будет. Вместо нее будет создан proxy-объект.
//    CascadeType.ALL означает, что все действия, которые мы выполняем с родительским объектом,
//    нужно повторить и для его зависимых объектов.
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> stock;
//    почему в стоке есть лист продуктов и в sale тоже такой лист и еще два стока?
//    и еще Moving содержит лист продуктов и сток, где лист продуктов
    // по заданию
    // - Склад (Наименование). На складе может храниться несколько товаров.
}
