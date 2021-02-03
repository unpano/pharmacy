package isa.pharmacy.Models;

import javax.persistence.*;
import java.util.HashSet;
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

    //Apoteka ima listu lekova,
    @OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Med> meds = new HashSet<>();

    public Pharmacy(){
        super();
    }

    public Pharmacy(String name, String address, String city, float avgRank) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.avgRank = avgRank;
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

}
