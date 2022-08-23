package main.resources.ui.component.header;

import javax.swing.*;
import java.awt.*;

public class Deconnexion extends JPanel {
    private JLabel label;
    private JButton btnDec;
    private ImageIcon iconUI;

    public Deconnexion(){
        initUI();
    }
    private void initUI(){
        iconUI = new ImageIcon("src/main/resources/ui/icon/icon.png");
        btnDec = new JButton("Deconnexion");
        label = new JLabel(iconUI);
        setBackground(Color.white);
        createLayout(btnDec,label);
    }
    private  void createLayout(JComponent... args){

        GroupLayout group = new GroupLayout(this);
        setLayout(group);
        GroupLayout.SequentialGroup hGroup = group.createSequentialGroup();
        GroupLayout.ParallelGroup vGroup = group.createParallelGroup();

        hGroup.addGroup(group.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(args[0]).addComponent(args[1]));
        vGroup.addGroup(group.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addComponent(args[0]).addComponent(args[1]));

        group.setHorizontalGroup(hGroup);
        group.setVerticalGroup(vGroup);
    }
}
