package isa.pharmacy.Controllers;

import isa.pharmacy.Models.*;
import isa.pharmacy.Services.DermAppointmentService;
import isa.pharmacy.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MultiValueMap;
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
    @Autowired
    private DermAppointmentService dermAppointmentService;

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
    @PreAuthorize("hasRole('USER')")
    public User user(Principal user) {
        //System.out.println(user.getName()); //VRACA USERNAME
        //System.out.println("Dosao sam bre dovde");
        return this.userService.findByUsername(user.getName());
    }

}
