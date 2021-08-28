package isa.pharmacy.Controllers;

import isa.pharmacy.Models.*;
import isa.pharmacy.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("/pharmacies")
public class PharmacyController {

    @Autowired
    private PharmacyService pharmacyService;
    @Autowired
    private UserService userService;



    //Vraca listu svih apoteka
    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(this.pharmacyService.findAll(), HttpStatus.OK) ;
    }

    //Vraca listu apoteka gde ima odredjeni lek
    @GetMapping("/{medId}")
    public ResponseEntity<?> findMedsByPharmacyId(@PathVariable Long medId){
        List<Pharmacy> pharmacies = pharmacyService.findPharmaciesByMedId(medId);

        if (!pharmacies.isEmpty()){
            return new ResponseEntity<>(pharmacies, HttpStatus.OK);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/pharmaciesToRate")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> findPharmaciesToRate(Principal user){
        Optional<User> user1 = Optional.ofNullable(userService.findByUsername(user.getName()));

        List<Pharmacy> pharmacies = pharmacyService.findPharmaciesToRate(user1.get().getId());

        if (user1.isPresent()){
            return new ResponseEntity<>(pharmacies, HttpStatus.OK);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/pharmaciesToRate/rated")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> findRatedPharmacies(Principal user){

        Optional<User> user1 = Optional.ofNullable(userService.findByUsername(user.getName()));

       List<Pharmacy> ratedPharmacies = pharmacyService.findRatedPharmacies(user1.get().getId());



        if (user1.isPresent()){
            return new ResponseEntity<>(ratedPharmacies, HttpStatus.OK);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }



}
