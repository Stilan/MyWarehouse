package com.example.mywarehouse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockProductQuantity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name =  "stockid")
    private Stock stockId;
    @ManyToOne
    @JoinColumn(name =  "productid")
    private Product productId;

    private Integer quantity;
}
