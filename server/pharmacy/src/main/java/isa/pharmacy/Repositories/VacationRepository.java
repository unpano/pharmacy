package isa.pharmacy.Repositories;

import isa.pharmacy.Models.VacationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacationRepository extends JpaRepository<VacationRequest,Long> {
}


