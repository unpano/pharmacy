package isa.pharmacy.Controllers;

import isa.pharmacy.Models.Med;
import isa.pharmacy.Models.Pharmacy;
import isa.pharmacy.Services.MedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        List<Pharmacy> pharms = med.get().getPharms();

        if (med.isPresent()){
            return new ResponseEntity<>(pharms, HttpStatus.OK);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
}
