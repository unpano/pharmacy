package isa.pharmacy.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
public class Term {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date start;
    private Date end;

    //User koji rezervise termin za savetovanje
    @JsonIgnore
    @ManyToOne
    private User user;

    //Farmaceut koji drzi savetovanje
    @JsonIgnore
    @ManyToOne
    private Pharmacist pharmacist;

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Pharmacist getPharmacist() {
        return pharmacist;
    }

    public void setPharmacist(Pharmacist pharmacist) {
        this.pharmacist = pharmacist;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }





    public Term(){}


}
