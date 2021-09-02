package isa.pharmacy.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import jdk.jfr.Category;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;

@Entity
public class User extends GeneralUser  {

    //broj penala
    private Integer penalties;

    public Integer getPenalties() {
        return penalties;
    }

    public void setPenalties(Integer penalties) {
        this.penalties = penalties;
    }

    //Osvojeni poeni
    private Integer points;

    //Kategorija korisnika
    private UserCategory userCategory;

    //Loyalty program
    @OneToOne
    private LoyaltyProgram loyaltyProgram;

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public UserCategory getUserCategory() {
        return userCategory;
    }

    public void setUserCategory(UserCategory userCategory) {
        this.userCategory = userCategory;
    }

    public LoyaltyProgram getLoyaltyProgram() {
        return loyaltyProgram;
    }

    public void setLoyaltyProgram(LoyaltyProgram loyaltyProgram) {
        this.loyaltyProgram = loyaltyProgram;
    }

    //User ima listu recepata
    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Prescription> prescriptions = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user" )
    private List<Reservation> reservations = new ArrayList<>();

    @JsonIgnore
    @OneToMany
    private List<Rate> rates = new ArrayList<>();

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }

    public Set<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(Set<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<DermAppointment> dermAppointments = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<Term> pharmAppointments = new HashSet<>();

    public Set<Term> getPharmAppointments() {
        return pharmAppointments;
    }

    public void setPharmAppointments(Set<Term> pharmAppointments) {
        this.pharmAppointments = pharmAppointments;
    }


    public Set<DermAppointment> getDermAppointments() {
        return dermAppointments;
    }

    public void setDermAppointments(Set<DermAppointment> dermAppointments) {
        this.dermAppointments = dermAppointments;
    }

    //User ima listu lekova na koje je alergican
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_allergy",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "med_id", referencedColumnName = "id"))
    private List<Med> allergies;

    //User ima listu apoteka na cije promocije je pretplacen
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_promoted_pharmacy",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "pharmacy_id", referencedColumnName = "id"))
    private List<Pharmacy> pharmacies;

    public List<Pharmacy> getPharmacies() {
        return pharmacies;
    }

    public void setPharmacies(List<Pharmacy> pharmacies) {
        this.pharmacies = pharmacies;
    }

    public List<Med> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<Med> allergies) {
        this.allergies = allergies;
    }






}
