package main.resources.ui.service;

import main.java.tp.eni.gsc.prof.GRADE;
import main.java.tp.eni.gsc.prof.ServiceProf;
import main.java.tp.eni.gsc.prof.bean.Prof;
import main.java.tp.eni.gsc.salle.ServiceSalle;
import main.java.tp.eni.gsc.salle.bean.Salle;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SalleServiceUI {
    public static Object[][] getAllSalle(String key){
        ServiceSalle service = new ServiceSalle();
        List<Salle> salleList =  service.getSalles(key);

        ArrayList<Salle> test = new ArrayList<>();
        salleList.forEach(salle -> {
            test.add(salle);
        });
        int i=0;
        Object[][] data = new Object[test.size()][3];
        while(i<test.size()){
            data[i][0] = test.get(i).getSalleId();
            data[i][1] = test.get(i).getSalleCode();
            data[i][2] = test.get(i).getSalleDesignation();
            i++;
        }
        return data;
    }
    public static Salle saveSalle(Salle salle){
        ServiceSalle service = new ServiceSalle();
       return service.addSalle(salle);
    }
    public static Salle updateSalle(Salle salle){
        ServiceSalle service = new ServiceSalle();
        return service.updateSalle(salle);
    }
    public static void deleteSalle(Salle salle){
        ServiceSalle service = new ServiceSalle();
        service.deleteSalle(salle);
    }
}