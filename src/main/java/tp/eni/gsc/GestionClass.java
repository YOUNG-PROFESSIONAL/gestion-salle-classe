package main.java.tp.eni.gsc;

import main.java.tp.eni.gsc.config.HibernateUtil;
import main.resources.ui.MainUI;

import javax.swing.*;

public class GestionClass {
    public static void  main(String args[]){
        HibernateUtil.getSessionFactory();
        //Running Windows
            SwingUtilities.invokeLater(() -> {
                new MainUI().setVisible(true);
            });
    }
}
