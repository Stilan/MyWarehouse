package com.example.mywarehouse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity(name = "document")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id = UUID.randomUUID();
    @Column(nullable = false)
    private Integer documentNumber;
    private UUID stockId;
    private UUID loadingWarehouseId;

    private Type type;

    public enum Type {
        ENTRANCE,
        MOVING,
        SALE
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> products;
}
