package isa.pharmacy.Models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "prescriptions")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date date;

    //Recept ima listu lekova
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "prescription_med",
            joinColumns = @JoinColumn(name = "prescription_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "med_id", referencedColumnName = "id"))
    private List<Med> meds;

    //Recept pripada tacno jednom useru
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User user;
}