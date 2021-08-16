package isa.pharmacy.Models;

import javax.persistence.*;

@Entity
@Table(name="dermatologist")
public class Dermatologist extends User{

    //Prosecna ocena dermatologa
    private Float stars;

    public Float getStars() {
        return stars;
    }

    public void setStars(Float stars) {
        this.stars = stars;
    }
}
