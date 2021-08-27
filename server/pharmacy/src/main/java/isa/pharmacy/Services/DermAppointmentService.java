package isa.pharmacy.Services;

import isa.pharmacy.Models.DermAppointment;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface DermAppointmentService {
    List<DermAppointment> findAll();
    Optional<DermAppointment> findById(Long id);
    DermAppointment update(DermAppointment dermAppointment);
    List<DermAppointment> findFreeAppointmentsByPharmacyId(Long id);

    DermAppointment addNewAppointment(DermAppointment dermAppointment);

    List<DermAppointment> findFutureAppointmentsByUserId(Long id);

    List<DermAppointment> findPastAppointmentsByUserId(Long id);

    Optional<DermAppointment> freeDermAppointment(Long id, Long id1);

    List<DermAppointment> findAllByUserId(Long id);


}
