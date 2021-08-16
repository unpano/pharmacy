package isa.pharmacy.Repositories;

import isa.pharmacy.Models.Med;
import isa.pharmacy.Models.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedRepository extends JpaRepository<Med,Long> {

}
