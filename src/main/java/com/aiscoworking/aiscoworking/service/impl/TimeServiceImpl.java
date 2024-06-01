package com.aiscoworking.aiscoworking.service.impl;

import com.aiscoworking.aiscoworking.exception.ResourceNotFoundException;
import com.aiscoworking.aiscoworking.model.Time;
import com.aiscoworking.aiscoworking.repository.TimeRepository;
import com.aiscoworking.aiscoworking.service.TimeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeServiceImpl implements TimeService {

    private final TimeRepository timeRepository;

    public TimeServiceImpl(TimeRepository timeRepository) {
        super();
        this.timeRepository = timeRepository;
    }

    @Override
    public Time saveTime(Time time) {
        return timeRepository.save(time);
    }

    @Override
    public List<Time> getAllTimes() {
        return timeRepository.findAll();
    }

    @Override
    public Time getTimeById(long id) {
        return timeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Time", "Id", id));
    }

    @Override
    public Time updateTime(Time time, long id) {
        // check whether time with given id is exist in DB or not
        Time existingTime = timeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Time", "Id", id));
        existingTime.setDateStampStart(time.getDateStampStart());
        existingTime.setDateStampEnd(time.getDateStampEnd());
        existingTime.setTimeStampStart(time.getTimeStampStart());
        existingTime.setTimeStampEnd(time.getTimeStampEnd());
        // save exsiting time
        timeRepository.save(existingTime);
        return existingTime;
    }

    @Override
    public void deleteTime(long id) {
        // check whether time with given id is exist in DB or not
        timeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Time", "Id", id));
        timeRepository.deleteById(id);
    }
}
