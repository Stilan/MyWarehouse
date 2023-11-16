package com.example.mywarehouse.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "entrance")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Entrance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private int documentNumber;
    @OneToOne
    private Stock stock;
    @OneToMany
    private List<Product> products;

}
