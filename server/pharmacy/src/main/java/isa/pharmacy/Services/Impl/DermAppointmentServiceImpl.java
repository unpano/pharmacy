package isa.pharmacy.Services.Impl;

import isa.pharmacy.Models.DermAppointment;
import isa.pharmacy.Repositories.DermAppointmentRepository;
import isa.pharmacy.Services.DermAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DermAppointmentServiceImpl implements DermAppointmentService {

    @Autowired
    private DermAppointmentRepository dermAppointmentRepository;

    @Override
    public List<DermAppointment> findAll() {
        return dermAppointmentRepository.findAll();
    }

    @Override
    public Optional<DermAppointment> findById(Long id) {
        return dermAppointmentRepository.findById(id);
    }

    @Override
    public DermAppointment update(DermAppointment dermAppointment) {
        return dermAppointmentRepository.save(dermAppointment);
    }

    @Override
    public List<DermAppointment> findFreeAppointmentsByPharmacyId(Long id) {

        return dermAppointmentRepository.findAllByPharmacyIdAndUserId(id,null);
    }

    @Override
    public List<DermAppointment> findFutureAppointmentsByUserId(Long id) {
        List<DermAppointment> appointments = dermAppointmentRepository.findAllByUserId(id);
        List<DermAppointment> futureAppointments = new ArrayList<>();
        Date now = new Date();

        for(int i = 0; i< appointments.size();i++){
            if(appointments.get(i).getDate().after(now)){
                futureAppointments.add(appointments.get(i));
            }
        }
        return futureAppointments;
    }

    @Override
    public List<DermAppointment> findPastAppointmentsByUserId(Long id) {
        List<DermAppointment> appointments = dermAppointmentRepository.findAllByUserId(id);
        List<DermAppointment> pastAppointments = new ArrayList<>();
        Date now = new Date();

        for(int i = 0; i< appointments.size();i++){
            if(appointments.get(i).getDate().before(now)){
                pastAppointments.add(appointments.get(i));
            }
        }
        return pastAppointments;
    }

    @Override
    public Optional<DermAppointment> freeDermAppointment(Long id, Long id1) {
        Optional<DermAppointment> dermAppointment = dermAppointmentRepository.findById(id1);

        //Ne moze da otkaze pregled ako je manje od 24h do pocetka istog
        long createdBefore = dermAppointment.get().getDate().getTime();
        long now = new Date().getTime();
        long oneDayMILS = 86400000;
        long difference = createdBefore-now;

        if (difference > oneDayMILS){
            dermAppointment.get().setUser(null);
            DermAppointment dermAppointment1 = update(dermAppointment.get());
            return null;
        }
        return dermAppointment;
    }
}
