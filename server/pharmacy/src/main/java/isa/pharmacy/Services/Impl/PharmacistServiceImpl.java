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
        List<Pharmacist> pharmacists = pharm.getPharmacists();

        List<Pharmacist> p_return = new ArrayList<>();



        //za svakog farmaceuta proveravamo da li je stv to i njegova uloga

        for( Pharmacist p : pharmacists)
        {
            if( ! p.getAuthorities().isEmpty() )
            {
                for( GrantedAuthority a  : p.getAuthorities())
                {
                    if( a.getAuthority().equals("ROLE_PHARMACIST"))
                    {
                        p_return.add(p);
                    }
                }

            }
        }

        return p_return;
    }

}
