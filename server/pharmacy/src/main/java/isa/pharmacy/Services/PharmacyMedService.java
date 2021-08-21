package isa.pharmacy.Services;

import isa.pharmacy.Models.PharmacyMed;

import java.util.Optional;

public interface PharmacyMedService {
    Optional<PharmacyMed> findByMedIdAndPharmacyId(Long medId, Long pharmacyId);
    PharmacyMed update(PharmacyMed pharmacyMed);
}
