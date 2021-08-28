package isa.pharmacy.Services;

import isa.pharmacy.Models.Med;
import isa.pharmacy.Models.Pharmacy;

import java.util.List;
import java.util.Optional;

public interface MedService {
    List<Med> findAll();

    Optional<Med> findById(Long id);

    Med save(Med med);


    List<Med> findMedsByPharmacyId(Long id);

    List<Med> findMedsToRate(Long id);

    List<Med> findRatedMeds(Long id);
}
