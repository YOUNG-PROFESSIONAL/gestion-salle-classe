package main.java.tp.eni.gsc.salle.bean;

import main.java.tp.eni.gsc.occuper.bean.Occuper;
import main.java.tp.eni.gsc.prof.bean.Prof;

import javax.persistence.*;

@Entity
@Table(name = "salle")
public class Salle {
    @Id
    private String salleId;
    @Column(unique = true)
    private String salleCode;
    @Column(unique = true,nullable = false)
    private String salleDesignation;

    public Salle() {
    }

    public Salle(String salleId, String salleCode,String salleDesignation) {
        this.salleId = salleId;
        this.salleCode = salleCode;
        this.salleDesignation = salleDesignation;
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

}
