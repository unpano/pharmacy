package isa.pharmacy.Services.Impl;

import isa.pharmacy.Models.*;
import isa.pharmacy.Repositories.MedRepository;
import isa.pharmacy.Repositories.ReservationRepository;
import isa.pharmacy.Repositories.UserRepository;
import isa.pharmacy.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MedService medService;

    @Autowired
    private PharmacyMedService pharmacyMedService;

    @Autowired
    private PharmacyService pharmacyService;

    @Override
    public Reservation add(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Object freeReservation(User user, Long resId) {
        Optional<Reservation> reservation = reservationRepository.findById(resId);

        //Ne moze da otkaze pregled ako je manje od 24h do pocetka istog
        long createdBefore = reservation.get().getPickUpDate().getTime();
        long now = new Date().getTime();
        long oneDayMILS = 86400000;
        long difference = createdBefore-now;

        if (difference > oneDayMILS){

            //obrisi rez
            reservationRepository.delete(reservation.get());

            return null;
        }
        return reservation;
    }

    @Override
    public Object pickUpMed(User user, Long resId) {
        Optional<Reservation> reservation = reservationRepository.findById(resId);

        reservation.get().setPickedUp(true);
        reservationRepository.save(reservation.get());

        return true;
    }

    @Override
    public List<Reservation> findByUserId(Long id) {
        return reservationRepository.findAllByUserId(id);
    }

    @Override
    public Reservation reserve(User user, Date date, Long medId, Long pharmacyId) {
        //nadjem njegove rezervacije
        List<Reservation> reservations = user.getReservations();

        //lek koji rezervise
        Optional<Med> foundMed = medService.findById(medId);
        Boolean alreadyReserved = false;

        //prodji kroz njih da vidis da li je vec rezervisao isti lek
        for(int i = 0; i<reservations.size(); i++){
            if(reservations.get(i).getMed() == foundMed.get()){
                alreadyReserved = true;
            }
        }

        Reservation reservation1 = new Reservation();

        if(alreadyReserved == false){
            //smanji kolicinu
            Optional<PharmacyMed> pharmacyMed = pharmacyMedService.findByMedIdAndPharmacyId(medId,pharmacyId);
            pharmacyMed.get().setQuantity(pharmacyMed.get().getQuantity()-1);
            pharmacyMedService.update(pharmacyMed.get());

            //napravi rezervaciju
            Optional<Pharmacy> pharmacy = pharmacyService.findById(pharmacyId);

            Reservation reservation = new Reservation();
            reservation.setMed(foundMed.get());
            reservation.setUser(user);
            reservation.setPickUpDate(date);
            reservation.setPickedUp(false);
            reservation.setPharmacy(pharmacy.get());

            //dodaj je u bazu i povratnu vrednost dodeli useru
            reservation1 = reservationRepository.save(reservation);

        }

        return reservation1;
    }

    @Override
    public List<Reservation> findAllByUserId(Long id) {

        List<Reservation> reservations = reservationRepository.findAllByUserId(id);

        //prodji kroz listu i uzmi samo one rezervacije koje nisu preuzete
        for(int i=0; i<reservations.size(); i++){
            if(reservations.get(i).getPickedUp()){
                reservations.remove(reservations.get(i));
            }
        }

        return  reservations;
    }


}
