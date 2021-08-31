package isa.pharmacy.Services;

import isa.pharmacy.Models.Pharmacist;

import java.util.List;

public interface PharmacistService {

    Pharmacist addMewPharmacist(Pharmacist p  );

    List<Pharmacist> findPharmacistsByPharmacyId(Long id);
}
