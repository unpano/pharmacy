package isa.pharmacy.Models;

import javax.persistence.*;

@Entity
public class OrderOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne( fetch = FetchType.EAGER)
    private MedsOrder medsOrder;

    private Long price;

    private String name;

    private boolean answered;

    private boolean accepted;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MedsOrder getMedsOrder() {
        return medsOrder;
    }

    public void setMedsOrder(MedsOrder medsOrder) {
        this.medsOrder = medsOrder;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public OrderOffer() {
    }



}







