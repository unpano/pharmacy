package isa.pharmacy.Services;

import isa.pharmacy.Models.Promotion;

import java.util.Optional;

public interface PromotionService {

    Promotion add(Promotion promotion);

    Optional<Promotion> findById(Long id);
}
