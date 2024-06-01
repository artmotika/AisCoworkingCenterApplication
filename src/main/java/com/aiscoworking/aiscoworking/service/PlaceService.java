package com.aiscoworking.aiscoworking.service;

import com.aiscoworking.aiscoworking.model.Place;

import java.util.List;

public interface PlaceService {
    Place savePlace(Place place);
    List<Place> getAllPlaces();
    List<Place> getAllEmptyPlaces();
    Place getPlaceById(long id);
    Place updatePlace(Place place, long id);
    void deletePlace(long id);
}
