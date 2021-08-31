package isa.pharmacy.Controllers;

import isa.pharmacy.Models.Pharmacist;
import isa.pharmacy.Models.User;
import isa.pharmacy.Services.PharmacistService;
import isa.pharmacy.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/pharmacists")
public class PharmacistController {

        @Autowired
        private PharmacistService pharmacistService;


    //dodavanje novog farmaceuta
    @PostMapping("/add_pharmacist/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addNewPharmacist(@RequestBody Pharmacist p ) {
        return new ResponseEntity<Pharmacist>(pharmacistService.addMewPharmacist(p), HttpStatus.OK);
    }


    @GetMapping("/allPharmacists/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> findPharmacistsByPharmacyId(@PathVariable Long id) {

        return new ResponseEntity<List<Pharmacist>>(pharmacistService.findPharmacistsByPharmacyId(id), HttpStatus.OK);
    }
}
