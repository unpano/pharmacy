package isa.pharmacy.Controllers;


import isa.pharmacy.Models.*;
import isa.pharmacy.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rates")
public class RateController {

    @Autowired
    private UserService userService;

    @Autowired
    private RateService rateService;

    @Autowired
    private DermatologistService dermatologistService;

    @Autowired
    private PharmacistService pharmacistService;

    @Autowired
    private MedService medService;

    @Autowired
    private PharmacyService pharmacyService;

    //Sve ocene
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> findAll(){
        return new ResponseEntity<>( rateService.findAll(), HttpStatus.OK);
    }

    //Ocenjivanje dermatologa
    @PutMapping("/rateDermatologist/{dermatologistId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> rateDermatologist(Principal user, @PathVariable Long dermatologistId, @RequestBody Float rate){
        //nadjem user-a
        Optional<User> optUser = Optional.ofNullable(userService.findByUsername(user.getName()));

        //Kreiram rate
        Rate rate1 = new Rate();
        rate1.setIdOfRatedObject(dermatologistId);
        rate1.setRate(rate);
        rate1.setUser(optUser.get());
        rate1.setWhomRates(WhomRates.DERMATOLOGIST);
        Object o = rateService.addRate(rate1);

        //poziv metode koja ce ponovo da izracuna prosecnu ocenu dermatologa
        //i da sacuva u njegovoj oceni

        Optional<Dermatologist> dermatologist = dermatologistService.findById(dermatologistId);
        float avg = calculateAvgRate(dermatologistId,WhomRates.DERMATOLOGIST);
        dermatologist.get().setStars(avg);
        dermatologistService.save(dermatologist.get());

        return new ResponseEntity<>(o, HttpStatus.OK);

    }
    //Ocenjivanje dermatologa
    @PutMapping("/ratePharmacist/{pharmacistId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> ratePharmacist(Principal user, @PathVariable Long pharmacistId, @RequestBody Float rate){
        //nadjem user-a
        Optional<User> optUser = Optional.ofNullable(userService.findByUsername(user.getName()));

        //Kreiram rate
        Rate rate1 = new Rate();
        rate1.setIdOfRatedObject(pharmacistId);
        rate1.setRate(rate);
        rate1.setUser(optUser.get());
        rate1.setWhomRates(WhomRates.PHARMACIST);
        Object o = rateService.addRate(rate1);

        //poziv metode koja ce ponovo da izracuna prosecnu ocenu farmaceuta
        //i da sacuva u njegovoj oceni

        Optional<Pharmacist> pharmacist = pharmacistService.findById(pharmacistId);
        float avg = calculateAvgRate(pharmacistId,WhomRates.PHARMACIST);
        pharmacist.get().setStars(avg);
        pharmacistService.save(pharmacist.get());

        return new ResponseEntity<>(o, HttpStatus.OK);

    }

    //Ocenjivanje dermatologa
    @PutMapping("/rateMed/{medId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> rateMed(Principal user, @PathVariable Long medId, @RequestBody Float rate){
        //nadjem user-a
        Optional<User> optUser = Optional.ofNullable(userService.findByUsername(user.getName()));

        //Kreiram rate
        Rate rate1 = new Rate();
        rate1.setIdOfRatedObject(medId);
        rate1.setRate(rate);
        rate1.setUser(optUser.get());
        rate1.setWhomRates(WhomRates.MED);
        Object o = rateService.addRate(rate1);

        //poziv metode koja ce ponovo da izracuna prosecnu ocenu leka
        //i da sacuva u njegovoj oceni

        Optional<Med> med = medService.findById(medId);
        float avg = calculateAvgRate(medId,WhomRates.MED);
        med.get().setStars(avg);
        medService.save(med.get());


        return new ResponseEntity<>(o, HttpStatus.OK);

    }

    //Ocenjivanje apoteke
    @PutMapping("/ratePharmacy/{pharmacyId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> ratePharmacy(Principal user, @PathVariable Long pharmacyId, @RequestBody Float rate){
        //nadjem user-a
        Optional<User> optUser = Optional.ofNullable(userService.findByUsername(user.getName()));

        //Kreiram rate
        Rate rate1 = new Rate();
        rate1.setIdOfRatedObject(pharmacyId);
        rate1.setRate(rate);
        rate1.setUser(optUser.get());
        rate1.setWhomRates(WhomRates.PHARMACY);
        Object o = rateService.addRate(rate1);

        //poziv metode koja ce ponovo da izracuna prosecnu ocenu leka
        //i da sacuva u njegovoj oceni

        Optional<Pharmacy> pharmacy = pharmacyService.findById(pharmacyId);
        float avg = calculateAvgRate(pharmacyId,WhomRates.MED);
        pharmacy.get().setAvgRank(avg);
        pharmacyService.save(pharmacy.get());

        return new ResponseEntity<>(o, HttpStatus.OK);

    }
    //Menjanje ocene
    @PutMapping("/changeRate/{objectId}/{whom}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> changeRate(Principal user,@PathVariable Long objectId, @RequestBody Float rate,
    @PathVariable String whom){

        //nadjem user-a
        Optional<User> optUser = Optional.ofNullable(userService.findByUsername(user.getName()));
        WhomRates whomRates;

        //Nadjem rate i izmenim ga
        Optional<Rate> rate1;
        if(whom.equals("DERMATOLOGIST")){
            rate1 = rateService.findByUserIdAndIdOfRatedObjectAndWhomRates(optUser.get().getId(),objectId,WhomRates.DERMATOLOGIST);
            whomRates = WhomRates.DERMATOLOGIST;

        }
            else if(whom.equals("PHARMACIST")){
            rate1 = rateService.findByUserIdAndIdOfRatedObjectAndWhomRates(optUser.get().getId(),objectId,WhomRates.PHARMACIST);
            whomRates = WhomRates.PHARMACIST;

        }
             else if(whom.equals("MED")){
            rate1 = rateService.findByUserIdAndIdOfRatedObjectAndWhomRates(optUser.get().getId(),objectId,WhomRates.MED);
            whomRates = WhomRates.MED;

        }
            else{
            rate1 = rateService.findByUserIdAndIdOfRatedObjectAndWhomRates(optUser.get().getId(),objectId,WhomRates.PHARMACY);
            whomRates = WhomRates.PHARMACY;

        }


        rate1.get().setRate(rate);
        rateService.update(rate1.get());

        //poziv metode koja ce ponovo da izracuna prosecnu ocenu
        //i da je sacuva
        float avg = calculateAvgRate(objectId,whomRates);

        if(whomRates == WhomRates.DERMATOLOGIST){
            Optional<Dermatologist> dermatologist = dermatologistService.findById(objectId);
            dermatologist.get().setStars(avg);
            dermatologistService.save(dermatologist.get());
        }else if(whomRates == WhomRates.PHARMACIST){
            Optional<Pharmacist> pharmacist = pharmacistService.findById(objectId);
            pharmacist.get().setStars(avg);
            pharmacistService.save(pharmacist.get());
        }else if(whomRates == WhomRates.MED){
            Optional<Med> med = medService.findById(objectId);
            med.get().setStars(avg);
            medService.save(med.get());
        }else{
            Optional<Pharmacy> pharmacy = pharmacyService.findById(objectId);
            pharmacy.get().setAvgRank(avg);
            System.out.println("Ovde puca");
            pharmacyService.save(pharmacy.get());
        }




        return new ResponseEntity<>(rate1, HttpStatus.OK);

    }

    public Float calculateAvgRate (Long objId, WhomRates whomRates){

        //imam id dermatologa
        //nadjem ga u bazi
        //prodjem kroz sve njegove rate-ove
        //sve saberem i podelim sa brojem ocena
        List<Rate> rates = rateService.findAllByIdOfRatedObjectAndWhomRates(objId,whomRates);
        int howManyRates = 0;
        float sum = 0;

        for (int i=0; i<rates.size(); i++){

            sum += rates.get(i).getRate();
            howManyRates += 1;
            System.out.println(sum);
            System.out.println(howManyRates);
        }

        return sum/howManyRates;
    }
}
