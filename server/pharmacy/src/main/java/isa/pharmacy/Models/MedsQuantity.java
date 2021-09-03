package isa.pharmacy.Models;

import javax.persistence.*;

@Entity
public class MedsQuantity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "med_id")
    private Med med;

    @ManyToOne
    private MedsOrder medsOrder;


    public MedsOrder getMedsOrder() {
        return medsOrder;
    }

    public void setMedsOrder(MedsOrder medsOrder) {
        this.medsOrder = medsOrder;
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

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Med getMed() {
        return med;
    }

    public void setMed(Med med) {
        this.med = med;
    }

    public MedsQuantity() {
    }
}
