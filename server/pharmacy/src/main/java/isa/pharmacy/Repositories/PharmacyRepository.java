package isa.pharmacy.Repositories;

import isa.pharmacy.Models.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PharmacyRepository extends JpaRepository<Pharmacy,Long> {
    public List<Pharmacy> findAllByCity(String city);
    public List<Pharmacy> findAllByName(String name);
}
