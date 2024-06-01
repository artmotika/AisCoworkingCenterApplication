package com.aiscoworking.aiscoworking.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="Promotion")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "discount", nullable = false)
    private int discount;

    public Promotion(long id, String name, int discount) {
        this.id = id;
        this.name = name;
        this.discount = discount;
    }

    public Promotion(String name, int discount) {
        this.name = name;
        this.discount = discount;
    }

    protected Promotion() {}
}
