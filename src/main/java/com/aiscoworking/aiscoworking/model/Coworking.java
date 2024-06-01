package com.aiscoworking.aiscoworking.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="Coworking")
public class Coworking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "coordx", nullable = false)
    private double coordx;

    @Column(name = "coordy", nullable = false)
    private double coordy;

    @Column(name = "like_number", nullable = false)
    private long likeNumber;

    public Coworking(long id, String name, String city, double coordx, double coordy, long likeNumber) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.coordx = coordx;
        this.coordy = coordy;
        this.likeNumber = likeNumber;
    }

    public Coworking(String name, String city, double coordx, double coordy, long likeNumber) {
        this.name = name;
        this.city = city;
        this.coordx = coordx;
        this.coordy = coordy;
        this.likeNumber = likeNumber;
    }

    protected Coworking() {}
}
