package com.aiscoworking.aiscoworking.controller;

import com.aiscoworking.aiscoworking.model.*;
import com.aiscoworking.aiscoworking.service.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
class PlaceHistoryWrapper {
    private long price;
    private long place;
    private long aisUser;
    private long marketingSurvey;
    private long promotion;
}

@RestController
@RequestMapping("api/placeHistory")
public class PlaceHistoryController {
    private final PlaceHistoryService placeHistoryService;
    private final PlaceService placeService;
    private final AisUserService aisUserService;
    private final MarketingSurveyService marketingSurveyService;
    private final PromotionService promotionService;

    public PlaceHistoryController(PlaceHistoryService placeHistoryService,
                                  PlaceService placeService,
                                  AisUserService aisUserService,
                                  MarketingSurveyService marketingSurveyService,
                                  PromotionService promotionService) {
        super();
        this.placeHistoryService = placeHistoryService;
        this.placeService = placeService;
        this.aisUserService = aisUserService;
        this.marketingSurveyService = marketingSurveyService;
        this.promotionService = promotionService;
    }

    // build create PlaceHistory Rest Api
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OWNER', 'ROLE_COWORKING_OWNER')")
    @PostMapping()
    public ResponseEntity<PlaceHistory> savePlaceHistory(@RequestBody PlaceHistoryWrapper placeHistoryWrapper) {
        PlaceHistory placeHistory = new PlaceHistory(placeHistoryWrapper.getPrice());
        Place place = placeService.getPlaceById(placeHistoryWrapper.getPlace());
        AisUser aisUser = aisUserService.getAisUserById(placeHistoryWrapper.getAisUser());
        MarketingSurvey marketingSurvey = marketingSurveyService.getMarketingSurveyById(placeHistoryWrapper.getMarketingSurvey());
        Promotion promotion = promotionService.getPromotionById(placeHistoryWrapper.getPromotion());
        placeHistory.setPlace(place);
        placeHistory.setAisUser(aisUser);
        placeHistory.setMarketingSurvey(marketingSurvey);
        placeHistory.setPromotion(promotion);
        return new ResponseEntity<PlaceHistory>(placeHistoryService.savePlaceHistory(placeHistory), HttpStatus.CREATED);
    }

    // build get all PlaceHistory Rest Api
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OWNER')")
    @GetMapping()
    public List<PlaceHistory> getAllPlaceHistory() {
        return placeHistoryService.getAllPlaceHistory();
    }

    // build get PlaceHistory by ID Rest Api
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OWNER')")
    @GetMapping("{id}")
    public ResponseEntity<PlaceHistory> getPlaceHistoryById(@PathVariable("id") long id) {
        return new ResponseEntity<PlaceHistory>(placeHistoryService.getPlaceHistoryById(id), HttpStatus.OK);
    }

    // build update PlaceHistory Rest Api
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OWNER')")
    @PutMapping("{id}")
    public ResponseEntity<PlaceHistory> updatePlaceHistory(@PathVariable("id") long id,
                                                           @RequestBody PlaceHistoryWrapper placeHistoryWrapper) {
        PlaceHistory placeHistory = new PlaceHistory(placeHistoryWrapper.getPrice());
        Place place = placeService.getPlaceById(placeHistoryWrapper.getPlace());
        AisUser aisUser = aisUserService.getAisUserById(placeHistoryWrapper.getAisUser());
        MarketingSurvey marketingSurvey = marketingSurveyService.getMarketingSurveyById(placeHistoryWrapper.getMarketingSurvey());
        Promotion promotion = promotionService.getPromotionById(placeHistoryWrapper.getPromotion());
        placeHistory.setPlace(place);
        placeHistory.setAisUser(aisUser);
        placeHistory.setMarketingSurvey(marketingSurvey);
        placeHistory.setPromotion(promotion);
        return new ResponseEntity<PlaceHistory>(placeHistoryService.updatePlaceHistory(placeHistory, id),
                HttpStatus.OK);
    }

    // build delete PlaceHistory Rest Api
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OWNER')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePlaceHistory(@PathVariable("id") long id) {
        // delete PlaceHistory from DB
        placeHistoryService.deletePlaceHistory(id);
        return new ResponseEntity<String>("PlaceHistory deleted successfully!", HttpStatus.OK);
    }
}
