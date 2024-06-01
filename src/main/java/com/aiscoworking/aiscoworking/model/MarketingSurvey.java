package com.aiscoworking.aiscoworking.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="Marketing_survey")
public class MarketingSurvey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "result", nullable = false)
    private String result;

    public MarketingSurvey(long id, String name, String result) {
        this.id = id;
        this.name = name;
        this.result = result;
    }

    public MarketingSurvey(String name, String result) {
        this.name = name;
        this.result = result;
    }

    protected MarketingSurvey() {}
}
