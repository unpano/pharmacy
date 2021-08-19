package isa.pharmacy.Models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@DiscriminatorValue("ROLE_DERMATOLOGIST")
public class Dermatologist extends User{


    //Prosecna ocena dermatologa
    private Float stars;


    @OneToMany(mappedBy = "dermatologist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<DermAppointment> dermAppointments = new HashSet<>();

    public Dermatologist() {

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

    public Dermatologist(Long id, String firstName, String lastName, String email, String password, String username, String address, String city, String country, String phoneNumber, boolean enabled, Timestamp lastPasswordResetDate, List<Authority> authorities, Set<Prescription> prescriptions, Float stars, Set<DermAppointment> dermAppointments) {
        super(id, firstName, lastName, email, password, username, address, city, country, phoneNumber, enabled, lastPasswordResetDate, authorities, prescriptions);
        this.stars = stars;
        this.dermAppointments = dermAppointments;
    }
}
