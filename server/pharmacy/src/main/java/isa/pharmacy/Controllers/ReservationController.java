package isa.pharmacy.Controllers;

import isa.pharmacy.Models.Reservation;
import isa.pharmacy.Models.User;
import isa.pharmacy.Services.ReservationService;
import isa.pharmacy.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UserService userService;

    //User otkazuje pregled
    @PutMapping("/free/{resId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> freeDermAppointment(Principal user, @PathVariable Long resId){
        //nadjem user-a
        Optional<User> optUser = Optional.ofNullable(userService.findByUsername(user.getName()));
        return new ResponseEntity<>(reservationService.freeReservation(optUser.get(),resId), HttpStatus.OK);

    }


}
