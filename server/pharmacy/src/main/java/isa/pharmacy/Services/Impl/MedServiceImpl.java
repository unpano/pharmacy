package isa.pharmacy.Services.Impl;

import isa.pharmacy.Models.*;
import isa.pharmacy.Repositories.MedRepository;
import isa.pharmacy.Repositories.PharmacyRepository;
import isa.pharmacy.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MedServiceImpl implements MedService {

    @Autowired
    private MedRepository medRepository;

    @Autowired
    private RateService rateService;

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private PharmacyService pharmacyService;

    public List<Med> findAll() throws AccessDeniedException {
        return medRepository.findAll();
    }

    @Override
    public Optional<Med> findById(Long id) {
        return medRepository.findById(id);
    }

    @Override
    public Med save(Med med) {
        return medRepository.save(med);
    }

    @Override
    public List<Med> findMedsByPharmacyId(Long id) {

        //pronadjem apoteku sa primljenim id-jem
        Optional<Pharmacy> pharmacy = this.pharmacyService.findById(id);

        //uzmem koji lekovi joj pripadaju
        List<PharmacyMed> meds = pharmacy.get().getMeds();
        List<Med> retMeds = new ArrayList<>();

        for (int i =0; i< meds.size(); i++){
            retMeds.add(meds.get(i).getMed());
        }

        return retMeds;
    }

    @Override
    public List<Med> findMedsToRate(Long id) {

        List<Med> meds = findMeds(id);

        //izbaci iz liste one koji su vec ocenjeni
        List<Rate> rates = rateService.findAllByUserIdAndWhomRates(id,WhomRates.MED);

        for(int i=0; i<meds.size(); i++){
            for(int j=0; j<rates.size(); j++){
                if(meds.get(i).getId() == rates.get(j).getIdOfRatedObject()){
                    meds.remove(meds.get(i));
                }
            }

        }

        return  meds;
    }

    @Override
    public List<Med> findRatedMeds(Long id) {
        List<Med> meds = findMeds(id);

        //samo ocdenjeni lekovi
        List<Rate> rates = rateService.findAllByUserIdAndWhomRates(id,WhomRates.MED);
        List<Med> ratedMeds = new ArrayList<>();

        for(int i=0; i<meds.size(); i++){
            for(int j=0; j<rates.size(); j++){
                if(meds.get(i).getId() == rates.get(j).getIdOfRatedObject()){
                    meds.get(i).setStars(rates.get(j).getRate());
                    ratedMeds.add(meds.get(i));
                }
            }

        }

        return ratedMeds;
    }

    public List<Med> findMeds(Long userId){

        List<Reservation> reservations = reservationService.findByUserId(userId);
        List<Prescription> prescriptions = prescriptionService.findAllByUserId(userId);
        List<Med> meds = new ArrayList<>();

        for (int i=0; i<prescriptions.size(); i++){
            //da ne ponavljam iste lekove
            for(int j=0; j<prescriptions.get(i).getMeds().size(); j++){
                if(!meds.contains(prescriptions.get(i).getMeds().get(j))){
                    meds.add(prescriptions.get(i).getMeds().get(j));
                }
            }
        }

        for (int i=0; i<reservations.size(); i++){
            //da ne ponavljam iste lekove
            if(!meds.contains(reservations.get(i).getMed()))
                meds.add(reservations.get(i).getMed());
        }

        return meds;
    }
}
