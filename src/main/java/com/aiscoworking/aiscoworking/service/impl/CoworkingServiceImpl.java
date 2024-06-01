package com.aiscoworking.aiscoworking.service.impl;

import com.aiscoworking.aiscoworking.exception.ResourceNotFoundException;
import com.aiscoworking.aiscoworking.model.AisUserGroup;
import com.aiscoworking.aiscoworking.model.Coworking;
import com.aiscoworking.aiscoworking.model.MarketingSurvey;
import com.aiscoworking.aiscoworking.model.Place;
import com.aiscoworking.aiscoworking.repository.CoworkingRepository;
import com.aiscoworking.aiscoworking.repository.PlaceRepository;
import com.aiscoworking.aiscoworking.service.CoworkingService;
import com.aiscoworking.aiscoworking.service.MarketingSurveyService;
import com.aiscoworking.aiscoworking.service.PlaceService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoworkingServiceImpl implements CoworkingService {

    private final CoworkingRepository coworkingRepository;
    private final PlaceService placeService;

    public CoworkingServiceImpl(CoworkingRepository coworkingRepository, PlaceService placeService) {
        super();
        this.coworkingRepository = coworkingRepository;
        this.placeService = placeService;
    }

    @Override
    public Coworking saveCoworking(Coworking coworking) {
        return coworkingRepository.save(coworking);
    }

    @Override
    public List<Place> getAllCoworkingPlaces(long id) {
        List<Place> coworkingPlaces = new ArrayList<>();
        for ( Place place : placeService.getAllPlaces() ) {
            if (place.getCoworking() == null) continue;
            if (place.getCoworking().getId() == id) {
                coworkingPlaces.add(place);
            }
        }
        return coworkingPlaces;
    }

    @Override
    public List<Place> getAllCoworkingEmptyPlaces(long id) {
        List<Place> coworkingEmptyPlaces = new ArrayList<>();
        for ( Place place : placeService.getAllEmptyPlaces() ) {
            if (place.getCoworking() == null) continue;
            if (place.getCoworking().getId() == id) {
                coworkingEmptyPlaces.add(place);
            }
        }
        return coworkingEmptyPlaces;
    }

    @Override
    public List<Coworking> getAllCoworking() {
        return coworkingRepository.findAll();
    }

    @Override
    public Coworking getCoworkingById(long id) {
        return coworkingRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Coworking", "Id", id));
    }

    @Override
    public Coworking updateCoworking(Coworking coworking, long id) {
        // check whether coworking with given id is exist in DB or not
        Coworking existingCoworking = coworkingRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Coworking", "Id", id));
        existingCoworking.setName(coworking.getName());
        existingCoworking.setCity(coworking.getCity());
        existingCoworking.setCoordx(coworking.getCoordx());
        existingCoworking.setCoordy(coworking.getCoordy());
        existingCoworking.setLikeNumber(coworking.getLikeNumber());
        // save exsiting coworking
        coworkingRepository.save(existingCoworking);
        return existingCoworking;
    }

    @Override
    public void deleteCoworking(long id) {
        // check whether coworking with given id is exist in DB or not
        coworkingRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Coworking", "Id", id));
        coworkingRepository.deleteById(id);
    }
}
