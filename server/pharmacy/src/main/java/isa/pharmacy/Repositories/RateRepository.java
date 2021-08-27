package isa.pharmacy.Repositories;

import isa.pharmacy.Models.Rate;
import isa.pharmacy.Models.WhomRates;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RateRepository extends JpaRepository<Rate,Long> {
    List<Rate> findAllByUserIdAndWhomRates(Long id, WhomRates dermatologist);

    Optional<Rate> findByUserIdAndIdOfRatedObjectAndWhomRates(Long id, Long objectId, WhomRates whom);

    List<Rate> findAllByIdOfRatedObjectAndWhomRates(Long objId, WhomRates whomRates);
}
