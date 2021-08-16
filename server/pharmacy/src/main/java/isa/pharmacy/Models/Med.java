package isa.pharmacy.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="meds")
public class Med {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private Form form;
    @ElementCollection
    private List<String> ingredients;
    private String producer;
    @ElementCollection
    private List<String> replacementCode;
    private IssuanceRegime issuanceRegime; //rezim izdavanja
    private String additionalNotes;
    private float price;

    //Lek ima listu apoteka u kojima je zastupljen
    @JsonIgnore
    @ManyToMany(mappedBy = "meds")
    private List<Pharmacy> pharms = new ArrayList<>();

    public List<Pharmacy> getPharms() {
        return pharms;
    }

    public void setPharms(List<Pharmacy> pharms) {
        this.pharms = pharms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public List<String> getReplacementCode() {
        return replacementCode;
    }

    public void setReplacementCode(List<String> replacementCode) {
        this.replacementCode = replacementCode;
    }

    public IssuanceRegime getIssuanceRegime() {
        return issuanceRegime;
    }

    public void setIssuanceRegime(IssuanceRegime issuanceRegime) {
        this.issuanceRegime = issuanceRegime;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
