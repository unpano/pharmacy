package isa.pharmacy.Services.Impl;

import isa.pharmacy.Models.*;
import isa.pharmacy.Repositories.RateRepository;
import isa.pharmacy.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.OptimisticLockException;
import java.util.List;
import java.util.Optional;

@Service
public class RateServiceImpl implements RateService {

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private DermatologistService dermatologistService;

    @Autowired
    private PharmacistService pharmacistService;

    @Autowired
    private MedService medService;

    @Autowired
    private PharmacyService pharmacyService;

    @Override
    public Object addRate(Rate rate1) {
        return rateRepository.save(rate1);
    }

    @Override
    public List<Rate> findAllByUserIdAndWhomRates(Long id, WhomRates whom) {
        return rateRepository.findAllByUserIdAndWhomRates(id,whom);
    }

    @Override
    public List<Rate> findAll() {
        return rateRepository.findAll();
    }

    @Override
    public Optional<Rate> findById(Long rateId) {
        return rateRepository.findById(rateId);
    }

    @Override
    public Rate update(Rate rate1) {
        return rateRepository.save(rate1);
    }

    @Override
    public Optional<Rate> findByUserIdAndIdOfRatedObjectAndWhomRates(Long id, Long objectId, WhomRates whom) {
        return rateRepository.findByUserIdAndIdOfRatedObjectAndWhomRates(id,objectId,whom);
    }

    @Override
    public List<Rate> findAllByIdOfRatedObjectAndWhomRates(Long objId, WhomRates whomRates) {
        return rateRepository.findAllByIdOfRatedObjectAndWhomRates(objId,whomRates);
    }

    @Override
    public Object rate(User user, Long objectId, Float rate, WhomRates whom) {
        //Kreiram rate
        Rate rate1 = new Rate();
        rate1.setIdOfRatedObject(objectId);
        rate1.setRate(rate);
        rate1.setUser(user);
        rate1.setWhomRates(whom);

        //provera da li je vec kreirana takva ocena
        if(!rateRepository.findByUserIdAndIdOfRatedObjectAndWhomRates(user.getId(),objectId, whom)
                .equals(Optional.empty())){
            throw new OptimisticLockException();
        }

        Object o = addRate(rate1);

        //poziv metode koja ce ponovo da izracuna prosecnu ocenu
        //i da je sacuva

        if(whom == WhomRates.DERMATOLOGIST){
            Optional<Dermatologist> dermatologist = dermatologistService.findById(objectId);
            float avg = calculateAvgRate(objectId,WhomRates.DERMATOLOGIST);
            dermatologist.get().setStars(avg);
            dermatologistService.save(dermatologist.get());
        }else if(whom == WhomRates.PHARMACIST){
            Optional<Pharmacist> pharmacist = pharmacistService.findById(objectId);
            float avg = calculateAvgRate(objectId,WhomRates.PHARMACIST);
            pharmacist.get().setStars(avg);
            pharmacistService.save(pharmacist.get());
        }else if(whom == WhomRates.MED){
            Optional<Med> med = medService.findById(objectId);
            float avg = calculateAvgRate(objectId,WhomRates.MED);
            med.get().setStars(avg);
            medService.save(med.get());
        }else{
            Optional<Pharmacy> pharmacy = pharmacyService.findById(objectId);
            float avg = calculateAvgRate(objectId,WhomRates.PHARMACY);
            pharmacy.get().setAvgRank(avg);
            pharmacyService.save(pharmacy.get());
        }
        return o;

    }

    public Float calculateAvgRate (Long objId, WhomRates whomRates){

        //imam id dermatologa
        //nadjem ga u bazi
        //prodjem kroz sve njegove rate-ove
        //sve saberem i podelim sa brojem ocena
        List<Rate> rates = rateRepository.findAllByIdOfRatedObjectAndWhomRates(objId,whomRates);
        int howManyRates = 0;
        float sum = 0;

        for (int i=0; i<rates.size(); i++){

            sum += rates.get(i).getRate();
            howManyRates += 1;
            System.out.println(sum);
            System.out.println(howManyRates);
        }

        return sum/howManyRates;
    }

    @Override
    public Rate changeRate(User user, Long objectId, Float rate, String whom) {
        WhomRates whomRates;

        //Nadjem rate i izmenim ga
        Optional<Rate> rate1;
        if(whom.equals("DERMATOLOGIST")){
            rate1 = rateRepository.findByUserIdAndIdOfRatedObjectAndWhomRates(user.getId(),objectId,WhomRates.DERMATOLOGIST);
            whomRates = WhomRates.DERMATOLOGIST;

        }
        else if(whom.equals("PHARMACIST")){
            rate1 = rateRepository.findByUserIdAndIdOfRatedObjectAndWhomRates(user.getId(),objectId,WhomRates.PHARMACIST);
            whomRates = WhomRates.PHARMACIST;

        }
        else if(whom.equals("MED")){
            rate1 = rateRepository.findByUserIdAndIdOfRatedObjectAndWhomRates(user.getId(),objectId,WhomRates.MED);
            whomRates = WhomRates.MED;

        }
        else{
            rate1 = rateRepository.findByUserIdAndIdOfRatedObjectAndWhomRates(user.getId(),objectId,WhomRates.PHARMACY);
            whomRates = WhomRates.PHARMACY;

        }


        rate1.get().setRate(rate);
        update(rate1.get());

        //poziv metode koja ce ponovo da izracuna prosecnu ocenu
        //i da je sacuva
        float avg = calculateAvgRate(objectId,whomRates);

        if(whomRates == WhomRates.DERMATOLOGIST){
            Optional<Dermatologist> dermatologist = dermatologistService.findById(objectId);
            dermatologist.get().setStars(avg);
            dermatologistService.save(dermatologist.get());
        }else if(whomRates == WhomRates.PHARMACIST){
            Optional<Pharmacist> pharmacist = pharmacistService.findById(objectId);
            pharmacist.get().setStars(avg);
            pharmacistService.save(pharmacist.get());
        }else if(whomRates == WhomRates.MED){
            Optional<Med> med = medService.findById(objectId);
            med.get().setStars(avg);
            medService.save(med.get());
        }else{
            Optional<Pharmacy> pharmacy = pharmacyService.findById(objectId);
            pharmacy.get().setAvgRank(avg);
            System.out.println("Ovde puca");
            pharmacyService.save(pharmacy.get());
        }

        return rate1.get();

    }
}
