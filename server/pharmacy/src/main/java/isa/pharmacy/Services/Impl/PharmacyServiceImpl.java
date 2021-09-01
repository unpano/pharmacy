package isa.pharmacy.Services.Impl;

import isa.pharmacy.Models.*;
import isa.pharmacy.Repositories.*;
import isa.pharmacy.Services.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.sql.Time;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class PharmacyServiceImpl implements PharmacyService {


    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Autowired
    private PharmacyMedRepository pharmacyMedRepository;

    @Autowired
    private MedRepository medRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PharmacistRepository pharmacistRepository;

    @Autowired
    private PharmacyDermatologistRepository pharmacyDermatologistRepository;


    @Autowired
    private ReservationRepository reservationRepository;

    public List<Pharmacy> findAll() { return pharmacyRepository.findAll(); }



    public Optional<Pharmacy> findById(Long id){ return pharmacyRepository.findById(id); }





    @Override
    public Pharmacy findPharmacyByAdmin(Long adminId)
    {

        User admin = userRepository.findById(adminId).get();
        //nasli smo konkretnog admina

        Pharmacy p = admin.getPharmacy();


       return p;
    }






    @Override
    public Object deleteMed(Pharmacy ph, Long medId) {


        Optional<PharmacyMed> med = pharmacyMedRepository.findByMedIdAndPharmacyId(medId, ph.getId());

        Boolean i = false;

         List<Reservation> reservations = reservationRepository.findAll();

        for (Reservation res : reservations)
        {
            if(res.getPharmacy() == ph) {
                if (res.getMed().getId() == medId) {
                    i = true;
                }

            }
        }

        if(i == false) {
            List<PharmacyMed> meds = ph.getMeds();
            meds.remove(med);
            ph.setMeds(meds);
            pharmacyRepository.save(ph);


            pharmacyMedRepository.delete(med.get());

            return 0;
        }

        return 1;
    }





    @Override
    public Object deleteDermatologist(Pharmacy ph, Long dermatologistId) {


        PharmacyDermatologist dermatologist = pharmacyDermatologistRepository.findByDermatologistIdAndPharmacyId(dermatologistId, ph.getId()).get();


            List<PharmacyDermatologist> dermatologists = ph.getDermatologists();
            dermatologists.remove(dermatologist);
            ph.setDermatologists(dermatologists);
            pharmacyRepository.save(ph);


            pharmacyDermatologistRepository.delete(dermatologist);

            return 0;
    }





    @Override
    public Object deletePharmacist(Pharmacy ph, Long pharmacistId) {

        System.out.println(pharmacistId);

        Pharmacist pharmacist = pharmacistRepository.findById(pharmacistId).get();

            System.out.println(pharmacist.getId());

        pharmacist.setPharmacy(null);

        pharmacistRepository.save(pharmacist);

        return 0;
    }





    @Override
    public Pharmacy addMed(Pharmacy ph, Long medId) {

        //pronadjimo oznaceni lek
        Med m = medRepository.findById(medId).get();

        //formirajmo vezu izmedju apoteke i leka
        PharmacyMed postoji = pharmacyMedRepository.findByMedIdAndPharmacyId(medId, ph.getId()).orElse(null);

        PharmacyMed pm = new PharmacyMed();
        System.out.println(pm);

        //ukoliko vec postoji taj lek u apoteci , onda samo povecaj kolicinu
        if ( postoji == null)
        {


            pm.setMed(m);
            pm.setPharmacy(ph);
            pm.setQuantity(1);

            ph.getMeds().add(pm);
        }
        else
        {
            //posto postoji, prvo ga ukloni, a onda dodaj sa povecanom kolicinom
            pm = postoji;
            ph.getMeds().remove(pm);

            pm.setQuantity( pm.getQuantity() + 1);

            ph.getMeds().add(pm);

        }

            return pharmacyRepository.save(ph);

    }





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
