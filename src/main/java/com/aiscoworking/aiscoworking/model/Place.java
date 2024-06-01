package com.aiscoworking.aiscoworking.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="Place")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "number", nullable = false)
    private int number;

    @Column(name = "price", nullable = false)
    private long price;

    @Column(name = "is_booked", nullable = false)
    private boolean isBooked;

    public Place(long id, int number, long price, boolean isBooked) {
        this.id = id;
        this.number = number;
        this.price = price;
        this.isBooked = isBooked;
    }

    public Place(int number, long price, boolean isBooked) {
        this.number = number;
        this.price = price;
        this.isBooked = isBooked;
    }

    protected Place() {}

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Coworking coworking;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Time time;
}
