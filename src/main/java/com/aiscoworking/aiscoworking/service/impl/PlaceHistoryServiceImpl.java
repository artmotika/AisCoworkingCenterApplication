package com.aiscoworking.aiscoworking.service.impl;

import com.aiscoworking.aiscoworking.exception.ResourceNotFoundException;
import com.aiscoworking.aiscoworking.model.*;
import com.aiscoworking.aiscoworking.repository.PlaceHistoryRepository;
import com.aiscoworking.aiscoworking.service.PlaceHistoryService;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceHistoryServiceImpl implements PlaceHistoryService {

    private final PlaceHistoryRepository placeHistoryRepository;

    public PlaceHistoryServiceImpl(PlaceHistoryRepository placeHistoryRepository) {
        super();
        this.placeHistoryRepository = placeHistoryRepository;
    }

    @Override
    public PlaceHistory savePlaceHistory(PlaceHistory placeHistory) {
        double discount = (double) placeHistory.getPromotion().getDiscount();
        double sail = (double) placeHistory.getPrice() * ((100 - discount) / 100);
        long price = (long) sail;
        placeHistory.setPrice(price);
        return placeHistoryRepository.save(placeHistory);
    }

    @Override
    public List<PlaceHistory> getAllPlaceHistory() {
        return placeHistoryRepository.findAll();
    }

    @Override
    public PlaceHistory getPlaceHistoryById(long id) {
        return placeHistoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("PlaceHistory", "Id", id));
    }

    @Override
    public PlaceHistory updatePlaceHistory(PlaceHistory placeHistory, long id) {
        // check whether placeHistory with given id is exist in DB or not
        PlaceHistory existingPlaceHistory = placeHistoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("PlaceHistory", "Id", id));
        existingPlaceHistory.setPrice(placeHistory.getPrice());
        existingPlaceHistory.setPlace(placeHistory.getPlace());
        existingPlaceHistory.setAisUser(placeHistory.getAisUser());
        existingPlaceHistory.setMarketingSurvey(placeHistory.getMarketingSurvey());
        existingPlaceHistory.setPromotion(placeHistory.getPromotion());
        // save exsiting placeHistory
        placeHistoryRepository.save(existingPlaceHistory);
        return existingPlaceHistory;
    }

    @Override
    public void deletePlaceHistory(long id) {
        // check whether placeHistory with given id is exist in DB or not
        placeHistoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("PlaceHistory", "Id", id));
        placeHistoryRepository.deleteById(id);
    }
}
