package isa.pharmacy.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name="derm_appointment")
public class DermAppointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;
    private Integer duration;
    private Integer price;

    private Time startTime;
    private Time endTime;



    @ManyToOne( fetch = FetchType.EAGER)
    //@NotFound(action = NotFoundAction.IGNORE)
    private Dermatologist dermatologist;


    //Pregled pripada tacno jednoj apoteci
    @ManyToOne(fetch = FetchType.EAGER)
    private Pharmacy pharmacy;


    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public DermAppointment(){

    }

    public DermAppointment(Long id, Date date, Integer duration, Integer price, Dermatologist dermatologist, Pharmacy pharmacy, User user) {
        this.id = id;
        this.date = date;
        this.duration = duration;
        this.price = price;
        this.dermatologist = dermatologist;
        this.pharmacy = pharmacy;
        this.user = user;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Dermatologist getDermatologist() {
        return dermatologist;
    }

    public void setDermatologist(Dermatologist dermatologist) {
        this.dermatologist = dermatologist;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
