package isa.pharmacy.Controllers;

import isa.pharmacy.Models.DateTimeJSON;
import isa.pharmacy.Models.Med;
import isa.pharmacy.Models.Pharmacy;
import isa.pharmacy.Models.PharmacyMed;
import isa.pharmacy.Services.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/pharmacies")
public class PharmacyController {

    @Autowired
    private PharmacyService pharmacyService;

    @GetMapping
    public ResponseEntity<?> findAll() {
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



}
