package isa.pharmacy.Controllers;

import isa.pharmacy.Models.Med;
import isa.pharmacy.Models.Pharmacy;
import isa.pharmacy.Models.PharmacyMed;
import isa.pharmacy.Models.User;
import isa.pharmacy.Services.MedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/meds")
public class MedController {

    @Autowired
    private MedService medService;




    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(this.medService.findAll(), HttpStatus.OK) ;
    }

    @GetMapping("/pharmacies/{id}")
    public ResponseEntity<?> findPharmaciessByMedId(@PathVariable Long id){
        System.out.println("-------------------");
        Optional<Med> med = this.medService.findById(id);


        List<PharmacyMed> pharms = med.get().getPharms();
        List<Pharmacy> pharmacies = new ArrayList<>();
        for (int i =0; i< pharms.size(); i++){
            pharmacies.add(pharms.get(i).getPharmacy());
        }

        if (med.isPresent()){
            return new ResponseEntity<>(pharmacies, HttpStatus.OK);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
}
