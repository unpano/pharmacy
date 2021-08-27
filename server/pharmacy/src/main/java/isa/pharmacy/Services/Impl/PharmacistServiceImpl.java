package isa.pharmacy.Services.Impl;

import isa.pharmacy.Models.Pharmacist;
import isa.pharmacy.Repositories.PharmacistRepository;
import isa.pharmacy.Services.PharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PharmacistServiceImpl implements PharmacistService {

    @Autowired
    private PharmacistRepository pharmacistRepository;

    @Override
    public Optional<Pharmacist> findById(Long objectId) {
        return pharmacistRepository.findById(objectId);
    }

    @Override
    public Pharmacist save(Pharmacist pharmacist) {
        return pharmacistRepository.save(pharmacist);
    }
}
