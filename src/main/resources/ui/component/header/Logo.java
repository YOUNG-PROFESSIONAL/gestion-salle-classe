package main.resources.ui.component.header;

import javax.swing.*;
import java.awt.*;
import java.time.Year;


public class Logo extends JPanel {
    JLabel gsc;
    JLabel anUniv;
    JLabel year;

    public  Logo(){
        initUI();
    }
    private void initUI(){
        gsc = new JLabel("GSC");
        anUniv = new JLabel("Année universitaire ");
        year = new JLabel(Year.now().toString());

        gsc.setFont(new Font("Iceberg",Font.PLAIN,50));
        anUniv.setFont(new Font("Iceberg",Font.PLAIN,20));
        year.setFont(new Font("Iceberg",Font.PLAIN,20));

        setBackground(Color.white);
        createLayout(gsc,anUniv,year);
    }
    private void createLayout(JComponent... args){
        GroupLayout group = new GroupLayout(this);
        setLayout(group);
        GroupLayout.SequentialGroup horizGroup = group.createSequentialGroup();
        GroupLayout.ParallelGroup vertGroup = group.createParallelGroup();

        horizGroup.addGroup(group.createParallelGroup()
                .addComponent(gsc)
                .addGroup(group.createSequentialGroup()
                        .addComponent(anUniv)
                        .addComponent(year)));

        vertGroup.addGroup(group.createSequentialGroup()
                .addComponent(gsc)
                .addGroup(group.createParallelGroup()
                        .addComponent(anUniv)
                        .addComponent(year)));

        group.setHorizontalGroup(horizGroup);
        group.setVerticalGroup(vertGroup);

    }
}
