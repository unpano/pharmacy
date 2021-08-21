package isa.pharmacy.Models;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
public class WorkHours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;
    private Time start;
    private Time end;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Pharmacist pharmacist;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStart() {
        return start;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public Time getEnd() {
        return end;
    }

    public void setEnd(Time end) {
        this.end = end;
    }

    public WorkHours(){}

    public WorkHours(Long id, Date date, Time start, Time end) {
        this.id = id;
        this.date = date;
        this.start = start;
        this.end = end;
    }
}
