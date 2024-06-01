package com.aiscoworking.aiscoworking.controller;

import com.aiscoworking.aiscoworking.model.Promotion;
import com.aiscoworking.aiscoworking.service.PromotionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/promotion")
public class PromotionController {
    private final PromotionService promotionService;

    public PromotionController(PromotionService promotionService) {
        super();
        this.promotionService = promotionService;
    }

    // build create Promotion Rest Api
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OWNER')")
    @PostMapping()
    public ResponseEntity<Promotion> savePromotion(@RequestBody Promotion promotion) {
        return new ResponseEntity<Promotion>(promotionService.savePromotion(promotion), HttpStatus.CREATED);
    }

    // build get all Promotions Rest Api
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OWNER')")
    @GetMapping()
    public List<Promotion> getAllPromotions() {
        return promotionService.getAllPromotions();
    }

    // build get Promotion by ID Rest Api
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OWNER')")
    @GetMapping("{id}")
    public ResponseEntity<Promotion> getPromotionById(@PathVariable("id") long id) {
        return new ResponseEntity<Promotion>(promotionService.getPromotionById(id), HttpStatus.OK);
    }

    // build update Promotion Rest Api
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OWNER')")
    @PutMapping("{id}")
    public ResponseEntity<Promotion> updatePromotion(@PathVariable("id") long id, @RequestBody Promotion promotion) {
        return new ResponseEntity<Promotion>(promotionService.updatePromotion(promotion, id), HttpStatus.OK);
    }

    // build delete Promotion Rest Api
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OWNER')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePromotion(@PathVariable("id") long id) {
        // delete Promotion from DB
        promotionService.deletePromotion(id);
        return new ResponseEntity<String>("Promotion deleted successfully!", HttpStatus.OK);
    }
}
