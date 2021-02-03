package isa.pharmacy.Component;

import isa.pharmacy.Models.Pharmacy;
import isa.pharmacy.Repositories.PharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class LoadPharmaciesInDB implements CommandLineRunner {

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Override
    public void run(String... args) throws Exception {

        if (pharmacyRepository.count() > 0) {
            return;
        }

        Pharmacy pharmacy = new Pharmacy("Krsenkovic","Uspenska 37", "Novi Sad", 4.5f);
        pharmacyRepository.save(pharmacy);

        pharmacy = new Pharmacy("Benu","Miselukova 13","Novi Sad",3.0f);
        pharmacyRepository.save(pharmacy);

        pharmacy = new Pharmacy("Jankovic","Zmaj Jovina 3","Novi Sad", 4.8f);
        pharmacyRepository.save(pharmacy);

        pharmacy = new Pharmacy("Drzavna", "Putnikova 3","Novi Sad", 4.7f);
        pharmacyRepository.save(pharmacy);
    }
}