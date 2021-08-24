package isa.pharmacy.Services;

import isa.pharmacy.Models.DateTimeJSON;
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


public interface PharmacyService {


    Object findAll();

    Optional<Pharmacy> findById(Long id);

    List<Pharmacy> findByCriteria(String searchItem);

}
