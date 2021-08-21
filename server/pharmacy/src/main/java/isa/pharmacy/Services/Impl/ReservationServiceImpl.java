package isa.pharmacy.Services.Impl;

import isa.pharmacy.Models.Reservation;
import isa.pharmacy.Repositories.ReservationRepository;
import isa.pharmacy.Services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public Reservation add(Reservation reservation) {
        return reservationRepository.save(reservation);
    }
}
