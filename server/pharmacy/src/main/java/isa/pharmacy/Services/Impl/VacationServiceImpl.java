package isa.pharmacy.Services.Impl;

import isa.pharmacy.Models.Dermatologist;
import isa.pharmacy.Models.Pharmacist;
import isa.pharmacy.Models.Pharmacy;
import isa.pharmacy.Models.VacationRequest;
import isa.pharmacy.Repositories.MedRepository;
import isa.pharmacy.Repositories.PharmacyRepository;
import isa.pharmacy.Repositories.VacationRepository;
import isa.pharmacy.Services.MedService;
import isa.pharmacy.Services.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

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
    public String findWorkerName(Long id)
    {
        Dermatologist d = vacationRepository.findById(id).get().getDermatologist();

        String name = d.getFirstName();

        if(d == null)
        {
            Pharmacist p =  vacationRepository.findById(id).get().getPharmacist();
            name = p.getFirstName();
        }



        return name;
    }

    @Override
    public Object approve(VacationRequest v )
    {
        v.setApproved(true);
        v.setAnswered(true);
        return vacationRepository.save(v);
    }

    @Override
    public Object decline(VacationRequest v )
    {
        v.setApproved(false);
        v.setAnswered(true);
        return vacationRepository.save(v);
    }
}

