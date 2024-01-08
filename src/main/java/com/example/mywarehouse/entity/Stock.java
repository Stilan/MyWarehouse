package com.example.mywarehouse.entity;

import com.example.mywarehouse.dto.ProductDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id = UUID.randomUUID();
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private boolean isDeleted = Boolean.FALSE;
    @OneToMany
    @ToString.Exclude
    private List<Product> products;

}
