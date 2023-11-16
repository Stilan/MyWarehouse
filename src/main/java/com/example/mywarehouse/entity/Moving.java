package com.example.mywarehouse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "moving")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Moving {

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
