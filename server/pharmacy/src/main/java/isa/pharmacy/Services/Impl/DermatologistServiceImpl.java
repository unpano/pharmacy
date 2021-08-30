package isa.pharmacy.Services.Impl;

import isa.pharmacy.Models.Dermatologist;
import isa.pharmacy.Models.WhomRates;
import isa.pharmacy.Repositories.DermatologistRepository;
import isa.pharmacy.Services.DermatologistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DermatologistServiceImpl implements DermatologistService {

    @Autowired
    private DermatologistRepository dermatologistRepository;

    @Override
    public Optional<Dermatologist> findById(Long dermatologistId) {
        return dermatologistRepository.findById(dermatologistId);
    }

    @Override
    public Dermatologist save(Dermatologist dermatologist) {
        return dermatologistRepository.save(dermatologist);
    }



}
