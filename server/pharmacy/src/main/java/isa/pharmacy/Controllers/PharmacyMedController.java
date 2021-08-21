package isa.pharmacy.Controllers;

import isa.pharmacy.Models.*;
import isa.pharmacy.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/pharmacyMed")
public class PharmacyMedController {

    @Autowired
    private PharmacyMedService pharmacyMedService;
    @Autowired
    private UserService userService;
    @Autowired
    private MedService medService;
    @Autowired
    private ReservationService reservationService;

    @PutMapping("/reserve/{pharmacyId}/{medId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> reserveMed(Principal user, @RequestBody Date date, @PathVariable Long pharmacyId,@PathVariable Long medId) {
        System.out.println(date);
        //nadji korisnikove rezervacije
        Optional<User> optUser = Optional.ofNullable(userService.findByUsername(user.getName()));
        List<Reservation> reservations = optUser.get().getReservations();

        Optional<Med> foundMed = medService.findById(medId);
        Boolean alreadyReserved = false;

        //prodji kroz njih da vidis da li je vec rezervisao isti lek
        for(int i = 0; i<reservations.size(); i++){
            if(reservations.get(i).getMed() == foundMed.get()){
                alreadyReserved = true;
            }
        }

        if(alreadyReserved == false){
            //smanji kolicinu
            Optional<PharmacyMed> pharmacyMed = pharmacyMedService.findByMedIdAndPharmacyId(medId,pharmacyId);
            pharmacyMed.get().setQuantity(pharmacyMed.get().getQuantity()-1);
            pharmacyMedService.update(pharmacyMed.get());

            //napravi rezervaciju
            Reservation reservation = new Reservation();
            reservation.setMed(foundMed.get());
            reservation.setUser(optUser.get());
            reservation.setPickUpDate(date);
            //dodaj je u bazu i povratnu vrednost dodeli useru
            Reservation reservation1 = reservationService.add(reservation);

            //dodeli je korisniku
            reservations.add(reservation1);
            optUser.get().setReservations(reservations);
            userService.update(optUser.get());
        }

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
