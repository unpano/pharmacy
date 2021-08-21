package isa.pharmacy.Services.Impl;

import isa.pharmacy.Models.Reservation;
import isa.pharmacy.Models.User;
import isa.pharmacy.Repositories.ReservationRepository;
import isa.pharmacy.Repositories.UserRepository;
import isa.pharmacy.Services.ReservationService;
import isa.pharmacy.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

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
            //obrisi rez iz liste kod usera
            List<Reservation> reservations = user.getReservations();
            reservations.remove(reservation);
            user.setReservations(reservations);
            userRepository.save(user);

            //obrisi rez
            reservationRepository.delete(reservation.get());

            return null;
        }
        return reservation;
    }
}
