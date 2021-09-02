package isa.pharmacy.Controllers;

import isa.pharmacy.Models.*;
import isa.pharmacy.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

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

    @GetMapping("/meds")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> findMedsByUserId(Principal user1){
        Optional<User> user = Optional.ofNullable(userService.findByUsername(user1.getName()));

        List<Med> allergies = user.get().getAllergies();

        if (user.isPresent()){
            return new ResponseEntity<>(allergies, HttpStatus.OK);
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
    @PreAuthorize("hasRole('USER')")
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

    @GetMapping("/profile")
    @PreAuthorize("hasRole('USER')")
    public User user(Principal user) {
        return this.userService.findByUsername(user.getName());
    }

    @GetMapping("/dermatologists")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> findDermatologists(Principal user){
        Optional<User> user1 = Optional.ofNullable(userService.findByUsername(user.getName()));

        List<Dermatologist> dermatologists = userService.findDermatologists(user1.get().getId());


        if (user1.isPresent()){
            return new ResponseEntity<>(dermatologists, HttpStatus.OK);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/dermatologists/rated")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> findRatedDermatologists(Principal user){
        Optional<User> user1 = Optional.ofNullable(userService.findByUsername(user.getName()));

        List<Dermatologist> ratedDermatologists = userService.findRatedDermatologists(user1.get().getId());


        if (user1.isPresent()){
            return new ResponseEntity<>(ratedDermatologists, HttpStatus.OK);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/pharmacists")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> findPharmacists(Principal user){
        Optional<User> user1 = Optional.ofNullable(userService.findByUsername(user.getName()));

        List<Pharmacist> pharmacists = userService.findPharmacists(user1.get().getId());


        if (user1.isPresent()){
            return new ResponseEntity<>((pharmacists), HttpStatus.OK);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/pharmacists/rated")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> findRatedPharmacists(Principal user){
        Optional<User> user1 = Optional.ofNullable(userService.findByUsername(user.getName()));

        List<Pharmacist> ratedPharmacists = userService.findRatedPharmacists(user1.get().getId());

        if (user1.isPresent()){
            return new ResponseEntity<>(ratedPharmacists, HttpStatus.OK);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

}
