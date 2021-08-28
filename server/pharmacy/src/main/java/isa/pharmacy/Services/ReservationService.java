package isa.pharmacy.Services;

import isa.pharmacy.Models.Med;
import isa.pharmacy.Models.Reservation;
import isa.pharmacy.Models.User;

import java.util.Date;
import java.util.List;

public interface ReservationService {
    Reservation add(Reservation reservation);

    Object freeReservation(User user, Long resId);

    Object pickUpMed(User user, Long resId);

    List<Reservation> findByUserId(Long id);

    Reservation reserve(User user, Date date, Long medId, Long pharmacyId);

    List<Reservation> findAllByUserId(Long id);
}
