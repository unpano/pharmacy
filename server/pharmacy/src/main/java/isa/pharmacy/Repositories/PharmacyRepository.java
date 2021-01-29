package isa.pharmacy.Repositories;

import isa.pharmacy.Models.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PharmacyRepository extends JpaRepository<Pharmacy,Long> {

}
