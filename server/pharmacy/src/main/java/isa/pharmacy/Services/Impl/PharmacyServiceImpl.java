package isa.pharmacy.Services.Impl;

import isa.pharmacy.Models.*;
import isa.pharmacy.Repositories.MedRepository;
import isa.pharmacy.Repositories.PharmacyRepository;
import isa.pharmacy.Services.*;
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
public class PharmacyServiceImpl implements PharmacyService {
    @Autowired
    private PharmacyRepository pharmacyRepository;
    @Autowired
    private MedRepository medRepository;
    @Autowired
    private DermAppointmentService dermAppointmentService;
    @Autowired
    private TermService termService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private RateService rateService;

    public List<Pharmacy> findAll() { return pharmacyRepository.findAll(); }

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

    @Override
    public Pharmacy save(Pharmacy pharmacy) {
        return pharmacyRepository.save(pharmacy);
    }

    @Override
    public List<Pharmacy> findPharmaciesByMedId(Long id) {

        //pronadjem lek sa primljenim id-jem
        Optional<Med> med = this.medRepository.findById(id);

        //uzmem koje apoteke mu pripadaju
        List<PharmacyMed> pharmacies = med.get().getPharms();
        List<Pharmacy> retPharmacies = new ArrayList<>();

        for (int i =0; i< pharmacies.size(); i++){
            retPharmacies.add(pharmacies.get(i).getPharmacy());
        }

        return retPharmacies;
    }

    @Override
    public List<Pharmacy> findPharmaciesToRate(Long id) {
        List<Pharmacy> pharmacies = findPharmacies(id);

        //izbaci iz liste one koji su vec ocenjeni
        List<Rate> rates = rateService.findAllByUserIdAndWhomRates(id,WhomRates.PHARMACY);

        for(int i=0; i<pharmacies.size(); i++){
            for(int j=0; j<rates.size(); j++){
                if(pharmacies.get(i).getId() == rates.get(j).getIdOfRatedObject()){
                    pharmacies.remove(pharmacies.get(i));
                }
            }

        }

        return pharmacies;
    }

    @Override
    public List<Pharmacy> findRatedPharmacies(Long id) {
        List<Pharmacy> pharmacies = findPharmacies(id);

        //samo ocenjene apoteke
        List<Rate> rates = rateService.findAllByUserIdAndWhomRates(id,WhomRates.PHARMACY);
        List<Pharmacy> ratedPharmacies = new ArrayList<>();

        for(int i=0; i<pharmacies.size(); i++){
            for(int j=0; j<rates.size(); j++){
                if(pharmacies.get(i).getId() == rates.get(j).getIdOfRatedObject()){
                    pharmacies.get(i).setAvgRank(rates.get(j).getRate());
                    ratedPharmacies.add(pharmacies.get(i));
                }
            }

        }

        return  ratedPharmacies;
    }

    public List<Pharmacy> findPharmacies(Long userId){
        List<Pharmacy> pharmacies = new ArrayList<>();

        //lista apoteka na osnovu pregleda kod dermatologa
        List<DermAppointment> appointments = dermAppointmentService.findAllByUserId(userId);
        for (int i=0; i< appointments.size(); i++){
            if(!pharmacies.contains(appointments.get(i).getPharmacy())){
                pharmacies.add(appointments.get(i).getPharmacy());
            }
        }

        //lista apoteka na osnovu pregleda farmaceuta
        List<Term> terms = termService.findAllByUserId(userId);
        List<Pharmacist> pharmacists = new ArrayList<>();
        for(int i=0; i<terms.size(); i++){
            if(!pharmacists.contains(terms.get(i).getPharmacist()))
                pharmacists.add(terms.get(i).getPharmacist());
        }
        for(int i=0; i<pharmacists.size(); i++){
            if(!pharmacies.contains(pharmacists.get(i).getPharmacy())){
                pharmacies.add(pharmacists.get(i).getPharmacy());
            }
        }

        //lista apoteka na osnovu rezervacija lekova (eRecept nema smisla da ima podatak o apoteci)
        List<Reservation> reservations = reservationService.findByUserId(userId);
        for(int i=0; i<reservations.size(); i++){
            if(!pharmacies.contains(reservations.get(i).getPharmacy())){
                pharmacies.add(reservations.get(i).getPharmacy());
            }
        }

        return pharmacies;
    }
}
