package isa.pharmacy.Services.Impl;

import isa.pharmacy.Models.*;
import isa.pharmacy.Repositories.PharmacyRepository;
import isa.pharmacy.Repositories.TermRepository;
import isa.pharmacy.Repositories.UserRepository;
import isa.pharmacy.Services.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.persistence.OptimisticLockException;
import java.util.*;

@Service
public class TermServiceImpl implements TermService {

    @Autowired
    private TermRepository termRepository;

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Pharmacy> findAllFree(DateTimeJSON dateTimeJSON) {
        Date date = dateTimeJSON.getDate();

        //lista termina
        List<Term> terms = termRepository.findAll();

        //zauzeti farmaceuti, farmaceut radi u jednoj apoteci, apoteka ima vise farmaceuta
        List<Pharmacist> busyPharmacist = new ArrayList<>();

        //izabrano vreme plus 30 min koliko traje pregled
        long ONE_MINUTE_IN_MILLIS=60000; //millisecs
        long t= date.getTime();
        Date date1=new Date(t + (30 * ONE_MINUTE_IN_MILLIS));

        for (int i=0; i<terms.size(); i++){
            //da li je izabrano vreme u opsegu vec zakazanog termina
            if(date.after(terms.get(i).getStart()) && date.before(terms.get(i).getEnd())){
                busyPharmacist.add(terms.get(i).getPharmacist());
            }
            //pregled traje 30 min., Da li je vreme kraja u opsegu vec zakazanog termina
            if(date1.after(terms.get(i).getStart()) && date1.before(terms.get(i).getEnd())){
                busyPharmacist.add(terms.get(i).getPharmacist());
            }

        }

        //lista svih apoteka
        List<Pharmacy> pharmacies = pharmacyRepository.findAll();

        for(int j=0; j<pharmacies.size(); j++){

            for(int k=0; k<busyPharmacist.size(); k++){
                if(pharmacies.get(j).getPharmacists().contains(busyPharmacist.get(k))){
                    pharmacies.get(j).getPharmacists().remove(busyPharmacist.get(k));
                }
            }

        }

        List<Pharmacy> retPharmacies = new ArrayList<>();

        //vrati apoteke cija lista farmaceuta nije prazna, izbacila sam zauzete
        for(int i=0; i<pharmacies.size(); i++){
            if(pharmacies.get(i).getPharmacists().size() != 0){
                retPharmacies.add(pharmacies.get(i));
            }
        }

        return retPharmacies;
    }

    @Override
    public List<Pharmacist> findAllFreePharmacists(DateTimeJSON dateTimeJSON, Long id) {
        Date date = dateTimeJSON.getDate();

        //lista termina
        List<Term> terms = termRepository.findAll();

        //zauzeti farmaceuti, farmaceut radi u jednoj apoteci, apoteka ima vise farmaceuta
        List<Pharmacist> busyPharmacist = new ArrayList<>();

        //izabrano vreme plus 30 min koliko traje pregled
        long ONE_MINUTE_IN_MILLIS=60000; //millisecs
        long t= date.getTime();
        Date date1=new Date(t + (30 * ONE_MINUTE_IN_MILLIS));

        for (int i=0; i<terms.size(); i++){
            //da li je izabrano vreme u opsegu vec zakazanog termina
            if(date.after(terms.get(i).getStart()) && date.before(terms.get(i).getEnd())){
                busyPharmacist.add(terms.get(i).getPharmacist());
            }
            //pregled traje 30 min., Da li je vreme kraja u opsegu vec zakazanog termina
            if(date1.after(terms.get(i).getStart()) && date1.before(terms.get(i).getEnd())){
                busyPharmacist.add(terms.get(i).getPharmacist());
            }

        }

        //lista svih apoteka
        List<Pharmacy> pharmacies = pharmacyRepository.findAll();

        for(int j=0; j<pharmacies.size(); j++){

            for(int k=0; k<busyPharmacist.size(); k++){
                if(pharmacies.get(j).getPharmacists().contains(busyPharmacist.get(k))){
                    pharmacies.get(j).getPharmacists().remove(busyPharmacist.get(k));
                }
            }

        }

        //apoteka po id-ju
        Optional<Pharmacy> pharmacy = pharmacyRepository.findById(id);

       for(int i=0; i<pharmacies.size(); i++){
           if(pharmacies.get(i).getId().equals(pharmacy.get().getId()))
               return pharmacies.get(i).getPharmacists();
       }

       return null;
    }

    @Override
    public Object add(Date dateValue, Long userId, Long pharmacistId) {

        Term term = new Term();

        Optional<User> user = userRepository.findById(userId);

        //izabrano vreme plus 30 min koliko traje pregled
        long ONE_MINUTE_IN_MILLIS=60000; //millisecs
        long t= dateValue.getTime();
        Date date1=new Date(t + (30 * ONE_MINUTE_IN_MILLIS));

        Pharmacist pharmacist = new Pharmacist();
        pharmacist.setId(pharmacistId);
        term.setPharmacist(pharmacist);
        term.setStart(dateValue);
        term.setEnd(date1);
        term.setUser(user.get());

        //da li je neko u medjuvremenu zakazao termin
        Boolean termNotFree = false;

        List<Term> scheduledTerms = termRepository.findAllByPharmacistId(pharmacistId);
        for(int i=0; i<scheduledTerms.size(); i++){
            if(dateValue.after(scheduledTerms.get(i).getStart()) && dateValue.before(scheduledTerms.get(i).getEnd())){
                termNotFree = true;
            }else if (dateValue.equals(scheduledTerms.get(i).getStart())){
                termNotFree = true;
            }
        }

        if(termNotFree){
            throw new OptimisticLockException();
        }

        return termRepository.save(term);
    }

    @Override
    public List<Term>  findFutureTermsByUserId(Long id) {
        List<Term> appointments = termRepository.findAllByUserId(id);
        List<Term> futureAppointments = new ArrayList<>();
        Date now = new Date();

        for(int i = 0; i< appointments.size();i++){
            if(appointments.get(i).getStart().after(now)){
                futureAppointments.add(appointments.get(i));
            }
        }
        return futureAppointments;
    }

    @Override
    public List<Term> findPastTermsByUserId(Long id) {
        List<Term> appointments = termRepository.findAllByUserId(id);
        List<Term> pastAppointments = new ArrayList<>();
        Date now = new Date();

        for(int i = 0; i< appointments.size();i++){
            if(appointments.get(i).getStart().before(now)){
                pastAppointments.add(appointments.get(i));
            }
        }
        return pastAppointments;
    }

    @Override
    public Optional<Term> freeTerm(Long id, Long id1) {
        Optional<Term> term = termRepository.findById(id1);

        //Ne moze da otkaze pregled ako je manje od 24h do pocetka istog
        long createdBefore = term.get().getStart().getTime();
        long now = new Date().getTime();
        long oneDayMILS = 86400000;
        long difference = createdBefore-now;

        if (difference > oneDayMILS){
            termRepository.delete(term.get());
            return null;
        }
        return term;
    }

    @Override
    public List<Term> findAllByUserId(Long id) {
        return termRepository.findAllByUserId(id);
    }

    @Override
    public Pharmacist findPharmacistByTermId(Long termId) {
        Optional<Term> term = termRepository.findById(termId);

        return term.get().getPharmacist();
    }
}
