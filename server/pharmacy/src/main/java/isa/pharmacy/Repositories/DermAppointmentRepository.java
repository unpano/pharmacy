package isa.pharmacy.Repositories;

import isa.pharmacy.Models.DermAppointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DermAppointmentRepository extends JpaRepository<DermAppointment,Long> {
    List<DermAppointment> findAllByPharmacyId(Long id);
}
