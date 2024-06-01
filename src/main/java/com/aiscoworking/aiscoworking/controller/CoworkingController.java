package com.aiscoworking.aiscoworking.controller;

import com.aiscoworking.aiscoworking.model.Coworking;
import com.aiscoworking.aiscoworking.model.Place;
import com.aiscoworking.aiscoworking.service.CoworkingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/coworking")
public class CoworkingController {
    private final CoworkingService coworkingService;

    public CoworkingController(CoworkingService coworkingService) {
        super();
        this.coworkingService = coworkingService;
    }

    // build get all Coworking Places Rest Api
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OWNER', 'ROLE_COWORKING_OWNER', 'ROLE_COWORKING_EMPLOYEE')")
    @GetMapping("{id}/places")
    public List<Place> getAllCoworkingPlaces(@PathVariable("id") long id) {
        return coworkingService.getAllCoworkingPlaces(id);
    }

    // build get all empty Coworking Places Rest Api
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OWNER', 'ROLE_COWORKING_OWNER', 'ROLE_COWORKING_EMPLOYEE')")
    @GetMapping("{id}/places/empty")
    public List<Place> getAllCoworkingEmptyPlaces(@PathVariable("id") long id) {
        return coworkingService.getAllCoworkingEmptyPlaces(id);
    }

    // build create Coworking Rest Api
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OWNER')")
    @PostMapping()
    public ResponseEntity<Coworking> saveCoworking(@RequestBody Coworking coworking) {
        return new ResponseEntity<Coworking>(coworkingService.saveCoworking(coworking), HttpStatus.CREATED);
    }

    // build get all Coworking Rest Api
    @GetMapping()
    public List<Coworking> getAllCoworking() {
        return coworkingService.getAllCoworking();
    }

    // build get Coworking by ID Rest Api
    @GetMapping("{id}")
    public ResponseEntity<Coworking> getCoworkingById(@PathVariable("id") long id) {
        return new ResponseEntity<Coworking>(coworkingService.getCoworkingById(id), HttpStatus.OK);
    }

    // build update Coworking Rest Api
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OWNER')")
    @PutMapping("{id}")
    public ResponseEntity<Coworking> updateCoworking(@PathVariable("id") long id, @RequestBody Coworking coworking) {
        return new ResponseEntity<Coworking>(coworkingService.updateCoworking(coworking, id), HttpStatus.OK);
    }

    // build delete Coworking Rest Api
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OWNER')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCoworking(@PathVariable("id") long id) {
        // delete Coworking from DB
        coworkingService.deleteCoworking(id);
        return new ResponseEntity<String>("Coworking deleted successfully!", HttpStatus.OK);
    }
}
