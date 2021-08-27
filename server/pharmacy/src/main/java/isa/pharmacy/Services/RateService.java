package isa.pharmacy.Services;

import isa.pharmacy.Models.Rate;
import isa.pharmacy.Models.WhomRates;

import java.util.List;
import java.util.Optional;

public interface RateService {
    Object addRate(Rate rate1);

    List<Rate> findAllByUserIdAndWhomRates(Long id, WhomRates dermatologist);

    List<Rate> findAll();

    Optional<Rate> findById(Long rateId);

    Rate update(Rate rate1);

    Optional<Rate> findByUserIdAndIdOfRatedObjectAndWhomRates(Long id, Long objectId, WhomRates whom);
}
