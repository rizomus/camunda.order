package com.example.order.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private Long article;
    private String description;
    private int amount;

    public Product(Long article, String description, int amount) {
        this.article = article;
        this.description = description;
        this.amount = amount;
    }
}
