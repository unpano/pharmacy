package isa.pharmacy.Services.Impl;

import isa.pharmacy.Models.*;
import isa.pharmacy.Repositories.DermatologistRepository;
import isa.pharmacy.Repositories.PharmacyRepository;
import isa.pharmacy.Services.AuthorityService;
import isa.pharmacy.Services.DermatologistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class DermatologistServiceImpl implements DermatologistService
{
    @Autowired
    private DermatologistRepository dermatologistRepository;

    @Autowired
    private AuthorityService authService;

    @Autowired
    private PharmacyRepository pharmacyRepository;



    @Override
    public Dermatologist addMewDermatologist(Dermatologist d)
    {

        List<Authority> auth = authService.findByName("ROLE_DERMATOLOGIST");
        d.setAuthorities(auth);

        return dermatologistRepository.save(d);
    }

    @Override
    public List<Dermatologist> findDermatologistByPharmacyId(Long id)
    {
        Pharmacy pharm = pharmacyRepository.findById(id).get();
        List<PharmacyDermatologist> pd = pharm.getDermatologists();

        List<Dermatologist> dermatologists = new ArrayList<>();
        for (int i =0; i< pd.size(); i++){
            dermatologists.add(pd.get(i).getDermatologist());
        }

        return dermatologists;

    }

}




