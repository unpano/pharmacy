package isa.pharmacy.Services.Impl;

import isa.pharmacy.Models.DermAppointment;
import isa.pharmacy.Repositories.DermAppointmentRepository;
import isa.pharmacy.Services.DermAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DermAppointmentServiceImpl implements DermAppointmentService {

    @Autowired
    private DermAppointmentRepository dermAppointmentRepository;

    @Override
    public List<DermAppointment> findAll() {
        return dermAppointmentRepository.findAll();
    }

    @Override
    public List<DermAppointment> findAppointmentsByPharmacyId(Long id) {
        return dermAppointmentRepository.findAllByPharmacyId(id);
    }
}
