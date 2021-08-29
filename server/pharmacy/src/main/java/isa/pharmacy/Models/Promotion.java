package isa.pharmacy.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="promotion")
public class Promotion {






        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private Date startDate;

        private Date endDate;

        public String title;

        public String content;


    @ManyToOne(fetch = FetchType.EAGER)
    private Pharmacy pharmacy;



    public Promotion() {
    }

    public Promotion(Long id, Date startDate, Date endDate, String title, String content, Pharmacy pharmacy) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.content = content;
        this.pharmacy = pharmacy;
    }

    public Long getId() {
        return id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }
}
