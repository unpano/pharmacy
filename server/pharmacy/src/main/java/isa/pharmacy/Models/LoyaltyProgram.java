package isa.pharmacy.Models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LoyaltyProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer lowerBound;
    private Integer upperBound;
    private Integer discountPercentage;

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

    public Integer getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(Integer lowerBound) {
        this.lowerBound = lowerBound;
    }

    public Integer getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(Integer upperBound) {
        this.upperBound = upperBound;
    }

    public Integer getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Integer discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}
