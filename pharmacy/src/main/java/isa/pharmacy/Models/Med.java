package isa.pharmacy.Models;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
public class Med {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String name;
    private String type;
    private Form form;
    private String [] ingredients;
    private String producer;
    private String [] replacementCode;
    private IssuanceRegime issuanceRegime; //rezim izdavanja
    private String additionalNotes;
    private float price;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Pharmacy pharmacy;


}
