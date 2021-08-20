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
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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

    //vraca slobodne termine na osnovu id-ja apoteke
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> findFreeAppointmentsByPharmacyId(@PathVariable Long id){

        return new ResponseEntity<>(dermAppointmentService.findFreeAppointmentsByPharmacyId(id), HttpStatus.OK);

    }

    //vraca buduce termine na osnovu id-ja korisnika
    @GetMapping("/futureAppointments")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> findFutureAppointmentsByUserId(Principal user){
        //nadjem user-a
        Optional<User> optUser = Optional.ofNullable(userService.findByUsername(user.getName()));

        return new ResponseEntity<>(dermAppointmentService.findFutureAppointmentsByUserId(optUser.get().getId()), HttpStatus.OK);

    }

    //vraca buduce termine na osnovu id-ja korisnika
    @GetMapping("/pastAppointments")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> findPastAppointmentsByUserId(Principal user){
        //nadjem user-a
        Optional<User> optUser = Optional.ofNullable(userService.findByUsername(user.getName()));

        return new ResponseEntity<>(dermAppointmentService.findPastAppointmentsByUserId(optUser.get().getId()), HttpStatus.OK);

    }

    //User otkazuje pregled
    @PutMapping("/frees/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> freeDermAppointment(Principal user,@PathVariable Long id){
        //nadjem user-a
        Optional<User> optUser = Optional.ofNullable(userService.findByUsername(user.getName()));
        return new ResponseEntity<>(dermAppointmentService.freeDermAppointment(optUser.get().getId(),id), HttpStatus.OK);

    }

}
