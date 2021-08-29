package isa.pharmacy.Services.Impl;

import isa.pharmacy.Models.Med;
import isa.pharmacy.Models.Promotion;

import isa.pharmacy.Repositories.PromotionRepository;

import isa.pharmacy.Services.PromotionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PromotionServiceImpl implements PromotionService {


        @Autowired
        private PromotionRepository promotionRepository;

    @Override
    public Promotion add(Promotion p)
    {
        return promotionRepository.save(p);
    }

    @Override
    public Optional<Promotion> findById(Long id) {
        return promotionRepository.findById(id);
    }


}
