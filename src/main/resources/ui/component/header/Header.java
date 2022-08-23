package main.resources.ui.component.header;

import main.resources.ui.component.header.Deconnexion;
import main.resources.ui.component.header.Logo;

import javax.swing.*;
import java.awt.*;

public class Header extends JPanel {
    Logo logo;
    Title title;
    Deconnexion deconnexion;

    public Header(){
        initUI();
    }
    private  void  initUI(){
        logo = new Logo();
        title = new Title("Tableau de bord");
        deconnexion = new Deconnexion();
        addComponentsToPane(this);
        setPreferredSize(new Dimension(800,80));
        setBackground(Color.WHITE);
    }
    public void addComponentsToPane(Container pane){
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.gridx = 0;
        c.gridy = 0;
        add(logo,c);
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1;
        c.gridx = 1;
        c.gridy = 0;
        add(title,c);
        c.weightx = 0;
        c.anchor = GridBagConstraints.EAST;
        c.gridx = 2;
        c.gridy = 0;
        add(deconnexion,c);
    }
    private void createLayout(JComponent... args){
        GroupLayout group = new GroupLayout(this);
        setLayout(group);
        group.setAutoCreateGaps(true);
        group.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup hGroup = group.createSequentialGroup();
        GroupLayout.ParallelGroup vGroup = group.createParallelGroup(GroupLayout.Alignment.TRAILING);

        hGroup.addContainerGap(20,40).addComponent(logo).addComponent(title).addContainerGap(320,400).addComponent(deconnexion);
        vGroup.addGap(20).addComponent(logo).addComponent(title).addComponent(deconnexion);

        group.setHorizontalGroup(hGroup);
        group.setVerticalGroup(vGroup);
    }
}
