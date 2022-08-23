package main.resources.ui.component;

import javax.swing.*;
import java.awt.*;

public class Footer extends JPanel {
    JLabel left;
    JLabel right;
    public Footer(){
        initUI();
    }

    private void initUI(){
        left = new JLabel("ECOLE NATIONALE D’INFORMATIQUE");
        right = new JLabel("UNIVEVRSITE DE FIANARANTSOA");

        left.setFont(new Font("iceberg",Font.PLAIN,20));
        right.setFont(new Font("iceberg",Font.PLAIN,20));

        createLayout(left,right);
    }
    private void createLayout(JComponent... args){
        GroupLayout group = new GroupLayout(this);
        setLayout(group);

        GroupLayout.SequentialGroup hGroup = group.createSequentialGroup();
        GroupLayout.ParallelGroup vGroup = group.createParallelGroup();

        hGroup.addComponent(args[0]).addGap(750).addComponent(args[1]);
        vGroup.addComponent(args[0]).addComponent(args[1]);

        group.setHorizontalGroup(hGroup);
        group.setVerticalGroup(vGroup);
    }
}
