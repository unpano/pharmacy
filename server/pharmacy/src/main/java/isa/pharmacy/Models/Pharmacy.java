package isa.pharmacy.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "pharmacies")
public class Pharmacy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private float avgRank;

    @Column(nullable = false)
    private float pharmacistCouncelingPrice;

    @JsonIgnore
    @OneToMany
    private List<User> subscribedUsers = new ArrayList<>();

    public List<User> getSubscribedUsers() {
        return subscribedUsers;
    }

    public void setSubscribedUsers(List<User> subscribedUsers) {
        this.subscribedUsers = subscribedUsers;
    }

    //Apoteka ima listu lekova
    @JsonIgnore
    @OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PharmacyMed> meds = new ArrayList<>();

    //Apoteka ima listu slobodnih termina kod dermatolooga
    @JsonIgnore
    @OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<DermAppointment> dermAppointments = new HashSet<>();

    //Apoteka ima listu farmaceuta
    @JsonIgnore
    @OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Pharmacist> pharmacists = new ArrayList<>();

    public Pharmacy(){
        super();
    }

    public List<Pharmacist> getPharmacists() {
        return pharmacists;
    }

    public void setPharmacists(List<Pharmacist> pharmacists) {
        this.pharmacists = pharmacists;
    }

    public Pharmacy(String name, String address, String city, float avgRank) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.avgRank = avgRank;
    }

    public float getPharmacistCouncelingPrice() {
        return pharmacistCouncelingPrice;
    }

    public void setPharmacistCouncelingPrice(float pharmacistCouncelingPrice) {
        this.pharmacistCouncelingPrice = pharmacistCouncelingPrice;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public float getAvgRank() {
        return avgRank;
    }

    public void setAvgRank(float avgRank) {
        this.avgRank = avgRank;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<PharmacyMed> getMeds() {
        return meds;
    }

    public void setMeds(List<PharmacyMed> meds) {
        this.meds = meds;
    }


    public Set<DermAppointment> getDermAppointments() {
        return dermAppointments;
    }

    public void setDermAppointments(Set<DermAppointment> dermAppointments) {
        this.dermAppointments = dermAppointments;
    }
}
