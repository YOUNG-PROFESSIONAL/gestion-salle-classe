package main.java.tp.eni.gsc.salle.bean;

import main.java.tp.eni.gsc.prof.bean.Prof;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "salle")
public class Salle {
    @Id
    private String salleId;
    private String salleDesignation;
    private String salleCode;
    @OneToOne()
    private Prof salleProf;

    public Salle(String salleId, String salleDesignation, String salleCode) {
        this.salleId = salleId;
        this.salleDesignation = salleDesignation;
        this.salleCode = salleCode;
    }

    public String getSalleId() {
        return salleId;
    }

    public void setSalleId(String salleId) {
        this.salleId = salleId;
    }

    public String getSalleDesignation() {
        return salleDesignation;
    }

    public void setSalleDesignation(String salleDesignation) {
        this.salleDesignation = salleDesignation;
    }

    public String getSalleCode() {
        return salleCode;
    }

    public void setSalleCode(String salleCode) {
        this.salleCode = salleCode;
    }

    public Prof getSalleProf() {
        return salleProf;
    }

    public void setSalleProf(Prof salleProf) {
        this.salleProf = salleProf;
    }
}
