package isa.pharmacy.Services.Impl;

import isa.pharmacy.Models.DermAppointment;
import isa.pharmacy.Models.Dermatologist;
import isa.pharmacy.Models.User;
import isa.pharmacy.Repositories.DermAppointmentRepository;
import isa.pharmacy.Services.DermAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class DermAppointmentServiceImpl implements DermAppointmentService {

    @Autowired
    private DermAppointmentRepository dermAppointmentRepository;

    @Override
    public DermAppointment addNewAppointment(DermAppointment dermAppointment) {

        //proveravamo da li dermatologu tada radno vreme
        Dermatologist derm = dermAppointment.getDermatologist();
        Date dateTime = dermAppointment.getDate();
        boolean i = false;

        //dermAppointmentRepository.findAllByDermatologistId(derm.getId());
        Set<DermAppointment> lista_svih_zakazanih_pregleda = derm.getDermAppointments();

        //proveravamo da li dermatolog vec ima zakazan termin

        for (DermAppointment pregled : lista_svih_zakazanih_pregleda)
        {
            System.out.println(pregled.getDermatologist());

            long ONE_MINUTE_IN_MILLIS=60000; //millisecs
            long t= pregled.getDate().getTime();    //pregled u vremenu
            long t2= dermAppointment.getDate().getTime();    //pregled u vremenu

            Date kraj_nekog=new Date(t + (pregled.getDuration() * ONE_MINUTE_IN_MILLIS));
            Date kraj_ovog=new Date(t2 + (dermAppointment.getDuration() * ONE_MINUTE_IN_MILLIS));

            //ako je vreme novog pregleda izmedju pocetka i kraja nekog vec postojeceg, onda ga necemo cuvati
            if ( dermAppointment.getDate().after( pregled.getDate() )  || dermAppointment.getDate().before( kraj_nekog) )
            {
                i = true;

            }
            if ( dermAppointment.getDate().after( pregled.getDate() )  && dermAppointment.getDate().before( kraj_ovog) )
            {
                i = true;

            }
            if(dermAppointment.getDate() ==pregled.getDate())
            {
                i = true;
            }


        }
        System.out.println(i);
        if(i == true)
        {
            return null;

        }
        else
            return dermAppointmentRepository.save(dermAppointment);
    }


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

    @Override
    public List<DermAppointment> findAllByUserId(Long id) {
        return dermAppointmentRepository.findAllByUserId(id);

    }


}
