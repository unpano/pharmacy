package isa.pharmacy.Controllers;

import isa.pharmacy.Models.Dermatologist;
import isa.pharmacy.Models.Pharmacist;
import isa.pharmacy.Services.DermatologistService;
import isa.pharmacy.Services.PharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/dermatologists")
public class DermatologistController {


    @Autowired
    private DermatologistService dermatologistService;


    //dodavanje novog dermatologa
    @PostMapping("/add_dermatologist/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addNewDermatologist(@RequestBody Dermatologist d) {
        System.out.println("dosao je ");
        return new ResponseEntity<Dermatologist>(dermatologistService.addMewDermatologist(d), HttpStatus.OK);
    }


    @GetMapping("/allDermatologists/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> findDermatologistsByPharmacyId(@PathVariable Long id) {
        System.out.println("dosao je u fin derm");
        return new ResponseEntity<List<Dermatologist>>(dermatologistService.findDermatologistByPharmacyId(id), HttpStatus.OK);
    }
}
