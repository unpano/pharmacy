package isa.pharmacy.Services;

import isa.pharmacy.Models.DermAppointment;

import java.util.List;
import java.util.Optional;

public interface DermAppointmentService {
    List<DermAppointment> findAll();
    Optional<DermAppointment> findById(Long id);
    DermAppointment update(DermAppointment dermAppointment);
    List<DermAppointment> findAppointmentsByPharmacyId(Long id);
}
