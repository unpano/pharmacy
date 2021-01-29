package isa.pharmacy.Controllers;

import isa.pharmacy.Models.Pharmacy;
import isa.pharmacy.Services.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/pharmacies")
public class PharmacyController {

    @Autowired
    private PharmacyService pharmacyService;

    @GetMapping
    public ResponseEntity<?> showPharmacies() {
        return new ResponseEntity<>(this.pharmacyService.findAll(), HttpStatus.OK) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Pharmacy> pharmOpt = this.pharmacyService.findById(id);

        if (pharmOpt.isPresent()){
            return new ResponseEntity<>(pharmOpt.get(), HttpStatus.OK);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
}
