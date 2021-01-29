package isa.pharmacy.Services;

import isa.pharmacy.Models.Pharmacy;
import isa.pharmacy.Repositories.PharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PharmacyService {

    @Autowired
    private PharmacyRepository pharmacyRepository;

    public List<Pharmacy> findAll() {
        return pharmacyRepository.findAll();
    }
    public Optional<Pharmacy> findById(Long id){ return pharmacyRepository.findById(id); }
}
