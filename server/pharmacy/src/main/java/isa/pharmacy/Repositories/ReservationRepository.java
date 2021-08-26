package isa.pharmacy.Repositories;

import isa.pharmacy.Models.Med;
import isa.pharmacy.Models.Pharmacy;
import isa.pharmacy.Models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ReservationRepository extends JpaRepository<Reservation,Long>{
    List<Reservation> findAllByUserId(Long id);
}
