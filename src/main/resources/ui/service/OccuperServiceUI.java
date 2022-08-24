package main.resources.ui.service;

import main.java.tp.eni.gsc.occuper.ServiceOccuper;
import main.java.tp.eni.gsc.occuper.bean.Occuper;
import main.java.tp.eni.gsc.salle.ServiceSalle;
import main.java.tp.eni.gsc.salle.bean.Salle;

import java.util.ArrayList;
import java.util.List;

public class OccuperServiceUI {
    public static Object[][] getAllSalle(String key){
        ServiceOccuper service = new ServiceOccuper();
        List<Occuper> salleList =  service.getSalles(null);

        ArrayList<Occuper> test = new ArrayList<>();
        salleList.forEach(salle -> {
            test.add(salle);
        });
        int i=0;
        Object[][] data = new Object[test.size()][4];
        while(i<test.size()){
            data[i][0] = test.get(i).getOccuperfId();
            data[i][1] = test.get(i).getOccuperSalle().getSalleDesignation();
            if(test.get(i).getOccuperProf() != null)
                data[i][2] = test.get(i).getOccuperProf().getProfNom()+" "+test.get(i).getOccuperProf().getProfPrenom();
            else data[i][2] = test.get(i).getOccuperProf();
            data[i][3] = test.get(i).getOccuperDate();
            i++;
        }
        return data;
    }
    public static Occuper occuperSalle(Object[] salle){
        ServiceOccuper service = new ServiceOccuper();
       return service.occuperSalle(salle);
    }
    public static void libererSalle(Occuper salle){
        ServiceOccuper service = new ServiceOccuper();
        service.libererSalle(salle);
    }
}