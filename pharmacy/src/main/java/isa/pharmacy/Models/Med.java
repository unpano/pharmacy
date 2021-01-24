package isa.pharmacy.Models;

import org.springframework.beans.factory.annotation.Autowired;

public class Med {
    private String code;
    private String name;
    private String type;
    private Form form;
    private String [] ingredients;
    private String producer;
    private String [] replacementCode;
    private IssuanceRegime issuanceRegime; //rezim izdavanja
    private String additionalNotes;

    @Autowired
    private Pharmacy pharmacy;


}
