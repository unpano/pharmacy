package isa.pharmacy.Services;

import isa.pharmacy.Models.Prescription;

import java.util.List;

public interface PrescriptionService {
    List<Prescription> findAllByUserId(Long id);
}
