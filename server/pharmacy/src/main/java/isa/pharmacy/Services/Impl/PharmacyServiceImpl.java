package isa.pharmacy.Services.Impl;

import isa.pharmacy.Models.*;
import isa.pharmacy.Repositories.*;
import isa.pharmacy.Services.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
