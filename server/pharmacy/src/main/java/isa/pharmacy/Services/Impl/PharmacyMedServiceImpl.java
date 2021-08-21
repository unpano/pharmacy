package isa.pharmacy.Services.Impl;

import isa.pharmacy.Models.PharmacyMed;
import isa.pharmacy.Repositories.PharmacyMedRepository;
import isa.pharmacy.Services.PharmacyMedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PharmacyMedServiceImpl implements PharmacyMedService {
    @Autowired
    private PharmacyMedRepository pharmacyMedRepository;

    @Override
    public Optional<PharmacyMed> findByMedIdAndPharmacyId(Long medId, Long pharmacyId) {
        return pharmacyMedRepository.findByMedIdAndPharmacyId(medId,pharmacyId);
    }

    @Override
    public PharmacyMed update(PharmacyMed pharmacyMed) {
        return pharmacyMedRepository.save(pharmacyMed);
    }
}
