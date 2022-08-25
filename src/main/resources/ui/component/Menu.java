package main.resources.ui.component;

import main.resources.ui.component.occuper.Occuper;
import main.resources.ui.component.professor.Professor;
import main.resources.ui.component.salle.Salles;
import main.resources.ui.event.MenuEvent;

import javax.swing.*;
import java.awt.*;

public class Menu extends JTabbedPane {
    public JPanel p1=new JPanel();
    public JPanel p2=new Professor();
    public JPanel p3=new Salles();
    public JPanel p4= new Occuper();
    public Menu(){
        initUI();
    }
    private  void initUI(){
        setBounds(80,80,300,300);
        add("Tableau de bord",p1);
        add("Professeurs",p2);
        add("Salles",p3);
        add("Gérer les salles",p4);
        setPreferredSize(new Dimension(1200,500));
        this.addChangeListener(new MenuEvent(this));
    }
}
