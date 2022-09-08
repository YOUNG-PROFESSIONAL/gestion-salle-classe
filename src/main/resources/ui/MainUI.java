package main.resources.ui;

import main.resources.ui.component.Footer;
import main.resources.ui.component.header.Header;
import main.resources.ui.component.Menu;
import main.resources.ui.component.professor.Professor;
import main.resources.ui.service.ProfServiceUI;

import javax.swing.*;
import java.awt.*;

public class MainUI extends JFrame {
    GroupLayout group;
    private JPanel header;
    private JTabbedPane  tab;
    private JPanel footer;

    public MainUI(){
        initUI();
    }
        private void initUI() {
            header = new Header();
            tab = new Menu();
            //tab.add(new Menu());
            footer = new Footer();

            createLayout(header,tab,footer);

            setDefaultCloseOperation(EXIT_ON_CLOSE);
            getContentPane().setBackground(Color.white);
            setPreferredSize(new Dimension(1920,1080));
            setMaximumSize(new Dimension(1920,1080));
        }
        private void createLayout(JComponent... args) {
            group = new GroupLayout(getContentPane());
            getContentPane().setLayout(group);
            group.setAutoCreateGaps(true);
            group.setAutoCreateContainerGaps(true);

            GroupLayout.SequentialGroup hGroup = group.createSequentialGroup();
            GroupLayout.ParallelGroup vGroup= group.createParallelGroup();

            hGroup.addGroup(
                    group.createParallelGroup()
                            .addComponent(args[0])
                            .addComponent(args[1])
                            .addComponent(args[2]));

            vGroup.addGroup(group.createSequentialGroup()
                    .addComponent(args[0])
                    .addComponent(args[1])
                    .addComponent(args[2]));

            group.setHorizontalGroup(hGroup);
            group.setVerticalGroup(vGroup);
            pack();
        }
}
