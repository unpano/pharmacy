package isa.pharmacy.Controllers;

import isa.pharmacy.Models.Med;
import isa.pharmacy.Models.Pharmacist;

import isa.pharmacy.Models.*;
import isa.pharmacy.Services.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/pharmacies")
public class PharmacyController {

    @Autowired
    private PharmacyService pharmacyService;
    @Autowired
    private UserService userService;
    @Autowired
    private DermAppointmentService dermAppointmentService;
    @Autowired
    private TermService termService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private RateService rateService;



    //Admin dodaje novi lek u apoteku
    @PutMapping("/addMed/{medId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addMedToPharmacy(@RequestBody Pharmacy ph, @PathVariable Long medId) {


        return new ResponseEntity<>(pharmacyService.addMed(ph, medId), HttpStatus.OK);
    }



    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(this.pharmacyService.findAll(), HttpStatus.OK) ;

    }

    @GetMapping("/getPharmacy/{adminId}")
    public ResponseEntity<?> findPharmacyByAdminId(@PathVariable Long adminId) {


        return new ResponseEntity<>( pharmacyService.findPharmacyByAdmin(adminId), HttpStatus.OK) ;

    }




    //Admin brise lek iz apoteke
    @PutMapping("/deleteMed/{medId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteMed(@RequestBody Pharmacy ph, @PathVariable Long medId){

        return new ResponseEntity<>(pharmacyService.deleteMed(ph, medId), HttpStatus.OK);

    }


    //Admin brise dermatologa iz apoteke
    @PutMapping("/deleteDermatologist/{dermatologistId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteDermatologist(@RequestBody Pharmacy ph, @PathVariable Long dermatologistId){

        return new ResponseEntity<>(pharmacyService.deleteDermatologist(ph, dermatologistId), HttpStatus.OK);

    }

    //Admin brise farmaceuta iz apoteke
    @PutMapping("/deletePharmacist/{pharmacistId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deletePharmacist(@RequestBody Pharmacy ph, @PathVariable Long pharmacistId){

        return new ResponseEntity<>(pharmacyService.deletePharmacist(ph, pharmacistId), HttpStatus.OK);

    }



    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Pharmacy> pharmOpt = this.pharmacyService.findById(id);

        if (pharmOpt.isPresent()){
            return new ResponseEntity<>(pharmOpt.get(), HttpStatus.OK);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/meds/{id}")
    public ResponseEntity<?> findMedsByPharmacyId(@PathVariable Long id){
        Optional<Pharmacy> pharm = this.pharmacyService.findById(id);
        List<PharmacyMed> ms = pharm.get().getMeds();

        List<Med> meds = new ArrayList<>();
        for (int i =0; i< ms.size(); i++){
            meds.add(ms.get(i).getMed());
        }

        if (pharm.isPresent()){
            return new ResponseEntity<>(meds, HttpStatus.OK);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }






    @GetMapping("/search")
    public ResponseEntity<?> findByCriteria(@RequestParam(name = "searchItem", required = true) String searchItem) {
        return new ResponseEntity<List<Pharmacy>>(pharmacyService.findByCriteria(searchItem), HttpStatus.OK);
    }

    @GetMapping("/pharmaciesToRate")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> findPharmaciesToRate(Principal user){

        List<Pharmacy> pharmacies = new ArrayList<>();
        Optional<User> user1 = Optional.ofNullable(userService.findByUsername(user.getName()));

        //lista apoteka na osnovu pregleda kod dermatologa
        List<DermAppointment> appointments = dermAppointmentService.findAllByUserId(user1.get().getId());
        for (int i=0; i< appointments.size(); i++){
            if(!pharmacies.contains(appointments.get(i).getPharmacy())){
                pharmacies.add(appointments.get(i).getPharmacy());
            }
        }

        //lista apoteka na osnovu pregleda farmaceuta
        List<Term> terms = termService.findAllByUserId(user1.get().getId());
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
        List<Reservation> reservations = reservationService.findByUserId(user1.get().getId());
        for(int i=0; i<reservations.size(); i++){
            if(!pharmacies.contains(reservations.get(i).getPharmacy())){
                pharmacies.add(reservations.get(i).getPharmacy());
            }
        }

        //izbaci iz liste one koji su vec ocenjeni
        List<Rate> rates = rateService.findAllByUserIdAndWhomRates(user1.get().getId(),WhomRates.PHARMACY);

        for(int i=0; i<pharmacies.size(); i++){
            for(int j=0; j<rates.size(); j++){
                if(pharmacies.get(i).getId() == rates.get(j).getIdOfRatedObject()){
                    pharmacies.remove(pharmacies.get(i));
                }
            }

        }

        if (user1.isPresent()){
            return new ResponseEntity<>(pharmacies, HttpStatus.OK);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }





    @GetMapping("/pharmaciesToRate/rated")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> findRatedPharmacies(Principal user){

        List<Pharmacy> pharmacies = new ArrayList<>();
        Optional<User> user1 = Optional.ofNullable(userService.findByUsername(user.getName()));

        //lista apoteka na osnovu pregleda kod dermatologa
        List<DermAppointment> appointments = dermAppointmentService.findAllByUserId(user1.get().getId());
        for (int i=0; i< appointments.size(); i++){
            if(!pharmacies.contains(appointments.get(i).getPharmacy())){
                pharmacies.add(appointments.get(i).getPharmacy());
            }
        }

        //lista apoteka na osnovu pregleda farmaceuta
        List<Term> terms = termService.findAllByUserId(user1.get().getId());
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
        List<Reservation> reservations = reservationService.findByUserId(user1.get().getId());
        for(int i=0; i<reservations.size(); i++){
            if(!pharmacies.contains(reservations.get(i).getPharmacy())){
                pharmacies.add(reservations.get(i).getPharmacy());
            }
        }

        //izbaci iz liste one koji su vec ocenjeni
        List<Rate> rates = rateService.findAllByUserIdAndWhomRates(user1.get().getId(),WhomRates.PHARMACY);
        List<Pharmacy> ratedPharmacies = new ArrayList<>();

        for(int i=0; i<pharmacies.size(); i++){
            for(int j=0; j<rates.size(); j++){
                if(pharmacies.get(i).getId() == rates.get(j).getIdOfRatedObject()){
                    pharmacies.get(i).setAvgRank(rates.get(j).getRate());
                    ratedPharmacies.add(pharmacies.get(i));
                }
            }

        }

        if (user1.isPresent()){
            return new ResponseEntity<>(ratedPharmacies, HttpStatus.OK);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }



}
