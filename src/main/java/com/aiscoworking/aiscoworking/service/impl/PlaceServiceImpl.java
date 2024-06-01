package com.aiscoworking.aiscoworking.service.impl;

import com.aiscoworking.aiscoworking.exception.ResourceNotFoundException;
import com.aiscoworking.aiscoworking.model.Place;
import com.aiscoworking.aiscoworking.repository.PlaceRepository;
import com.aiscoworking.aiscoworking.service.PlaceService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;

    public PlaceServiceImpl(PlaceRepository placeRepository) {
        super();
        this.placeRepository = placeRepository;
    }

    @Override
    public Place savePlace(Place place) {
        return placeRepository.save(place);
    }

    @Override
    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }

    @Override
    public List<Place> getAllEmptyPlaces() {
        Date date = new Date();
        List<Place> emptyPlaces = new ArrayList<>();
        for ( Place place : placeRepository.findAll()) {
            if (!place.isBooked()) {
                emptyPlaces.add(place);
            } else if (date.getTime() >= place.getTime().getTimeStampEnd().getTime()) {
                place.setBooked(false);
                updatePlace(place, place.getId());
                emptyPlaces.add(place);
            }
        }
        return emptyPlaces;
    }

    @Override
    public Place getPlaceById(long id) {
        return placeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Place", "Id", id));
    }

    @Override
    public Place updatePlace(Place place, long id) {
        // check whether place with given id is exist in DB or not
        Place existingPlace = placeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Place", "Id", id));
        existingPlace.setNumber(place.getNumber());
        existingPlace.setPrice(place.getPrice());
        existingPlace.setBooked(place.isBooked());
        existingPlace.setCoworking(place.getCoworking());
        existingPlace.setTime(place.getTime());
        // save exsiting place
        placeRepository.save(existingPlace);
        return existingPlace;
    }

    @Override
    public void deletePlace(long id) {
        // check whether place with given id is exist in DB or not
        placeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Place", "Id", id));
        placeRepository.deleteById(id);
    }
}
