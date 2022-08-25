package main.resources.ui.component.salle;

import main.java.tp.eni.gsc.salle.bean.Salle;

import main.resources.ui.service.SalleServiceUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Salles extends JPanel {
    Salle salle;
    JTable salleTable;
    DefaultTableModel model;
    JPanel tablePanel;
    GroupLayout tableGroup;

    JScrollPane pane;
    GroupLayout group;

    JPanel formPanel ;
    GroupLayout formGroup;

    /**************Professor Form******************/
    JLabel code,designation;
    JTextField fNum,fCode,fDesignation;
    JButton addBtn,editBtn,saveBtn,deleteBtn;

    public Salles(){initUI();}
    private void initUI(){

        /********* Init Table *****/
        initTable();

        /******* Init form *********/
        initProfessorForm();

        /*************Create main layout****************/
        createGroup();
        /***************** Btn event *****/
        //AJOUTER
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                disabledAddBtn();
                emptyField();
            }
        });
        //EDITER
        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                disabledEditBtn();
            }
        });
        //SAUVEGARDER
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salle = new Salle(fNum.getText(),fCode.getText(),fDesignation.getText());
                if(salle.getSalleId().isEmpty() |salle.getSalleId() == null ) {
                    salle = SalleServiceUI.saveSalle(salle);
                    if(salle.getSalleId() != null)
                        model.insertRow(0,new Object[]{salle.getSalleId(),salle.getSalleCode(),salle.getSalleDesignation()});
                    else { String msg;
                        if(fCode.getText().isEmpty()) msg = "Veuillez remplir le code salle";
                        else msg = "Le code salle doit-être unique!";
                        if(fDesignation.getText().isEmpty()) msg = "Veuillez remplir le désignation";
                        else msg = "La désignation doit-être unique!";
                        JOptionPane.showMessageDialog(null,msg);
                    }
                }
                else {
                    salle = SalleServiceUI.updateSalle(salle);
                    int count = model.getRowCount();
                    for(int i=0; i<count; i++){
                        if(model.getValueAt(i,0).toString().contains(salle.getSalleId())){
                            model.removeRow(i);
                            model.insertRow(i,
                                    new Object[]{salle.getSalleId(),salle.getSalleCode(),salle.getSalleDesignation()});
                            break;
                        };
                    }
                }
                disabledSaveBtn();
                emptyField();
            }
        });
        //SUPPRIMER
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = salleTable.getSelectedRow();
                salle = new Salle();
                salle.setSalleId(fNum.getText());
                SalleServiceUI.deleteSalle(salle);
                model.removeRow(i);

                disabledDeleteBtn();
                emptyField();
            }
        });
        salleTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTable table = (JTable) e.getSource();
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                fCode.setText(model.getValueAt(table.getSelectedRow(),1).toString());
                fDesignation.setText(model.getValueAt(table.getSelectedRow(),2).toString());
                fNum.setText(salleTable.getModel().getValueAt(table.getSelectedRow(),0).toString());
                disabledSelectRow();
            }
        });

        for (Object[] salle : SalleServiceUI.getAllSalle(null)){
            model.addRow(salle);
        }

        /*****remove the second column*//////////////
        salleTable.removeColumn(salleTable.getColumnModel().getColumn(0));
        disabledOnStart();

    }
    private void emptyField(){
        fNum.setText("");
        fCode.setText("");
        fDesignation.setText("");
    }

    private void initTable(){
        salleTable = new JTable();
        model = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column)
            {
                return false;//This causes all cells to be not editable
            }
        };
        pane = new JScrollPane();
        tablePanel = new JPanel();
        model.setColumnIdentifiers(new Object[]{"ID","Code Salle","Désignation"});
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
        fCode.setEnabled(false);
        fDesignation.setEnabled(false);
        editBtn.setEnabled(false);
        saveBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
    }
    private void disabledAddBtn(){
        addBtn.setEnabled(false);
        fCode.setEnabled(true);
        fDesignation.setEnabled(true);
        editBtn.setEnabled(false);
        saveBtn.setEnabled(true);
        deleteBtn.setEnabled(false);
    }
    private void disabledEditBtn(){
        addBtn.setEnabled(true);
        fCode.setEnabled(true);
        fDesignation.setEnabled(true);
        editBtn.setEnabled(false);
        saveBtn.setEnabled(true);
        deleteBtn.setEnabled(false);
    }
    private void disabledSaveBtn(){
        addBtn.setEnabled(true);
        fCode.setEnabled(false);
        fDesignation.setEnabled(false);
        editBtn.setEnabled(false);
        saveBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
    }
    private void disabledDeleteBtn(){
        addBtn.setEnabled(true);
        fCode.setEnabled(false);
        fDesignation.setEnabled(false);
        editBtn.setEnabled(false);
        saveBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
    }
    private void disabledSelectRow(){
        addBtn.setEnabled(true);
        fCode.setEnabled(false);
        fDesignation.setEnabled(false);
        editBtn.setEnabled(true);
        saveBtn.setEnabled(false);
        deleteBtn.setEnabled(true);
    }
    private void initProfessorForm(){
        code = new JLabel("Code Salle");
        designation = new JLabel("Désignation");
        fNum = new JTextField();
        fCode = new JTextField();
        fDesignation = new JTextField();

        addBtn = new JButton("Ajouter");
        editBtn = new JButton("Modifier");
        saveBtn = new JButton("Enregistrer");
        deleteBtn = new JButton("Supprimer");

        formPanel = new JPanel();
        formGroup = new GroupLayout(formPanel);
        formPanel.setLayout(formGroup);

        formGroup.setAutoCreateGaps(true);
        formGroup.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup hFormGroup = formGroup.createSequentialGroup();
        GroupLayout.ParallelGroup vFormGroup = formGroup.createBaselineGroup(true,false);

        hFormGroup.addGroup(formGroup.createParallelGroup()
                        .addGroup(formGroup.createSequentialGroup()
                                .addComponent(code)
                                .addComponent(fCode)
                                .addComponent(designation)
                                .addComponent(fDesignation))
                 .addGroup(formGroup.createSequentialGroup()
                         .addComponent(addBtn).addComponent(editBtn).addComponent(saveBtn).addComponent(deleteBtn)));

        vFormGroup.addGroup(formGroup.createSequentialGroup()
                        .addGroup(formGroup.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(code).addComponent(fCode).addComponent(designation).addComponent(fDesignation))
                        .addGroup(formGroup.createParallelGroup()
                                .addComponent(addBtn).addComponent(editBtn).addComponent(saveBtn).addComponent(deleteBtn)));

        formGroup.setHorizontalGroup(hFormGroup);
        formGroup.setVerticalGroup(vFormGroup);
        formGroup.linkSize(SwingConstants.HORIZONTAL,addBtn,editBtn,saveBtn,deleteBtn);
//        formGroup.linkSize(SwingConstants.HORIZONTAL,fCode,fDesignation);

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
