package main.resources.ui.component;

import javax.swing.*;
import java.awt.*;

public class Search extends JPanel {
    private JButton search;
    private JTextField field;
    private ImageIcon icon;

    public Search(){initUI();}

    private void initUI(){
        icon = new ImageIcon("src/main/resources/ui/icon/search.png");
        this.field = new JTextField("search");
        this.search = new JButton(icon);
        add(search);
        add(field);
        field.setPreferredSize(new Dimension(500,30));
        createLayout(field,search);
    }
    public void createLayout(JComponent... args){

    }
}
