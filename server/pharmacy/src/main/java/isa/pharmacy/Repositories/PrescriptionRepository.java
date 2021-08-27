package isa.pharmacy.Repositories;

import isa.pharmacy.Models.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription,Long>{
    List<Prescription> findAllByUserId(Long id);
}
