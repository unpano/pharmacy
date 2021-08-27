package isa.pharmacy.Services;

import isa.pharmacy.Models.Dermatologist;

import java.util.Optional;

public interface DermatologistService {
    Optional<Dermatologist> findById(Long dermatologistId);

    Dermatologist save(Dermatologist dermatologist);
}
