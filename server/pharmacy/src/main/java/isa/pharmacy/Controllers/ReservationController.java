package isa.pharmacy.Controllers;

import isa.pharmacy.Models.*;
import isa.pharmacy.Services.PrescriptionService;
import isa.pharmacy.Services.RateService;
import isa.pharmacy.Services.ReservationService;
import isa.pharmacy.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UserService userService;

    //User rezervise lek
    @PutMapping("/reserve/{pharmacyId}/{medId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> reserveMed(Principal user, @RequestBody Date date, @PathVariable Long pharmacyId, @PathVariable Long medId) {
        //nadjem user-a
        Optional<User> optUser = Optional.ofNullable(userService.findByUsername(user.getName()));

        return new ResponseEntity<Reservation>(reservationService.reserve(optUser.get(),date,medId,pharmacyId), HttpStatus.OK);
    }

    //User otkazuje rezervaciju leka
    @PutMapping("/free/{resId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> freeReservation(Principal user, @PathVariable Long resId){
        //nadjem user-a
        Optional<User> optUser = Optional.ofNullable(userService.findByUsername(user.getName()));
        return new ResponseEntity<>(reservationService.freeReservation(optUser.get(),resId), HttpStatus.OK);

    }

    //User preuzima lek
    @PutMapping("/pickUpMed/{resId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> pickUpMedication(Principal user, @PathVariable Long resId){
        //nadjem user-a
        Optional<User> optUser = Optional.ofNullable(userService.findByUsername(user.getName()));
        return new ResponseEntity<>(reservationService.pickUpMed(optUser.get(),resId), HttpStatus.OK);

    }

    //treba vratiti samo one koji nisu pokupljeni
    @GetMapping("/userReservations")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> findUserReservations(Principal user){
        Optional<User> user1 = Optional.ofNullable(userService.findByUsername(user.getName()));

        List<Reservation> reservations = reservationService.findAllByUserId(user1.get().getId());

        if (user1.isPresent()){
            return new ResponseEntity<>(reservations, HttpStatus.OK);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }



}
