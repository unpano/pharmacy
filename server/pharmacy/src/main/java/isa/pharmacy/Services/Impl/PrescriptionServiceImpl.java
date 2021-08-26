package isa.pharmacy.Services.Impl;

import isa.pharmacy.Models.Prescription;
import isa.pharmacy.Repositories.PrescriptionRepository;
import isa.pharmacy.Services.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionServiceImpl  implements PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Override
    public List<Prescription> findAllByUserId(Long id) {
        return prescriptionRepository.findAllByUserId(id);
    }
}
