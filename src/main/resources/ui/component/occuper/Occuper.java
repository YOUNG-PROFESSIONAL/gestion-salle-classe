package main.resources.ui.component.occuper;

import main.java.tp.eni.gsc.prof.GRADE;
import main.java.tp.eni.gsc.prof.bean.Prof;
import main.java.tp.eni.gsc.salle.bean.Salle;
import main.resources.ui.service.OccuperServiceUI;
import main.resources.ui.service.ProfServiceUI;
import main.resources.ui.service.SalleServiceUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class Occuper extends JPanel {
    Salle salle;
    Prof prof;
    JTable salleTable;
    DefaultTableModel model;
    JPanel tablePanel;
    GroupLayout tableGroup;

    JScrollPane pane;
    GroupLayout group;

    JPanel formPanel ;
    GroupLayout formGroup;

    /**************Professor Form******************/
    JLabel salleLabel,profLabel,dateLabel;
    JTextField fNum,fDate;
    JComboBox<String> fProf,fSalle;
    JButton busyBtn,freeBtn;

    public Occuper(){initUI();}
    private void initUI(){
        /********* Init Table *****/
        initTable();

        /******* Init form *********/
        initProfessorForm();

        /*************Create main layout****************/
        createGroup();
        /***************** Btn event *****/
        //BUSY
        busyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OccuperServiceUI.occuperSalle(new Object[]{fProf.getSelectedItem(), fSalle.getSelectedItem(), fDate.getText()});
                emptyField();
            }
        });
        //FREE
        freeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        salleTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTable table = (JTable) e.getSource();
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                fNum.setText(model.getValueAt(table.getSelectedRow(),0).toString());
/*
                if(model.getValueAt(table.getSelectedRow(),3) == null){
                    fGrade.setSelectedIndex(0);
                }
                else if(model.getValueAt(table.getSelectedRow(),3).toString().contains("LICENCE"))
                    fGrade.setSelectedIndex(0);
                else if(model.getValueAt(table.getSelectedRow(),3).toString().contains("MASTER"))
                    fGrade.setSelectedIndex(1);
                else if(model.getValueAt(table.getSelectedRow(),3).toString().contains("DOCTORAT"))
                    fGrade.setSelectedIndex(2);
                else if(model.getValueAt(table.getSelectedRow(),3).toString().contains("HDR"))
                    fGrade.setSelectedIndex(3);
                else if(model.getValueAt(table.getSelectedRow(),3).toString().contains("PROF"))
                    fGrade.setSelectedIndex(4);*/
                disabledSelectRow();
            }
        });
        // POPULATE DATA BASE ON START
        for (Object[] busy : OccuperServiceUI.getAllSalle(null)) model.addRow(busy);
        disabledOnStart();

    }
    private void emptyField(){
        fNum.setText("");
    }

    private void initTable(){
        salleTable = new JTable();
        model = new DefaultTableModel();
        pane = new JScrollPane();
        tablePanel = new JPanel();
        model.setColumnIdentifiers(new Object[]{"","Désignation","Professeur","Date","Occuper"});
        salleTable.setModel(model);
        pane.setViewportView(salleTable);
        salleTable.setFont(new Font("serif",700,12));
        /************** GRoup ************/
        tableGroup = new GroupLayout(tablePanel);
        tablePanel.setLayout(tableGroup);
        tableGroup.setAutoCreateGaps(true);
        tableGroup.setAutoCreateContainerGaps(true);
        GroupLayout.SequentialGroup hTableGroup = tableGroup.createSequentialGroup();
        GroupLayout.ParallelGroup vTableGroup = tableGroup.createParallelGroup();
        hTableGroup.addComponent(pane);
        vTableGroup.addComponent(pane);
        tableGroup.setHorizontalGroup(hTableGroup);
        tableGroup.setVerticalGroup(vTableGroup);
    }
    private void disabledOnStart(){
        fProf.setEnabled(true);
        fSalle.setEnabled(true);
        fDate.setEnabled(true);
        busyBtn.setEnabled(true);
        freeBtn.setEnabled(false);
    }


    private void disabledSelectRow(){
        busyBtn.setEnabled(true);
        freeBtn.setEnabled(true);
    }
    private void initProfessorForm(){
        profLabel = new JLabel("Professeur");
        salleLabel = new JLabel("Salle");
        dateLabel = new JLabel("Date");
        fNum = new JTextField();

        fProf = new JComboBox<String>();
        JComboBox<String> fProf1 = new JComboBox<>();
        for(Object[] p : ProfServiceUI.getAllProfessors(null)){
            fProf.addItem( p[0] + "- " + p[1] + " " +p[2]);
        }


        fSalle = new JComboBox<String>();
        for(Object[] salle : SalleServiceUI.getAllSalle(null)){
            fSalle.addItem(salle[1] +"- "+salle[2]);
        }


        fDate = new JTextField();
        fDate.setToolTipText("MM/DD/YYYY");
        busyBtn = new JButton("OCCUPER");
        freeBtn = new JButton("LIBERER");

        formPanel = new JPanel();
        formGroup = new GroupLayout(formPanel);
        formPanel.setLayout(formGroup);

        formGroup.setAutoCreateGaps(true);
        formGroup.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup hFormGroup = formGroup.createSequentialGroup();
        GroupLayout.ParallelGroup vFormGroup = formGroup.createBaselineGroup(true,false);

        hFormGroup.addGroup(formGroup.createSequentialGroup()
                        .addGroup(formGroup.createParallelGroup()
                                .addComponent(profLabel).addComponent(salleLabel).addComponent(dateLabel))
                        .addGroup(formGroup.createParallelGroup()
                                .addComponent(fProf).addComponent(fSalle).addComponent(fDate)))
                 .addGroup(formGroup.createParallelGroup()
                         .addComponent(busyBtn).addComponent(freeBtn));

        vFormGroup.addGroup(formGroup.createSequentialGroup()
                        .addGroup(formGroup.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(profLabel).addComponent(fProf))
                        .addGroup(formGroup.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(salleLabel).addComponent(fSalle))
                        .addGroup(formGroup.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(dateLabel).addComponent(fDate)))
                .addGroup(formGroup.createSequentialGroup()
                        .addComponent(busyBtn).addComponent(freeBtn));

        formGroup.setHorizontalGroup(hFormGroup);
        formGroup.setVerticalGroup(vFormGroup);
        formGroup.linkSize(SwingConstants.HORIZONTAL,busyBtn,freeBtn);
        formGroup.linkSize(SwingConstants.VERTICAL,fProf,fSalle,fDate);

    }
    public void createGroup(){
        group = new GroupLayout(this);
        setLayout(group);

        group.setAutoCreateGaps(true);
        group.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup hGroup = group.createSequentialGroup();
        GroupLayout.ParallelGroup vGroup = group.createParallelGroup();

        hGroup.addGroup(group.createParallelGroup()
                .addComponent(tablePanel).addComponent(formPanel));
        vGroup.addGroup(group.createSequentialGroup()
                .addComponent(tablePanel).addComponent(formPanel));

        group.setHorizontalGroup(hGroup);
        group.setVerticalGroup(vGroup);

    }

}
