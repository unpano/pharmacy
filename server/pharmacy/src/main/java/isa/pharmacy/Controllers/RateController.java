package isa.pharmacy.Controllers;


import isa.pharmacy.Models.*;
import isa.pharmacy.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
    @PutMapping("/rateDermatologist/{objectId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> rateDermatologist(Principal user, @PathVariable Long objectId, @RequestBody Float rate){
        //nadjem user-a
        Optional<User> optUser = Optional.ofNullable(userService.findByUsername(user.getName()));

        Object o = rateService.rate(optUser.get(),objectId,rate,WhomRates.DERMATOLOGIST);

        return new ResponseEntity<>(o, HttpStatus.OK);

    }
    //Ocenjivanje farmaceuta
    @PutMapping("/ratePharmacist/{objectId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> ratePharmacist(Principal user, @PathVariable Long objectId, @RequestBody Float rate){
        //nadjem user-a
        Optional<User> optUser = Optional.ofNullable(userService.findByUsername(user.getName()));

        Object o = rateService.rate(optUser.get(),objectId,rate,WhomRates.PHARMACIST);


        return new ResponseEntity<>(o, HttpStatus.OK);

    }

    //Ocenjivanje leka
    @PutMapping("/rateMed/{objectId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> rateMed(Principal user, @PathVariable Long objectId, @RequestBody Float rate){
        //nadjem user-a
        Optional<User> optUser = Optional.ofNullable(userService.findByUsername(user.getName()));

        Object o = rateService.rate(optUser.get(),objectId,rate,WhomRates.MED);

        return new ResponseEntity<>(o, HttpStatus.OK);

    }

    //Ocenjivanje apoteke
    @PutMapping("/ratePharmacy/{objectId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> ratePharmacy(Principal user, @PathVariable Long objectId, @RequestBody Float rate){
        //nadjem user-a
        Optional<User> optUser = Optional.ofNullable(userService.findByUsername(user.getName()));

        Object o = rateService.rate(optUser.get(),objectId,rate,WhomRates.PHARMACY);

        return new ResponseEntity<>(o, HttpStatus.OK);

    }
    //Menjanje ocene
    @PutMapping("/changeRate/{objectId}/{whom}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> changeRate(Principal user,@PathVariable Long objectId, @RequestBody Float rate,
    @PathVariable String whom){

        //nadjem user-a
        Optional<User> optUser = Optional.ofNullable(userService.findByUsername(user.getName()));

        Rate rate1 = rateService.changeRate(optUser.get(),objectId,rate,whom);

        return new ResponseEntity<>(rate1, HttpStatus.OK);

    }


}
