package isa.pharmacy.Services;

import isa.pharmacy.Models.Pharmacist;

import java.util.Optional;

public interface PharmacistService {
    Optional<Pharmacist> findById(Long objectId);

    Pharmacist save(Pharmacist pharmacist);
}
