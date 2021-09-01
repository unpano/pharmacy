package isa.pharmacy.Models;

import javax.persistence.*;

@Entity
public class PharmacyDermatologist {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;


        @ManyToOne
        @JoinColumn(name = "pharmacy_id")
        private Pharmacy pharmacy;

        @ManyToOne
        @JoinColumn(name = "dermatologist_id")
        private Dermatologist dermatologist;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public Dermatologist getDermatologist() {
        return dermatologist;
    }

    public void setDermatologist(Dermatologist dermatologist) {
        this.dermatologist = dermatologist;
    }

    public PharmacyDermatologist(Long id, Pharmacy pharmacy, Dermatologist dermatologist) {
        this.id = id;
        this.pharmacy = pharmacy;
        this.dermatologist = dermatologist;
    }

    public PharmacyDermatologist() {
    }
}
