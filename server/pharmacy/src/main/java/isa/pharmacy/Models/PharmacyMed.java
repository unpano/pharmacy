package isa.pharmacy.Models;

import javax.persistence.*;

@Entity
public class PharmacyMed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "pharmacy_id")
    private Pharmacy pharmacy;

    @ManyToOne
    @JoinColumn(name = "med_id")
    private Med med;

    @Column(name = "quantity")
    private Integer quantity;

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public Med getMed() {
        return med;
    }

    public void setMed(Med med) {
        this.med = med;
    }

    public Integer isQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public PharmacyMed(Pharmacy pharmacy, Med med, Integer quantity) {
        this.pharmacy = pharmacy;
        this.med = med;
        this.quantity = quantity;
    }

    public PharmacyMed(){}
}
