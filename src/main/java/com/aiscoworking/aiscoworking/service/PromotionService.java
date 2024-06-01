package com.aiscoworking.aiscoworking.service;

import com.aiscoworking.aiscoworking.model.Promotion;

import java.util.List;

public interface PromotionService {
    Promotion savePromotion(Promotion promotion);
    List<Promotion> getAllPromotions();
    Promotion getPromotionById(long id);
    Promotion updatePromotion(Promotion promotion, long id);
    void deletePromotion(long id);
}
