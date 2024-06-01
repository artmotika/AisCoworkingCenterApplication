package com.aiscoworking.aiscoworking.controller;

import com.aiscoworking.aiscoworking.model.Coworking;
import com.aiscoworking.aiscoworking.model.Place;
import com.aiscoworking.aiscoworking.model.Time;
import com.aiscoworking.aiscoworking.service.CoworkingService;
import com.aiscoworking.aiscoworking.service.PlaceService;
import com.aiscoworking.aiscoworking.service.TimeService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
class PlaceWrapper {
    private int number;
    private long price;
    private int isBooked;
    private long coworking;
    private long time;
}

@RestController
@RequestMapping("api/place")
public class PlaceController {
    private final PlaceService placeService;
    private final CoworkingService coworkingService;
    private final TimeService timeService;

    public PlaceController(PlaceService placeService, CoworkingService coworkingService, TimeService timeService) {
        super();
        this.placeService = placeService;
        this.coworkingService = coworkingService;
        this.timeService = timeService;
    }

    // build create Place Rest Api
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OWNER')")
    @PostMapping()
    public ResponseEntity<Place> savePlace(@RequestBody() PlaceWrapper placeWrapper) {
        Place place = new Place(placeWrapper.getNumber(), placeWrapper.getPrice(), placeWrapper.getIsBooked()==1);
        Coworking coworking = coworkingService.getCoworkingById(placeWrapper.getCoworking());
        Time time = timeService.getTimeById(placeWrapper.getTime());
        place.setCoworking(coworking);
        place.setTime(time);
        return new ResponseEntity<Place>(placeService.savePlace(place), HttpStatus.CREATED);
    }

    // build get all Place Rest Api
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OWNER')")
    @GetMapping()
    public List<Place> getAllPlaces() {
        return placeService.getAllPlaces();
    }

    // build get all empty Places Rest Api
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OWNER')")
    @GetMapping("empty")
    public List<Place> getAllEmptyPlaces() {
        return placeService.getAllEmptyPlaces();
    }

    // build get Place by ID Rest Api
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OWNER')")
    @GetMapping("{id}")
    public ResponseEntity<Place> getPlaceById(@PathVariable("id") long id) {
        return new ResponseEntity<Place>(placeService.getPlaceById(id), HttpStatus.OK);
    }

    // build update Place Rest Api
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OWNER', 'ROLE_COWORKING_OWNER')")
    @PutMapping("{id}")
    public ResponseEntity<Place> updatePlace(@PathVariable("id") long id, @RequestBody PlaceWrapper placeWrapper) {
        Place place = new Place(placeWrapper.getNumber(), placeWrapper.getPrice(), placeWrapper.getIsBooked()==1);
        Coworking coworking = coworkingService.getCoworkingById(placeWrapper.getCoworking());
        Time time = timeService.getTimeById(placeWrapper.getTime());
        place.setCoworking(coworking);
        place.setTime(time);
        return new ResponseEntity<Place>(placeService.updatePlace(place, id), HttpStatus.OK);
    }

    // build delete Place Rest Api
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OWNER')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePlace(@PathVariable("id") long id) {
        // delete Place from DB
        placeService.deletePlace(id);
        return new ResponseEntity<String>("Place deleted successfully!", HttpStatus.OK);
    }
}
