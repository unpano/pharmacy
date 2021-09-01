package isa.pharmacy.Repositories;

import isa.pharmacy.Models.PharmacyDermatologist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PharmacyDermatologistRepository extends JpaRepository<PharmacyDermatologist,Long> {
    Optional<PharmacyDermatologist> findByDermatologistIdAndPharmacyId(Long dermatologistId, Long pharmacyId);


    Optional<PharmacyDermatologist> deleteByDermatologistIdAndPharmacyId(Long dermatologistId, Long pharmacyId);
}


