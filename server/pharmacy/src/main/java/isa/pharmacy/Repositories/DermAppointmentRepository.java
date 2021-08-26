package isa.pharmacy.Repositories;

import isa.pharmacy.Models.DermAppointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DermAppointmentRepository extends JpaRepository<DermAppointment,Long> {
    List<DermAppointment> findAllByPharmacyIdAndUserId(Long pharmacyId,Long userId);
    Optional<DermAppointment> findById(Long id);
    List<DermAppointment> findAllByUserId(Long id);

    List<DermAppointment> findAllByDermatologistIdAndUserId(Long dermId, Long userId);
}
