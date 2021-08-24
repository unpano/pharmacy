package isa.pharmacy.Services.Impl;

import isa.pharmacy.Models.*;
import isa.pharmacy.Repositories.PharmacyRepository;
import isa.pharmacy.Repositories.TermRepository;
import isa.pharmacy.Repositories.UserRepository;
import isa.pharmacy.Services.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.midi.SysexMessage;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class TermServiceImpl implements TermService {

    @Autowired
    private TermRepository termRepository;

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Override
    public Object findAllFree(DateTimeJSON dateTimeJSON) {
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
                //System.out.println("Jedan true");
                busyPharmacist.add(terms.get(i).getPharmacist());
                //System.out.println(busyPharmacist.get(0).getId());
            }
            //pregled traje 30 min., Da li je vreme kraja u opsegu vec zakazanog termina
            if(date1.after(terms.get(i).getStart()) && date1.before(terms.get(i).getEnd())){
                //System.out.println("JEdan true");
                busyPharmacist.add(terms.get(i).getPharmacist());
            }

        }

        //lista svih apoteka
        List<Pharmacy> pharmacies = pharmacyRepository.findAll();

        for(int i=0; i<pharmacies.size(); i++){
            System.out.printf("Apoteka %d ima %d farmaceuta",i,pharmacies.get(i).getPharmacists().size());
        }

        //OVDE JE GRESKA u dvostrukom for-u
        //proci kroz sve apoteke
        for(int j=0; j<busyPharmacist.size(); j++){
            //System.out.println(j);
            //odnosno kroz sve zauzete farmaceute
            for(int k=0; k<pharmacies.size(); k++){
                if(pharmacies.get(k).getPharmacists().contains(busyPharmacist.get(j))){
                    pharmacies.remove(pharmacies.get(k));
                }
            }

        }

        for(int i=0; i<pharmacies.size(); i++){
            System.out.printf("Apoteka %d ima %d farmaceuta",i,pharmacies.get(i).getPharmacists().size());
            }
        System.out.println(pharmacies.size());

        List<Pharmacy> retPharmacies = new ArrayList<>();
        //vrati apoteke cija lista farmaceuta nije prazna, izbacila sam zauzete
        for(int i=0; i<pharmacies.size(); i++){
            if(pharmacies.get(i).getPharmacists().size() != 0){

                retPharmacies.add(pharmacies.get(i));
            }
        }

        System.out.println(retPharmacies.size());

        return retPharmacies;
    }
}
