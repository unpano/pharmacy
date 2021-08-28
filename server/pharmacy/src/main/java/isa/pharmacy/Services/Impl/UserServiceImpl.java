package isa.pharmacy.Services.Impl;

import isa.pharmacy.Models.*;
import isa.pharmacy.Repositories.UserRepository;
import isa.pharmacy.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityService authService;

    @Autowired
    private RateService rateService;

    @Autowired
    private DermAppointmentService dermAppointmentService;

    @Autowired
    private TermService termService;

    @Override
    public User findByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);

    }

    public Page<User> findAll() throws AccessDeniedException {
        return (Page<User>) userRepository.findAll();
    }

    public Optional<User> findById(Long id) throws AccessDeniedException {
        return userRepository.findById(id);
    }


    public User add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        List<Authority> auth = authService.findByName("ROLE_USER");
        user.setAuthorities(auth);


        userRepository.save(user);


        return user;
    }

    public Optional<User> delete(Long id) {
        Optional<User> userOpt = userRepository.findById(id);

        if (userOpt.isPresent()) {
            userRepository.delete(userOpt.get());
            return userOpt;
        }

        return Optional.empty();
    }

    public Optional<User> update(User user) {
        Optional<User> userOpt = userRepository.findById(user.getId());


        if (userOpt.isPresent()) {
            User existingUser = userOpt.get();

            if (user.getFirstName() != null) {
                existingUser.setFirstName(user.getFirstName());
            }

            if (user.getLastName() != null) {
                existingUser.setLastName(user.getLastName());
            }

            if (user.getUsername() != null) {
                existingUser.setUsername(user.getUsername());
            }


            if (user.getAddress() != null) {
                existingUser.setAddress(user.getAddress());
            }

            if (user.getCity() != null) {
                existingUser.setCity(user.getCity());
            }

            if (user.getCountry() != null) {
                existingUser.setCountry(user.getCountry());
            }

            if (user.getPhoneNumber() != null) {
                existingUser.setPhoneNumber(user.getPhoneNumber());
            }

            userRepository.save(existingUser);

            return Optional.of(existingUser);
        }

        return Optional.empty();
    }

    //metoda koje se okine svakog prvog u mesecu i obrise sve penale svih user-a
    //testirano za svaki minut po sablonu cron="0 0/1 * * * ?" i radi
    //Penali se inace dobijaju tako sto se uvecava polje penalties u klasi User
    //kada ne dodje na termin i sl.
    @Scheduled(cron = "0 0 0 1 * ?")
    public void erasePenalties(){
        //System.out.println("Opalio");
        List<User> users = userRepository.findAll();

        for(int i=0; i< users.size(); i++){
            users.get(i).setPenalties(0);
            userRepository.save(users.get(i));
        }
    }

    @Override
    public List<Dermatologist> findDermatologists(Long id) {

        List<Dermatologist> dermatologists = findDerms(id);

        //izbaci iz liste one koji su vec ocenjeni
        List<Rate> rates = rateService.findAllByUserIdAndWhomRates(id,WhomRates.DERMATOLOGIST);

        for(int i=0; i<dermatologists.size(); i++){
            for(int j=0; j<rates.size(); j++){
                if(dermatologists.get(i).getId() == rates.get(j).getIdOfRatedObject()){
                    dermatologists.remove(dermatologists.get(i));
                }
            }

        }

        return  dermatologists;
    }

    @Override
    public List<Dermatologist> findRatedDermatologists(Long id) {
        List<Dermatologist> dermatologists = findDerms(id);

        //naci one koji su ocenjeni
        List<Rate> rates = rateService.findAllByUserIdAndWhomRates(id,WhomRates.DERMATOLOGIST);
        List<Dermatologist> ratedDermatologists = new ArrayList<>();

        for(int i=0; i<dermatologists.size(); i++){
            for(int j=0; j<rates.size(); j++){
                if(dermatologists.get(i).getId() == rates.get(j).getIdOfRatedObject()){
                    dermatologists.get(i).setStars(rates.get(j).getRate());
                    ratedDermatologists.add(dermatologists.get(i));

                }
            }

        }
        return ratedDermatologists;
    }

    @Override
    public List<Pharmacist> findPharmacists(Long id) {
        List<Pharmacist> pharmacists = findPharms(id);

        //izbaci iz liste one koji su vec ocenjeni
        List<Rate> rates = rateService.findAllByUserIdAndWhomRates(id,WhomRates.PHARMACIST);

        for(int i=0; i<pharmacists.size(); i++){
            for(int j=0; j<rates.size(); j++){
                if(pharmacists.get(i).getId() == rates.get(j).getIdOfRatedObject()){
                    pharmacists.remove(pharmacists.get(i));
                }
            }

        }

        return pharmacists;
    }

    @Override
    public List<Pharmacist> findRatedPharmacists(Long id) {

        List<Pharmacist> pharmacists = findPharms(id);

        //naci one koji su ocenjeni
        List<Rate> rates = rateService.findAllByUserIdAndWhomRates(id,WhomRates.PHARMACIST);
        List<Pharmacist> ratedPharmacists = new ArrayList<>();

        for(int i=0; i<pharmacists.size(); i++){
            for(int j=0; j<rates.size(); j++){
                if(pharmacists.get(i).getId() == rates.get(j).getIdOfRatedObject()){
                    pharmacists.get(i).setStars(rates.get(j).getRate());
                    ratedPharmacists.add(pharmacists.get(i));
                }
            }

        }
        return  ratedPharmacists;

    }

    public List<Pharmacist> findPharms(Long userId){
        List<Term> terms = termService.findAllByUserId(userId);
        List<Pharmacist> pharmacists = new ArrayList<>();

        //proci i vratiti farmaceute
        for (int i=0; i<terms.size(); i++){
            //da ne ponavljam iste farmaceute
            if(!pharmacists.contains(terms.get(i).getPharmacist()))
                pharmacists.add(terms.get(i).getPharmacist());
        }

        return  pharmacists;
    }

    public List<Dermatologist> findDerms(Long userId){
        List<DermAppointment> appointments = dermAppointmentService.findAllByUserId(userId);
        List<Dermatologist> dermatologists = new ArrayList<>();

        //proci i vratiti dermatologe
        for (int i=0; i<appointments.size(); i++){
            //da ne ponavljam dermatologe i ako user nije setovan znaci da se pregled nije ni desio
            if(appointments.get(i).getUser() != null && !dermatologists.contains(appointments.get(i).getDermatologist()))
                dermatologists.add(appointments.get(i).getDermatologist());
        }

        return  dermatologists;
    }
}
