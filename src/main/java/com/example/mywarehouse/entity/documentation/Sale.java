package com.example.mywarehouse.entity.documentation;

import com.example.mywarehouse.entity.Product;
import com.example.mywarehouse.entity.Stock;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id = UUID.randomUUID();
    @Column(nullable = false)
    private Integer documentNumber;
    @OneToOne
    private Stock dispatchWarehouse;
    @OneToOne
    private Stock loadingWarehouse;
//    Если параметр fetch принимает значение LAZY, то при загрузке родительской сущности,
//    дочерняя сущность загружена не будет. Вместо нее будет создан proxy-объект.
//    CascadeType.ALL означает, что все действия, которые мы выполняем с родительским объектом,
//    нужно повторить и для его зависимых объектов.
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> products;

}
