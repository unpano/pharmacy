package isa.pharmacy.Services.Impl;

import isa.pharmacy.Models.*;
import isa.pharmacy.Repositories.AuthorityRepository;
import isa.pharmacy.Repositories.PharmacistRepository;
import isa.pharmacy.Repositories.PharmacyRepository;
import isa.pharmacy.Repositories.UserRepository;
import isa.pharmacy.Services.AuthorityService;
import isa.pharmacy.Services.PharmacistService;
import isa.pharmacy.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PharmacistServiceImpl implements PharmacistService {

        @Autowired
        private PharmacistRepository pharmacistRepository;

    @Autowired
    private AuthorityService authService;

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Override
    public Pharmacist addMewPharmacist(Pharmacist p  )
    {

        List<Authority> auth = authService.findByName("ROLE_PHARMACIST");
        p.setAuthorities(auth);

        return pharmacistRepository.save(p);
    }

    @Override
    public List<Pharmacist> findPharmacistsByPharmacyId(Long id)
    {
        Pharmacy pharm = pharmacyRepository.findById(id).get();
        System.out.println(pharm.getId());

        List<Pharmacist> pharmacists = pharmacistRepository.findAll();
        System.out.println(pharmacists);



        for( Pharmacist p : pharmacists)
        {
            if(p.getPharmacy() !=  null)
            {
                if( p.getPharmacy().getId() != id)
                {
                    pharmacists.remove(p);
                }
            }

        }




        return pharmacists;
    }

}
