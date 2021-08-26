package isa.pharmacy.Models;

import javax.persistence.*;

@Entity
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idOfRatedObject;
    private WhomRates whomRates;
    private Long rate;
    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdOfRatedObject() {
        return idOfRatedObject;
    }

    public void setIdOfRatedObject(Long idOfRatedObject) {
        this.idOfRatedObject = idOfRatedObject;
    }

    public WhomRates getWhomRates() {
        return whomRates;
    }

    public void setWhomRates(WhomRates whomRates) {
        this.whomRates = whomRates;
    }

    public Long getRate() {
        return rate;
    }

    public void setRate(Long rate) {
        this.rate = rate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
