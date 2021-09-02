package isa.pharmacy.Services.Impl;

import isa.pharmacy.Models.*;
import isa.pharmacy.Repositories.MedRepository;
import isa.pharmacy.Repositories.PharmacyRepository;
import isa.pharmacy.Repositories.VacationRepository;
import isa.pharmacy.Services.MedService;
import isa.pharmacy.Services.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.saml2.Saml2RelyingPartyProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.persistence.OptimisticLockException;
import java.util.ArrayList;
import java.util.List;


@Service
public class VacationServiceImpl implements VacationService {

    @Autowired
    private VacationRepository vacationRepository;

    @Autowired
    private PharmacyRepository pharmacyRepository;




    @Override
    public List<VacationRequest> findVacationsByPharmacyId(Long id)
    {
        Pharmacy pharm = pharmacyRepository.findById(id).get();
        List<VacationRequest> vacations = pharm.getVacations();


        return vacations;
    }

    @Override
    public GeneralUser findWorkerName(Long id)
    {
        Dermatologist d = vacationRepository.findById(id).get().getDermatologist();

        GeneralUser u = d;

        if(d == null)
        {
            Pharmacist p =  vacationRepository.findById(id).get().getPharmacist();
            u = p;
        }



        return u;
    }





    @Override
    public Object approve(VacationRequest v )
    {
        VacationRequest vacationRequest = vacationRepository.findById(v.getId()).get();

        if( vacationRequest.isAnswered() )
        {
            throw new OptimisticLockException();
        }
        v.setApproved(true);
        v.setAnswered(true );
        return vacationRepository.save(v);
    }

    @Override
    public Object decline(VacationRequest v )
    {
        VacationRequest vacationRequest = vacationRepository.findById(v.getId()).get();
        if( vacationRequest.isAnswered() )
        {
            throw new OptimisticLockException();
        }

        v.setApproved(false);
        v.setAnswered(true);
        return vacationRepository.save(v);
    }
}

