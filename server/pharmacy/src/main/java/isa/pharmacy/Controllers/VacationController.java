package isa.pharmacy.Controllers;

import isa.pharmacy.Models.Pharmacist;
import isa.pharmacy.Models.Pharmacy;
import isa.pharmacy.Models.VacationRequest;
import isa.pharmacy.Services.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/vacations")
public class VacationController {

    @Autowired
    private VacationService vacationService;


    @GetMapping("/allVacation/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> findVacationsByPharmacyId(@PathVariable Long id) {

        return new ResponseEntity<List<VacationRequest>>(vacationService.findVacationsByPharmacyId(id), HttpStatus.OK);
    }

    @GetMapping("/getWorkerName/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> findWorkerName(@PathVariable Long vId) {
        return new ResponseEntity<String>(vacationService.findWorkerName(vId), HttpStatus.OK) ;

    }

    @PutMapping("/approve/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> approveRequest(@RequestBody VacationRequest v){

        return new ResponseEntity<>(vacationService.approve(v), HttpStatus.OK);

    }

    @PutMapping("/decline/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> declineRequest(@RequestBody VacationRequest v){

        return new ResponseEntity<>(vacationService.decline(v), HttpStatus.OK);

    }

}
