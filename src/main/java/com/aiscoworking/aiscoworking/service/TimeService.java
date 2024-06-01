package com.aiscoworking.aiscoworking.service;

import com.aiscoworking.aiscoworking.model.Time;

import java.util.List;

public interface TimeService {
    Time saveTime(Time time);
    List<Time> getAllTimes();
    Time getTimeById(long id);
    Time updateTime(Time time, long id);
    void deleteTime(long id);
}
