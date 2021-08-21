package isa.pharmacy.Services;

import isa.pharmacy.Models.Reservation;
import isa.pharmacy.Models.User;

public interface ReservationService {
    Reservation add(Reservation reservation);

    Object freeReservation(User user, Long resId);
}
