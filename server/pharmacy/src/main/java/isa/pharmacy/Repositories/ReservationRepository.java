package isa.pharmacy.Repositories;

import isa.pharmacy.Models.Pharmacy;
import isa.pharmacy.Models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReservationRepository extends JpaRepository<Reservation,Long>{
}
