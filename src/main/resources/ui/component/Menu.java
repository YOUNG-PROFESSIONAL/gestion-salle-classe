package main.resources.ui.component;

import main.resources.ui.component.professor.Professor;
import main.resources.ui.event.MenuEvent;

import javax.swing.*;
import java.awt.*;

public class Menu extends JTabbedPane {
    public Menu(){
        initUI();
    }
    private  void initUI(){
        setBounds(80,80,300,300);
        JPanel p1=new JPanel();
        JPanel p2=new Professor();
        JPanel p3=new JPanel();
        add("Tableau de bord",p1);
        add("Professeurs",p2);
        add("Salles",p3);
        setPreferredSize(new Dimension(1200,500));
        this.addChangeListener(new MenuEvent(this));
    }
}
