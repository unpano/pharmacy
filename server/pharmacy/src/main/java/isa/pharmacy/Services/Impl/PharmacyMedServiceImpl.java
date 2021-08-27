package isa.pharmacy.Services.Impl;

import isa.pharmacy.Models.Pharmacy;
import isa.pharmacy.Models.PharmacyMed;
import isa.pharmacy.Models.Reservation;
import isa.pharmacy.Models.User;
import isa.pharmacy.Repositories.MedRepository;
import isa.pharmacy.Repositories.PharmacyMedRepository;
import isa.pharmacy.Repositories.PharmacyRepository;
import isa.pharmacy.Services.PharmacyMedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PharmacyMedServiceImpl implements PharmacyMedService {
    @Autowired
    private PharmacyMedRepository pharmacyMedRepository;
    @Autowired
    private  PharmacyRepository pharmacyRepository;

    @Autowired
    private MedRepository medRepository;

    @Override
    public Optional<PharmacyMed> findByMedIdAndPharmacyId(Long medId, Long pharmacyId) {
        return pharmacyMedRepository.findByMedIdAndPharmacyId(medId,pharmacyId);
    }




    @Override
    public PharmacyMed update(PharmacyMed pharmacyMed) {
        return pharmacyMedRepository.save(pharmacyMed);
    }
}
