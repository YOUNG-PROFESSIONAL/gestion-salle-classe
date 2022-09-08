package main.resources.ui.model;

import java.util.Date;

public class DashBoard {
    private String salleCode;
    private String profNom;
    private Date date;

    public DashBoard() {
    }

    public DashBoard(String salleCode, String profNom, Date date) {
        this.salleCode = salleCode;
        this.profNom = profNom;
        this.date = date;
    }

    public String getSalleCode() {

        return salleCode;
    }

    public void setSalleCode(String salleCode) {
        this.salleCode = salleCode;
    }

    public String getProfNom() {
        return profNom;
    }

    public void setProfNom(String profNom) {
        this.profNom = profNom;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
