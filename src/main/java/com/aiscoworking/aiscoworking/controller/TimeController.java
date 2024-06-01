package com.aiscoworking.aiscoworking.controller;

import com.aiscoworking.aiscoworking.model.Time;
import com.aiscoworking.aiscoworking.service.TimeService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
class TimeWrapper {
    private long dateStart;
    private long dateEnd;
    private long timeStart;
    private long timeEnd;
}

@RestController
@RequestMapping("api/time")
public class TimeController {
    private final TimeService timeService;

    public TimeController(TimeService timeService) {
        super();
        this.timeService = timeService;
    }

    // build create Time Rest Api
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping()
    public ResponseEntity<Time> saveTime(@RequestBody TimeWrapper timeWrapper) {
        Time time = new Time(new java.sql.Date(timeWrapper.getDateStart()),
                new java.sql.Date(timeWrapper.getDateEnd()),
                new java.sql.Time(timeWrapper.getTimeStart()),
                new java.sql.Time(timeWrapper.getTimeEnd()));
        return new ResponseEntity<Time>(timeService.saveTime(time), HttpStatus.CREATED);
    }

    // build get all Time Rest Api
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping()
    public List<Time> getAllTimes() {
        return timeService.getAllTimes();
    }

    // build get Time by ID Rest Api
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("{id}")
    public ResponseEntity<Time> getTimeById(@PathVariable("id") long id) {
        return new ResponseEntity<Time>(timeService.getTimeById(id), HttpStatus.OK);
    }

    // build update Time Rest Api
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<Time> updateTime(@PathVariable("id") long id, @RequestBody TimeWrapper timeWrapper) {
        Time time = new Time(new java.sql.Date(timeWrapper.getDateStart()),
                new java.sql.Date(timeWrapper.getDateEnd()),
                new java.sql.Time(timeWrapper.getTimeStart()),
                new java.sql.Time(timeWrapper.getTimeEnd()));
        return new ResponseEntity<Time>(timeService.updateTime(time, id), HttpStatus.OK);
    }

    // build delete Time Rest Api
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTime(@PathVariable("id") long id) {
        // delete Time from DB
        timeService.deleteTime(id);
        return new ResponseEntity<String>("Time deleted successfully!", HttpStatus.OK);
    }
}
