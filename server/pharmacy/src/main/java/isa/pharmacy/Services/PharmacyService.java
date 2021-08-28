package isa.pharmacy.Services;

import isa.pharmacy.Models.Pharmacy;

import java.util.List;
import java.util.Optional;


public interface PharmacyService {


    Object findAll();

    Optional<Pharmacy> findById(Long id);

    List<Pharmacy> findByCriteria(String searchItem);

    Pharmacy save(Pharmacy pharmacy);

    List<Pharmacy> findPharmaciesByMedId(Long id);

    List<Pharmacy> findPharmaciesToRate(Long id);

    List<Pharmacy> findRatedPharmacies(Long id);
}
