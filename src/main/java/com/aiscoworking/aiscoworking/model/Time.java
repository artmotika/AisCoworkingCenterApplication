package com.aiscoworking.aiscoworking.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="Time")
public class Time {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Basic
    @Column(name = "date_stamp_start", nullable = false)
    private java.sql.Date dateStampStart;

    @Basic
    @Column(name = "date_stamp_end", nullable = false)
    private java.sql.Date dateStampEnd;

    @Basic
    @Column(name = "time_stamp_start", nullable = false)
    private java.sql.Time timeStampStart;

    @Basic
    @Column(name = "time_stamp_end", nullable = false)
    private java.sql.Time timeStampEnd;

    public Time(long id, java.sql.Date dateStampStart, java.sql.Date dateStampEnd,
                java.sql.Time timeStampStart, java.sql.Time timeStampEnd) {
        this.id = id;
        this.dateStampStart = dateStampStart;
        this.dateStampEnd = dateStampEnd;
        this.timeStampStart = timeStampStart;
        this.timeStampEnd = timeStampEnd;
    }

    public Time(java.sql.Date dateStampStart, java.sql.Date dateStampEnd,
                java.sql.Time timeStampStart, java.sql.Time timeStampEnd) {
        this.dateStampStart = dateStampStart;
        this.dateStampEnd = dateStampEnd;
        this.timeStampStart = timeStampStart;
        this.timeStampEnd = timeStampEnd;
    }

    protected Time() {}
}
