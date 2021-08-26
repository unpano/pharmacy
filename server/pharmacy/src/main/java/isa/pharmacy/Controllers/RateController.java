package isa.pharmacy.Controllers;


import isa.pharmacy.Models.Rate;
import isa.pharmacy.Models.User;
import isa.pharmacy.Models.WhomRates;
import isa.pharmacy.Services.RateService;
import isa.pharmacy.Services.UserService;
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

    //Sve ocene
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> findAll(){
        return new ResponseEntity<>( rateService.findAll(), HttpStatus.OK);
    }

    //Ocenjivanje dermatologa
    @PutMapping("/rateDermatologist/{dermatologistId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> rateDermatologist(Principal user, @PathVariable Long dermatologistId, @RequestBody Long rate){
        //nadjem user-a
        Optional<User> optUser = Optional.ofNullable(userService.findByUsername(user.getName()));

        //Kreiram rate
        Rate rate1 = new Rate();
        rate1.setIdOfRatedObject(dermatologistId);
        rate1.setRate(rate);
        rate1.setUser(optUser.get());
        rate1.setWhomRates(WhomRates.DERMATOLOGIST);

        return new ResponseEntity<>(rateService.addRate(rate1), HttpStatus.OK);

    }
    //Ocenjivanje dermatologa
    @PutMapping("/ratePharmacist/{pharmacistId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> ratePharmacist(Principal user, @PathVariable Long pharmacistId, @RequestBody Long rate){
        //nadjem user-a
        Optional<User> optUser = Optional.ofNullable(userService.findByUsername(user.getName()));

        //Kreiram rate
        Rate rate1 = new Rate();
        rate1.setIdOfRatedObject(pharmacistId);
        rate1.setRate(rate);
        rate1.setUser(optUser.get());
        rate1.setWhomRates(WhomRates.PHARMACIST);

        return new ResponseEntity<>(rateService.addRate(rate1), HttpStatus.OK);

    }

    //Ocenjivanje dermatologa
    @PutMapping("/rateMed/{medId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> rateMed(Principal user, @PathVariable Long medId, @RequestBody Long rate){
        //nadjem user-a
        Optional<User> optUser = Optional.ofNullable(userService.findByUsername(user.getName()));

        //Kreiram rate
        Rate rate1 = new Rate();
        rate1.setIdOfRatedObject(medId);
        rate1.setRate(rate);
        rate1.setUser(optUser.get());
        rate1.setWhomRates(WhomRates.MED);

        return new ResponseEntity<>(rateService.addRate(rate1), HttpStatus.OK);

    }

    //Ocenjivanje apoteke
    @PutMapping("/ratePharmacy/{pharmacyId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> ratePharmacy(Principal user, @PathVariable Long pharmacyId, @RequestBody Long rate){
        //nadjem user-a
        Optional<User> optUser = Optional.ofNullable(userService.findByUsername(user.getName()));

        //Kreiram rate
        Rate rate1 = new Rate();
        rate1.setIdOfRatedObject(pharmacyId);
        rate1.setRate(rate);
        rate1.setUser(optUser.get());
        rate1.setWhomRates(WhomRates.PHARMACY);

        return new ResponseEntity<>(rateService.addRate(rate1), HttpStatus.OK);

    }
    //Menjanje ocene
    @PutMapping("/changeRate/{rateId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> changeRate(@PathVariable Long rateId, @RequestBody Long rate){

        //Nadjem rate i izmenim ga
        Optional<Rate> rate1 = rateService.findById(rateId);
        rate1.get().setRate(rate);
        rateService.update(rate1.get());

        return new ResponseEntity<>(rate1, HttpStatus.OK);

    }

    
}
