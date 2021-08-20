package isa.pharmacy.Controllers;

import isa.pharmacy.Models.DermAppointment;
import isa.pharmacy.Models.Med;
import isa.pharmacy.Models.User;
import isa.pharmacy.Services.DermAppointmentService;
import isa.pharmacy.Services.UserService;
import org.hibernate.engine.transaction.jta.platform.internal.SynchronizationRegistryBasedSynchronizationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/dermAppointments")
public class DermAppointmentController {

    @Autowired
    private DermAppointmentService dermAppointmentService;

    @Autowired
    private UserService userService;

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
