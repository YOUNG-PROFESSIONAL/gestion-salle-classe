package main.resources.ui.service;

import main.java.tp.eni.gsc.prof.GRADE;
import main.java.tp.eni.gsc.prof.ServiceProf;
import main.java.tp.eni.gsc.prof.bean.Prof;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProfServiceUI {
    public static Object[][] getAllProfessors(String key){
        ServiceProf service = new ServiceProf();
        List<Prof> profList =  service.getPersonnes(key);
        int listSize = profList.size();
        ArrayList<Prof> test = new ArrayList<>();
        profList.forEach(prof -> {
            test.add(prof);
        });
        int i=0;
        Object[][] data = new Object[test.size()][4];
        while(i<test.size()){
            data[i][0] = test.get(i).getProfMatricule();
            data[i][1] = test.get(i).getProfNom();
            data[i][2] = test.get(i).getProfPrenom();
            data[i][3] = test.get(i).getProfGrade();
            i++;
        }
        return data;
    }
    public static void saveProf(Prof prof){
        ServiceProf service = new ServiceProf();
        service.add(prof);
    }
    public static void updateProf(Prof prof){
        ServiceProf service = new ServiceProf();
        service.update(prof);
    }
    public static void deleteProf(Prof prof){
        ServiceProf service = new ServiceProf();
        service.delete(prof);
    }
}