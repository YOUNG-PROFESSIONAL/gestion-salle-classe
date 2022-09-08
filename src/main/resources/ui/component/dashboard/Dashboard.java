package main.resources.ui.component.dashboard;


import main.resources.ui.model.DashBoard;
import main.resources.ui.service.OccuperServiceUI;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class Dashboard extends JPanel {
    Date date;
    DashBoard content;
    JPanel component;
    JLabel dt;
    SpringLayout sprLayout = new SpringLayout();

    public Dashboard(){initUI();}
    private void initUI() {
        date = new Date();
        dt = new JLabel(date.toString());

        setBackground(new Color(0x9090FD));
        setLayout(new GridBagLayout());
        add(dt);
        createLayout();
        addSalle();
    }
   void  createLayout(){
    }
    private  void addSalle(){
        int x = 0; int y = 0;
        JLabel label = new JLabel("TEXT");
        component = new JPanel();
        for (Object[] salle : OccuperServiceUI.getAllOccuperSalle("")) {
            component.setBackground(new Color(0xB2EBFF));
            component.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
            component.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            component.setPreferredSize(new Dimension(200,200));
            label.setText(salle.toString());
            component.add(label);
            component.setLayout(new GridBagLayout());
            add(component);
        }
    }
}
