package com.aiscoworking.aiscoworking.service.impl;

import com.aiscoworking.aiscoworking.exception.ResourceNotFoundException;
import com.aiscoworking.aiscoworking.model.Coworking;
import com.aiscoworking.aiscoworking.model.MarketingSurvey;
import com.aiscoworking.aiscoworking.repository.MarketingSurveyRepository;
import com.aiscoworking.aiscoworking.service.MarketingSurveyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketingSurveyServiceImpl implements MarketingSurveyService {

    private final MarketingSurveyRepository marketingSurveyRepository;

    public MarketingSurveyServiceImpl(MarketingSurveyRepository marketingSurveyRepository) {
        super();
        this.marketingSurveyRepository = marketingSurveyRepository;
    }

    @Override
    public MarketingSurvey saveMarketingSurvey(MarketingSurvey marketingSurvey) {
        return marketingSurveyRepository.save(marketingSurvey);
    }

    @Override
    public List<MarketingSurvey> getAllMarketingSurveys() {
        return marketingSurveyRepository.findAll();
    }

    @Override
    public MarketingSurvey getMarketingSurveyById(long id) {
        return marketingSurveyRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("MarketingSurvey", "Id", id));
    }

    @Override
    public MarketingSurvey updateMarketingSurvey(MarketingSurvey marketingSurvey, long id) {
        // check whether marketingSurvey with given id is exist in DB or not
        MarketingSurvey existingMarketingSurvey = marketingSurveyRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("MarketingSurvey", "Id", id));
        existingMarketingSurvey.setName(marketingSurvey.getName());
        existingMarketingSurvey.setResult(marketingSurvey.getResult());
        // save exsiting marketingSurvey
        marketingSurveyRepository.save(existingMarketingSurvey);
        return existingMarketingSurvey;
    }

    @Override
    public void deleteMarketingSurvey(long id) {
        // check whether marketingSurvey with given id is exist in DB or not
        marketingSurveyRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("MarketingSurvey", "Id", id));
        marketingSurveyRepository.deleteById(id);
    }
}
