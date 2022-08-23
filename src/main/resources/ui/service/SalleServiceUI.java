package main.resources.ui.service;

import main.java.tp.eni.gsc.prof.GRADE;
import main.java.tp.eni.gsc.salle.ServiceSalle;

import javax.swing.table.DefaultTableModel;
import java.util.concurrent.atomic.AtomicInteger;

public class SalleServiceUI {
    public static DefaultTableModel getAllSalle(String key){
        Object[] column = {"Numéro","Désignation","Professeur","Occupé","Libre"};
        Object[] data = {"","","","",""};
        AtomicInteger i = new AtomicInteger(1);
        ServiceSalle service = new ServiceSalle();
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(column);

        service.getSalles().stream().forEach(salle -> {
                    data[0] = i.getAndIncrement();
                    data[1] = salle.getSalleDesignation();
                    data[2] = salle.getSalleProf();
                    data[3] = "";
                    data[4] = "";
                    model.addRow(data);
         });
        return  model;
    }
}