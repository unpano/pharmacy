package isa.pharmacy.Repositories;

import isa.pharmacy.Models.DermAppointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DermAppointmentRepository extends JpaRepository<DermAppointment,Long> {
    List<DermAppointment> findAllByPharmacyIdAndUserId(Long pharmacyId,Long userId);
    Optional<DermAppointment> findById(Long id);
    List<DermAppointment> findAllByUserId(Long id);
        //naci sve termine koji su zauzeti za apoteku, da bi mogli naci one koji su slobodni
    List<DermAppointment> findAllByPharmacyId(Long pharmacyId);
}
