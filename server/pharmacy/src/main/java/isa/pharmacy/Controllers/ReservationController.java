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
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UserService userService;

    @Autowired
    private RateService rateService;

    @Autowired
    private PrescriptionService prescriptionService;

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

    @GetMapping("/medications")
    public ResponseEntity<?> findMedsForRate(Principal user){

        Optional<User> user1 = Optional.ofNullable(userService.findByUsername(user.getName()));
        List<Reservation> reservations = this.reservationService.findByUserId(user1.get().getId());
        List<Prescription> prescriptions = this.prescriptionService.findAllByUserId(user1.get().getId());

        List<Med> meds = new ArrayList<>();

        for (int i=0; i<prescriptions.size(); i++){
            //da ne ponavljam iste lekove
            for(int j=0; j<prescriptions.get(i).getMeds().size(); j++){

                if(!meds.contains(prescriptions.get(i).getMeds().get(j))){
                    meds.add(prescriptions.get(i).getMeds().get(j));
                }
            }
        }



        for (int i=0; i<reservations.size(); i++){
            //da ne ponavljam iste lekove
            if(!meds.contains(reservations.get(i).getMed()))
                meds.add(reservations.get(i).getMed());
        }

        //izbaci iz liste one koji su vec ocenjeni
        List<Rate> rates = rateService.findAllByUserIdAndWhomRates(user1.get().getId(),WhomRates.MED);

        for(int i=0; i<meds.size(); i++){
            for(int j=0; j<rates.size(); j++){
                if(meds.get(i).getId() == rates.get(j).getIdOfRatedObject()){
                    meds.remove(meds.get(i));
                }
            }

        }



        //System.out.println(meds.size());

        if (user1.isPresent()){
            return new ResponseEntity<>(meds, HttpStatus.OK);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/medications/rated")
    public ResponseEntity<?> findRatedMeds(Principal user){

        Optional<User> user1 = Optional.ofNullable(userService.findByUsername(user.getName()));
        List<Reservation> reservations = this.reservationService.findByUserId(user1.get().getId());
        List<Prescription> prescriptions = this.prescriptionService.findAllByUserId(user1.get().getId());

        List<Med> meds = new ArrayList<>();

        for (int i=0; i<prescriptions.size(); i++){
            //da ne ponavljam iste lekove
            for(int j=0; j<prescriptions.get(i).getMeds().size(); j++){

                if(!meds.contains(prescriptions.get(i).getMeds().get(j))){
                    meds.add(prescriptions.get(i).getMeds().get(j));
                }
            }
        }



        for (int i=0; i<reservations.size(); i++){
            //da ne ponavljam iste lekove
            if(!meds.contains(reservations.get(i).getMed()))
                meds.add(reservations.get(i).getMed());
        }

        //izbaci iz liste one koji su vec ocenjeni
        List<Rate> rates = rateService.findAllByUserIdAndWhomRates(user1.get().getId(),WhomRates.MED);
        List<Med> ratedMeds = new ArrayList<>();

        for(int i=0; i<meds.size(); i++){
            for(int j=0; j<rates.size(); j++){
                if(meds.get(i).getId() == rates.get(j).getIdOfRatedObject()){
                    meds.get(i).setStars(rates.get(j).getRate());
                    ratedMeds.add(meds.get(i));
                }
            }

        }



        //System.out.println(meds.size());

        if (user1.isPresent()){
            return new ResponseEntity<>(ratedMeds, HttpStatus.OK);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }


}
