package com.aiscoworking.aiscoworking.service;

import com.aiscoworking.aiscoworking.model.Coworking;
import com.aiscoworking.aiscoworking.model.Place;

import java.util.List;

public interface CoworkingService {
    Coworking saveCoworking(Coworking coworking);
    List<Coworking> getAllCoworking();
    Coworking getCoworkingById(long id);
    List<Place> getAllCoworkingPlaces(long id);
    List<Place> getAllCoworkingEmptyPlaces(long id);
    Coworking updateCoworking(Coworking coworking, long id);
    void deleteCoworking(long id);
}
