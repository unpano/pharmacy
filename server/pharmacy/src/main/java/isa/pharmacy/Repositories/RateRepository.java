package isa.pharmacy.Repositories;

import isa.pharmacy.Models.Rate;
import isa.pharmacy.Models.WhomRates;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RateRepository extends JpaRepository<Rate,Long> {
    List<Rate> findAllByUserIdAndWhomRates(Long id, WhomRates dermatologist);
}
