package isa.pharmacy.Services;

import isa.pharmacy.Models.Promotion;

import java.util.List;
import java.util.Optional;

public interface PromotionService {

    Promotion add(Promotion promotion);

    List<Promotion> findAllByPharmacyId(Long id);

    Optional<Promotion> findById(Long id);
}
