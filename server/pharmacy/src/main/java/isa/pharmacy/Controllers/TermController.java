package isa.pharmacy.Controllers;

import isa.pharmacy.Models.DateTimeJSON;
import isa.pharmacy.Services.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@RestController
@RequestMapping("/terms")
public class TermController {

    @Autowired
    private TermService termService;



    //Nadji sve apoteke koje imaju slobodan termin kod farmaceuta
    @PutMapping("/getAllFree/{time}/{date}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> findAllFree(@PathVariable String time, @PathVariable String date) throws ParseException {
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
}
