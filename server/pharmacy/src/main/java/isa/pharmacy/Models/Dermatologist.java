package isa.pharmacy.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Dermatologist extends GeneralUser{

    //Prosecna ocena dermatologa
    
    private Float stars;

    private Time startTime;

    private Time endTime;

    @JsonIgnore
    @OneToMany(mappedBy = "dermatologist", fetch = FetchType.LAZY)
    private Set<DermAppointment> dermAppointments = new HashSet<>();

    public Dermatologist() {
        super();
    }

    public Set<DermAppointment> getDermAppointments() {
        return dermAppointments;
    }

    public void setDermAppointments(Set<DermAppointment> dermAppointments) {
        this.dermAppointments = dermAppointments;
    }

    public Float getStars() {
        return stars;
    }

    public void setStars(Float stars) {
        this.stars = stars;
    }

    public Dermatologist(Float stars, Set<DermAppointment> dermAppointments, Time startTime, Time endTime) {
        this.stars = stars;
        this.dermAppointments = dermAppointments;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }





}
