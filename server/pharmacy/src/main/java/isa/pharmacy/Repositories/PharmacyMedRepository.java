package isa.pharmacy.Repositories;

import isa.pharmacy.Models.Pharmacy;
import isa.pharmacy.Models.PharmacyMed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PharmacyMedRepository extends JpaRepository<PharmacyMed,Long> {
    Optional<PharmacyMed> findByMedIdAndPharmacyId(Long medId, Long pharmacyId);
}
