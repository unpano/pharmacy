package isa.pharmacy.Controllers;

import isa.pharmacy.Models.DateTimeJSON;
import isa.pharmacy.Models.User;
import isa.pharmacy.Services.TermService;
import isa.pharmacy.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping("/terms")
public class TermController {

    @Autowired
    private TermService termService;

    @Autowired
    private UserService userService;

    //Nadji sve apoteke koje imaju slobodan termin kod farmaceuta
    @GetMapping("/getAllFreePharmacies/{time}/{date}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> findAllFreePharmacies(@PathVariable String time, @PathVariable String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
        Date dateValue = formatter.parse(date+' '+time);
        //System.out.println(dateParse);

        SimpleDateFormat formatter1 = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        Time timeValue = new Time(formatter1.parse(time).getTime());
        //System.out.println(timeValue);

        DateTimeJSON dateTimeJSON = new DateTimeJSON();
        dateTimeJSON.setDate(dateValue);
        dateTimeJSON.setTime(timeValue);

        return new ResponseEntity<>(this.termService.findAllFree(dateTimeJSON), HttpStatus.OK) ;
    }

    //Nadji sve farmaceute koje imaju slobodan termin
    @GetMapping("/getAllFreePharmacists/{time}/{date}/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> findAllFreePharmacists(@PathVariable String time, @PathVariable String date, @PathVariable Long id) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
        Date dateValue = formatter.parse(date+' '+time);
        //System.out.println(dateParse);

        SimpleDateFormat formatter1 = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        Time timeValue = new Time(formatter1.parse(time).getTime());
        //System.out.println(timeValue);

        DateTimeJSON dateTimeJSON = new DateTimeJSON();
        dateTimeJSON.setDate(dateValue);
        dateTimeJSON.setTime(timeValue);

        return new ResponseEntity<>(this.termService.findAllFreePharmacists(dateTimeJSON,id), HttpStatus.OK) ;
    }

    //Nadji sve farmaceute koje imaju slobodan termin
    @PutMapping("/add/{pharmacistId}/{date}/{time}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> addTerm(@PathVariable Long pharmacistId, Principal user1,
                                     @PathVariable String date, @PathVariable String time) throws ParseException {
        Optional<User> user = Optional.ofNullable(userService.findByUsername(user1.getName()));
        Long userId = user.get().getId();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
        Date dateValue = formatter.parse(date+' '+time);
        Optional<User> optUser = userService.findById(userId);

        if(optUser.get().getPenalties() <= 3){
            return new ResponseEntity<>(this.termService.add(dateValue,userId,pharmacistId), HttpStatus.OK) ;

        }

        return new ResponseEntity<>(null,HttpStatus.OK);

    }

    //vraca buduce termine na osnovu id-ja korisnika
    @GetMapping("/futureTerms")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> findFutureTermsByUserId(Principal user){
        //nadjem user-a
        Optional<User> optUser = Optional.ofNullable(userService.findByUsername(user.getName()));

        return new ResponseEntity<>(termService.findFutureTermsByUserId(optUser.get().getId()), HttpStatus.OK);

    }

    //vraca buduce termine na osnovu id-ja korisnika
    @GetMapping("/pharmacist/{termId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> findPharmacistByTermId(@PathVariable Long termId){

        return new ResponseEntity<>(termService.findPharmacistByTermId(termId), HttpStatus.OK);

    }

    //vraca buduce termine na osnovu id-ja korisnika
    @GetMapping("/pastTerms")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> findPastTermsByUserId(Principal user){
        //nadjem user-a
        Optional<User> optUser = Optional.ofNullable(userService.findByUsername(user.getName()));

        return new ResponseEntity<>(termService.findPastTermsByUserId(optUser.get().getId()), HttpStatus.OK);

    }

    //User otkazuje pregled
    @PutMapping("/frees/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> freeTerm(Principal user,@PathVariable Long id){
        //nadjem user-a
        Optional<User> optUser = Optional.ofNullable(userService.findByUsername(user.getName()));
        return new ResponseEntity<>(termService.freeTerm(optUser.get().getId(),id), HttpStatus.OK);

    }
}
