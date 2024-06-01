package com.aiscoworking.aiscoworking.service.impl;

import com.aiscoworking.aiscoworking.exception.ResourceNotFoundException;
import com.aiscoworking.aiscoworking.model.Coworking;
import com.aiscoworking.aiscoworking.model.Promotion;
import com.aiscoworking.aiscoworking.repository.PromotionRepository;
import com.aiscoworking.aiscoworking.service.PromotionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRepository promotionRepository;

    public PromotionServiceImpl(PromotionRepository promotionRepository) {
        super();
        this.promotionRepository = promotionRepository;
    }

    @Override
    public Promotion savePromotion(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    @Override
    public List<Promotion> getAllPromotions() {
        return promotionRepository.findAll();
    }

    @Override
    public Promotion getPromotionById(long id) {
        return promotionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Promotion", "Id", id));
    }

    @Override
    public Promotion updatePromotion(Promotion promotion, long id) {
        // check whether promotion with given id is exist in DB or not
        Promotion existingPromotion = promotionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Promotion", "Id", id));
        existingPromotion.setName(promotion.getName());
        existingPromotion.setDiscount(promotion.getDiscount());
        // save exsiting promotion
        promotionRepository.save(existingPromotion);
        return existingPromotion;
    }

    @Override
    public void deletePromotion(long id) {
        // check whether promotion with given id is exist in DB or not
        promotionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Promotion", "Id", id));
        promotionRepository.deleteById(id);
    }
}
