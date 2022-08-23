package main.resources.ui.component.header;

import javax.swing.*;
import java.awt.*;
import java.time.Year;


public class Title extends JPanel {
    private static JLabel mainTitle;

    public Title(String title){
        mainTitle = new JLabel();
        initUI(title);
    }
    private void initUI(String title){
        mainTitle.setText(title);
        mainTitle.setFont(new Font("Iceberg",Font.PLAIN,30));
        setBackground(Color.white);
        createLayout(mainTitle);
    }
    public static void setMainTitle(String title){
        mainTitle.setText(title);
    }

    private void createLayout(JComponent... args){
        GroupLayout group = new GroupLayout(this);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                setLayout(group);
                GroupLayout.SequentialGroup horizGroup = group.createSequentialGroup();
                GroupLayout.ParallelGroup vertGroup = group.createParallelGroup();

                horizGroup
                        .addComponent(args[0]);

                vertGroup
                        .addComponent(args[0]);
                group.setHorizontalGroup(horizGroup);
                group.setVerticalGroup(vertGroup);
            }
        });
    }
}
