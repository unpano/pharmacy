package isa.pharmacy.Repositories;

import isa.pharmacy.Models.Pharmacist;
import isa.pharmacy.Models.PharmacyMed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PharmacyPharmacistsRepository extends JpaRepository<Pharmacist,Long> {

    public List<Pharmacist> findAllByPharmacyId(Long id);
}
