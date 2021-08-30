package isa.pharmacy.Repositories;

import isa.pharmacy.Models.Authority;
import isa.pharmacy.Models.Dermatologist;
import isa.pharmacy.Models.WhomRates;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DermatologistRepository extends JpaRepository<Dermatologist, Long> {
}
