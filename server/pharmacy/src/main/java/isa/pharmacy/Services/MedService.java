package isa.pharmacy.Services;

import isa.pharmacy.Models.Med;

import java.util.List;
import java.util.Optional;

public interface MedService {
    List<Med> findAll();

    Optional<Med> findById(Long id);

    Optional<Med> update(Med med);
}
