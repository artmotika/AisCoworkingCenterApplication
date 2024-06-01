package com.aiscoworking.aiscoworking.service;

import com.aiscoworking.aiscoworking.model.MarketingSurvey;

import java.util.List;

public interface MarketingSurveyService {
    MarketingSurvey saveMarketingSurvey(MarketingSurvey marketingSurvey);
    List<MarketingSurvey> getAllMarketingSurveys();
    MarketingSurvey getMarketingSurveyById(long id);
    MarketingSurvey updateMarketingSurvey(MarketingSurvey marketingSurvey, long id);
    void deleteMarketingSurvey(long id);
}
