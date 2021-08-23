package isa.pharmacy.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Dermatologist extends GeneralUser{

    //Prosecna ocena dermatologa
    private Float stars;

    @JsonIgnore
    @OneToMany(mappedBy = "dermatologist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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

    public Dermatologist(Float stars, Set<DermAppointment> dermAppointments) {
        this.stars = stars;
        this.dermAppointments = dermAppointments;
    }


}
