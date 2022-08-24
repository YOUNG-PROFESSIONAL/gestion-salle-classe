package main.java.tp.eni.gsc.prof.bean;

import main.java.tp.eni.gsc.prof.GRADE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "professor")
public class Prof implements Serializable {
    @Id
    private String profId;
    private String profNom;
    private String profPrenom;
    private GRADE profGrade;
    @Column(unique = true)
    private String profMatricule;



    public Prof() {
    }
    public Prof(String profId, String profNom, String profPrenom, GRADE profGrade, String matricule) {
        this.profId = profId;
        this.profNom = profNom;
        this.profPrenom = profPrenom;
        this.profGrade = profGrade;
        this.profMatricule = matricule;
    }

    public String getProfId() {
        return profId;
    }

    public void setProfId(String profId) {
        this.profId = profId;
    }

    public String getProfNom() {
        return profNom;
    }

    public void setProfNom(String profNom) {
        this.profNom = profNom;
    }

    public String getProfPrenom() {
        return profPrenom;
    }

    public void setProfPrenom(String profPrenom) {
        this.profPrenom = profPrenom;
    }

    public GRADE getProfGrade() {
        return profGrade;
    }

    public void setProfGrade(GRADE profGrade) {
        this.profGrade = profGrade;
    }

    public String getProfMatricule() {
        return profMatricule;
    }

    public void setProfMatricule(String profMatricule) {
        this.profMatricule = profMatricule;
    }

}
