package isa.pharmacy.Controllers;

import isa.pharmacy.Models.*;
import isa.pharmacy.Services.MedService;
import isa.pharmacy.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/meds")
public class MedController {

    @Autowired
    private MedService medService;
    @Autowired
    private UserService userService;

    //Vraca listu svih lekova
    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(this.medService.findAll(), HttpStatus.OK) ;
    }

    //Vraca listu lekova koje ima odredjena apoteka
    @GetMapping("/{pharmacyId}")
    public ResponseEntity<?> findPharmaciessByMedId(@PathVariable Long pharmacyId){

        List<Med> meds = medService.findMedsByPharmacyId(pharmacyId);

        if (!meds.isEmpty()){
            return new ResponseEntity<>(meds, HttpStatus.OK);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/medications")
    public ResponseEntity<?> findMedsForRate(Principal user){

        Optional<User> user1 = Optional.ofNullable(userService.findByUsername(user.getName()));

        List<Med> meds = medService.findMedsToRate(user1.get().getId());


        if (user1.isPresent()){
            return new ResponseEntity<>(meds, HttpStatus.OK);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/medications/rated")
    public ResponseEntity<?> findRatedMeds(Principal user){
        Optional<User> user1 = Optional.ofNullable(userService.findByUsername(user.getName()));

        List<Med> ratedMeds = medService.findRatedMeds(user1.get().getId());

        if (user1.isPresent()){
            return new ResponseEntity<>(ratedMeds, HttpStatus.OK);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }


}
