package com.aiscoworking.aiscoworking.service;

import com.aiscoworking.aiscoworking.model.PlaceHistory;

import java.util.List;

public interface PlaceHistoryService {
    PlaceHistory savePlaceHistory(PlaceHistory placeHistory);
    List<PlaceHistory> getAllPlaceHistory();
    PlaceHistory getPlaceHistoryById(long id);
    PlaceHistory updatePlaceHistory(PlaceHistory placeHistory, long id);
    void deletePlaceHistory(long id);
}
