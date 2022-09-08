package main.java.tp.eni.gsc.occuper.bean;

import main.java.tp.eni.gsc.prof.bean.Prof;
import main.java.tp.eni.gsc.salle.bean.Salle;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
public class Occuper {
    @Id
    private String occuperfId;
    @ManyToOne
    private Prof occuperProf;
    @ManyToOne
    private Salle occuperSalle;

    private Date occuperDate;

    public Occuper() {
    }

    public Occuper(String occuperfId, Prof occuperProf, Salle occuperSalle, Date occuperDate) {
        this.occuperfId = occuperfId;
        this.occuperProf = occuperProf;
        this.occuperSalle = occuperSalle;
        this.occuperDate = occuperDate;
    }

    public String getOccuperfId() {
        return occuperfId;
    }

    public void setOccuperfId(String occuperfId) {
        this.occuperfId = occuperfId;
    }

    public Prof getOccuperProf() {
        return occuperProf;
    }

    public void setOccuperProf(Prof occuperProf) {
        this.occuperProf = occuperProf;
    }

    public Salle getOccuperSalle() {
        return occuperSalle;
    }

    public void setOccuperSalle(Salle occuperSalle) {
        this.occuperSalle = occuperSalle;
    }

    public Date getOccuperDate() {
        return occuperDate;
    }

    public void setOccuperDate(Date occuperDate) {
        this.occuperDate = occuperDate;
    }
}
