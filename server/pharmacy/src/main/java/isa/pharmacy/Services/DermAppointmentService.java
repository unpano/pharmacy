package isa.pharmacy.Services;

import isa.pharmacy.Models.DermAppointment;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DermAppointmentService {
    List<DermAppointment> findAll();

    List<DermAppointment> findAppointmentsByPharmacyId(Long id);
}
