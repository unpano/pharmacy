package isa.pharmacy.Models;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
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

    //Lek pripada tacno jednoj apoteci
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Pharmacy pharmacy;


}
