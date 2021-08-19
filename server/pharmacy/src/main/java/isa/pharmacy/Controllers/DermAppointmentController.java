package isa.pharmacy.Controllers;

import isa.pharmacy.Models.DermAppointment;
import isa.pharmacy.Models.Med;
import isa.pharmacy.Models.User;
import isa.pharmacy.Services.DermAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dermAppointments")
public class DermAppointmentController {

    @Autowired
    private DermAppointmentService dermAppointmentService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(this.dermAppointmentService.findAll(), HttpStatus.OK) ;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> findAppointmentsByPharmacyId(@PathVariable Long id){
        return new ResponseEntity<>(dermAppointmentService.findAppointmentsByPharmacyId(id), HttpStatus.OK);

    }
}
