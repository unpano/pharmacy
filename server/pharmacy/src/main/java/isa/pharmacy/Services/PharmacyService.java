package isa.pharmacy.Services;

import isa.pharmacy.Models.Pharmacy;
import isa.pharmacy.Repositories.PharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class PharmacyService {

    @Autowired
    private PharmacyRepository pharmacyRepository;

    public List<Pharmacy> findAll() {
        return pharmacyRepository.findAll();
    }
    public Optional<Pharmacy> findById(Long id){ return pharmacyRepository.findById(id); }

    public List<Pharmacy> findByCriteria(String searchItem) {

        List<Pharmacy> newList = new ArrayList<>();

        //Search name or city
        List<Pharmacy> pharmaciesByName = pharmacyRepository.findAllByName(searchItem);
        List<Pharmacy> pharmaciesByCity = pharmacyRepository.findAllByCity(searchItem);

        newList = Stream.concat(pharmaciesByName.stream(), pharmaciesByCity.stream())
                .collect(Collectors.toList());

        return newList;
    }

}
