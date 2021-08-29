package isa.pharmacy.Controllers;

import isa.pharmacy.Models.*;
import isa.pharmacy.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private DermAppointmentService dermAppointmentService;
    @Autowired
    private TermService termService;
    @Autowired
    private RateService rateService;

    @Autowired
    private PharmacyService pharmacyService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<Page<User>>(userService.findAll(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<User> userOpt = this.userService.findById(id);

        if (userOpt.isPresent()){
            return new ResponseEntity<>(userOpt.get(), HttpStatus.OK);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/meds/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> findMedsByUserId(@PathVariable Long id){
        Optional<User> user = this.userService.findById(id);
        List<Med> allergies = user.get().getAllergies();

        if (user.isPresent()){
            return new ResponseEntity<>(allergies, HttpStatus.OK);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    //treba vratiti samo one koji nisu pokupljeni
    @GetMapping("/reservations")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> findUserReservations(Principal user){
        Optional<User> user1 = Optional.ofNullable(userService.findByUsername(user.getName()));
        List<Reservation> reservations = user1.get().getReservations();

        //prodji kroz listu i uzmi samo one rezervacije koje nisu preuzete
        for(int i=0; i<reservations.size(); i++){
            if(reservations.get(i).getPickedUp()){
                reservations.remove(reservations.get(i));
            }
        }

        if (user1.isPresent()){
            return new ResponseEntity<>(reservations, HttpStatus.OK);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    //lista recepata
    //ONAJ KO KREIRA RECEPTE NE SME PREPORUCITI UOPSTE LEK NA KOJI JE USER ALERGICAN
    //TO NIJE DEO ULOGE PACIJENTA
    @GetMapping("/prescriptions")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> findUserPrescriptions(Principal user){
        Optional<User> user1 = Optional.ofNullable(userService.findByUsername(user.getName()));
        Set<Prescription> prescriptions = user1.get().getPrescriptions();

        if (user1.isPresent()){
            return new ResponseEntity<>(prescriptions, HttpStatus.OK);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody User user) {
        userService.add(user);

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<User> optUser = userService.delete(id);

        if (optUser.isPresent()) {
            return new ResponseEntity<User>(optUser.get(), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<?> update(@RequestBody User user) {
        Optional<User> optUser = userService.update(user);

        if (optUser.isPresent()) {
            return new ResponseEntity<User>(optUser.get(), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/addAllergy")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> addAllergy(Principal user,@RequestBody Med med) {
        Optional<User> optUser = Optional.ofNullable(userService.findByUsername(user.getName()));
        List<Med> allergies = optUser.get().getAllergies();
        if(!allergies.contains(med))
            allergies.add(med);

        optUser.get().setAllergies(allergies);
        Optional<User> optUser1 = userService.update(optUser.get());

        if (optUser.isPresent()) {
            return new ResponseEntity<User>(optUser1.get(), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/addDermAppointment")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> addAllergy(Principal user, @RequestBody Long appId) {
        //nadjem user-a koji hoce da zakaze pregled
        Optional<User> optUser = Optional.ofNullable(userService.findByUsername(user.getName()));

        //nadjem pregled koji hoce da zakaze
        Optional<DermAppointment> dermApp = dermAppointmentService.findById(appId);

        //nadjem preglede koje user ima i dodam im ovaj koji hoce da zakaze i update-ujem ga
        Set<DermAppointment> dermAppointments = optUser.get().getDermAppointments();
        dermAppointments.add(dermApp.get());
        optUser.get().setDermAppointments(dermAppointments);
        Optional<User> user1 = userService.update(optUser.get());

        //isto uradim i za pregled, setujem usera na pregled
        dermApp.get().setUser(optUser.get());
        dermAppointmentService.update(dermApp.get());

        if (optUser.isPresent()) {
            return new ResponseEntity<User>(user1.get(), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/profile")
    public User user(Principal user) {
        //System.out.println(user.getName()); //VRACA USERNAME
        //System.out.println("Dosao sam bre dovde");
        return this.userService.findByUsername(user.getName());
    }


    @GetMapping("/adminProfile")
    @PreAuthorize("hasRole('ADMIN')")
    public User adminProfile(Principal user) {
        //System.out.println(user.getName()); //VRACA USERNAME
        //System.out.println("Dosao sam bre dovde");
        return this.userService.findByUsername(user.getName());
    }

    @GetMapping("/dermatologists")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<?> findDermatologists(Principal user){
        Optional<User> user1 = Optional.ofNullable(userService.findByUsername(user.getName()));
        List<DermAppointment> appointments = dermAppointmentService.findAllByUserId(user1.get().getId());
        List<Dermatologist> dermatologists = new ArrayList<>();

        //proci i vratiti dermatologe
        for (int i=0; i<appointments.size(); i++){
            //da ne ponavljam dermatologe i ako user nije setovan znaci da se pregled nije ni desio
            if(appointments.get(i).getUser() != null && !dermatologists.contains(appointments.get(i).getDermatologist()))
                dermatologists.add(appointments.get(i).getDermatologist());
        }

        //izbaci iz liste one koji su vec ocenjeni
        List<Rate> rates = rateService.findAllByUserIdAndWhomRates(user1.get().getId(),WhomRates.DERMATOLOGIST);

        for(int i=0; i<dermatologists.size(); i++){
            for(int j=0; j<rates.size(); j++){
                if(dermatologists.get(i).getId() == rates.get(j).getIdOfRatedObject()){
                    dermatologists.remove(dermatologists.get(i));
                }
            }

        }


        if (user1.isPresent()){
            return new ResponseEntity<>(dermatologists, HttpStatus.OK);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/dermatologists/rated")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> findRatedDermatologists(Principal user){
        Optional<User> user1 = Optional.ofNullable(userService.findByUsername(user.getName()));
        List<DermAppointment> appointments = dermAppointmentService.findAllByUserId(user1.get().getId());
        List<Dermatologist> dermatologists = new ArrayList<>();

        //proci i vratiti dermatologe
        for (int i=0; i<appointments.size(); i++){
            //da ne ponavljam dermatologe i ako user nije setovan znaci da se pregled nije ni desio
            if(appointments.get(i).getUser() != null && !dermatologists.contains(appointments.get(i).getDermatologist()))
                dermatologists.add(appointments.get(i).getDermatologist());
        }

        //naci one koji su ocenjeni
        List<Rate> rates = rateService.findAllByUserIdAndWhomRates(user1.get().getId(),WhomRates.DERMATOLOGIST);
        List<Dermatologist> ratedDermatologists = new ArrayList<>();

        for(int i=0; i<dermatologists.size(); i++){
            for(int j=0; j<rates.size(); j++){
                if(dermatologists.get(i).getId() == rates.get(j).getIdOfRatedObject()){
                    dermatologists.get(i).setStars(rates.get(j).getRate());
                    ratedDermatologists.add(dermatologists.get(i));

                }
            }

        }


        if (user1.isPresent()){
            return new ResponseEntity<>(ratedDermatologists, HttpStatus.OK);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/allPharmacists/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> findPharmacistsByPharmacyId(@PathVariable Long id) {

        Optional<Pharmacy> pharm = this.pharmacyService.findById(id);
        List<Pharmacist> pharmacists = pharm.get().getPharmacists();

        if (pharm.isPresent()){
            return new ResponseEntity<>(pharmacists, HttpStatus.OK);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

    }




        @GetMapping("/pharmacists")
        @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
        public ResponseEntity<?> findPharmacists(Principal user){
            Optional<User> user1 = Optional.ofNullable(userService.findByUsername(user.getName()));
            List<Term> terms = termService.findAllByUserId(user1.get().getId());
            List<Pharmacist> pharmacists = new ArrayList<>();

            //proci i vratiti farmaceute
            for (int i=0; i<terms.size(); i++){
                //da ne ponavljam iste farmaceute
                if(!pharmacists.contains(terms.get(i).getPharmacist()))
                    pharmacists.add(terms.get(i).getPharmacist());
            }

            //izbaci iz liste one koji su vec ocenjeni
            List<Rate> rates = rateService.findAllByUserIdAndWhomRates(user1.get().getId(),WhomRates.PHARMACIST);

            for(int i=0; i<pharmacists.size(); i++){
                for(int j=0; j<rates.size(); j++){
                    if(pharmacists.get(i).getId() == rates.get(j).getIdOfRatedObject()){
                        pharmacists.remove(pharmacists.get(i));
                    }
                }

            }


        if (user1.isPresent()){
            return new ResponseEntity<>((pharmacists), HttpStatus.OK);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/pharmacists/rated")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> findRatedPharmacists(Principal user){
        Optional<User> user1 = Optional.ofNullable(userService.findByUsername(user.getName()));
        List<Term> terms = termService.findAllByUserId(user1.get().getId());
        List<Pharmacist> pharmacists = new ArrayList<>();

        //proci i vratiti farmaceute
        for (int i=0; i<terms.size(); i++){
            //da ne ponavljam iste farmaceute
            if(!pharmacists.contains(terms.get(i).getPharmacist()))
                pharmacists.add(terms.get(i).getPharmacist());
        }

        //naci one koji su ocenjeni
        List<Rate> rates = rateService.findAllByUserIdAndWhomRates(user1.get().getId(),WhomRates.PHARMACIST);
        List<Pharmacist> ratedPharmacists = new ArrayList<>();

        for(int i=0; i<pharmacists.size(); i++){
            for(int j=0; j<rates.size(); j++){
                if(pharmacists.get(i).getId() == rates.get(j).getIdOfRatedObject()){
                    pharmacists.get(i).setStars(rates.get(j).getRate());
                    ratedPharmacists.add(pharmacists.get(i));
                }
            }

        }


        if (user1.isPresent()){
            return new ResponseEntity<>(ratedPharmacists, HttpStatus.OK);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

}
