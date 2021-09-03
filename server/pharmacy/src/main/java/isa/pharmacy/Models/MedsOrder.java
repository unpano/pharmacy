package isa.pharmacy.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name="meds_order")
public class MedsOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToMany(mappedBy = "medsOrder")
    private List<MedsQuantity> lekovi = new ArrayList<>();

    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    private Pharmacy pharmacy;

    @JsonIgnore
    @OneToMany(mappedBy = "medsOrder", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderOffer> offers = new ArrayList<>();




    public List<OrderOffer> getOffers() {
        return offers;
    }

    public void setOffers(List<OrderOffer> offers) {
        this.offers = offers;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<MedsQuantity> getLekovi() {
        return lekovi;
    }

    public void setLekovi(List<MedsQuantity> lekovi) {
        this.lekovi = lekovi;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public MedsOrder() {
    }
}



