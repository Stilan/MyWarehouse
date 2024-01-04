package com.example.mywarehouse.entity.documentation;

import com.example.mywarehouse.entity.Product;
import com.example.mywarehouse.entity.Stock;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class Moving {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id = UUID.randomUUID();
    @Column(nullable = false)
    private Integer documentNumber;
    @OneToOne
    private Stock stock;
//    Если параметр fetch принимает значение LAZY, то при загрузке родительской сущности,
//    дочерняя сущность загружена не будет. Вместо нее будет создан proxy-объект.
//    CascadeType.ALL означает, что все действия, которые мы выполняем с родительским объектом,
//    нужно повторить и для его зависимых объектов.
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> products;
    //    почему в стоке есть лист продуктов и в sale тоже такой лист и еще два стока?
    //    и еще Moving содержит лист продуктов и сток, где лист продуктов

    //    Перемещение (Номер, Склад1, Склад2, Список товаров). Заводится при перемещении товара между складами.
    //    Содержит список товаров и их количество. В документе указывается склад, с которого списывают товары и склад,
    //    на который они поступают.
}
