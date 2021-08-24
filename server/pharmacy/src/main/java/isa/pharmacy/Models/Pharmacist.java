package isa.pharmacy.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Pharmacist extends GeneralUser {

    //Prosecna ocena farmaceuta
    private Float stars;

    private Time startWorking;
    private Time endWorking;

    public Float getStars() {
        return stars;
    }

    public void setStars(Float stars) {
        this.stars = stars;
    }

    public Time getStartWorking() {
        return startWorking;
    }

    public void setStartWorking(Time startWorking) {
        this.startWorking = startWorking;
    }

    public Time getEndWorking() {
        return endWorking;
    }

    public void setEndWorking(Time endWorking) {
        this.endWorking = endWorking;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public Set<Term> getTerms() {
        return terms;
    }

    public void setTerms(Set<Term> terms) {
        this.terms = terms;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Pharmacy pharmacy;

    //Farmaceut ima radne sate/termine za preglede koji nisu unapred definisani
    @OneToMany(mappedBy = "pharmacist")
    private Set<Term> terms = new HashSet<>();

}
