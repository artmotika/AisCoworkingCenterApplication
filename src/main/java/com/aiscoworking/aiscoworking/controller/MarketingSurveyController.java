package com.aiscoworking.aiscoworking.controller;

import com.aiscoworking.aiscoworking.model.MarketingSurvey;
import com.aiscoworking.aiscoworking.service.MarketingSurveyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/marketingSurvey")
public class MarketingSurveyController {
    private final MarketingSurveyService marketingSurveyService;

    public MarketingSurveyController(MarketingSurveyService marketingSurveyService) {
        super();
        this.marketingSurveyService = marketingSurveyService;
    }

    // build create MarketingSurvey Rest Api
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OWNER')")
    @PostMapping()
    public ResponseEntity<MarketingSurvey> saveMarketingSurvey(@RequestBody MarketingSurvey marketingSurvey) {
        return new ResponseEntity<MarketingSurvey>(
                marketingSurveyService.saveMarketingSurvey(marketingSurvey), HttpStatus.CREATED);
    }

    // build get all MarketingSurvey Rest Api
    @GetMapping()
    public List<MarketingSurvey> getAllMarketingSurveys() {
        return marketingSurveyService.getAllMarketingSurveys();
    }

    // build get MarketingSurvey by ID Rest Api
    @GetMapping("{id}")
    public ResponseEntity<MarketingSurvey> getMarketingSurveyById(@PathVariable("id") long id) {
        return new ResponseEntity<MarketingSurvey>(marketingSurveyService.getMarketingSurveyById(id), HttpStatus.OK);
    }

    // build update MarketingSurvey Rest Api
    @PutMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MarketingSurvey> updateMarketingSurvey(@PathVariable("id") long id,
                                                           @RequestBody MarketingSurvey marketingSurvey) {
        return new ResponseEntity<MarketingSurvey>(
                marketingSurveyService.updateMarketingSurvey(marketingSurvey, id), HttpStatus.OK);
    }
}
