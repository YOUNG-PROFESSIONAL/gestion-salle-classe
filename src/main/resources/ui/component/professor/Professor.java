package main.resources.ui.component.professor;

import main.java.tp.eni.gsc.prof.GRADE;
import main.java.tp.eni.gsc.prof.bean.Prof;
import main.resources.ui.service.ProfServiceUI;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;

public class Professor extends JPanel {
    Prof prof;
    JTable profTable;
    DefaultTableModel model;
    JPanel tablePanel;
    GroupLayout tableGroup;

    JScrollPane pane;
    GroupLayout group;

    JPanel formPanel ;
    GroupLayout formGroup;

    /**************Professor Form******************/
    JLabel nom,prenom,grade;
    JTextField fNum,fNom,fPrenom;
    JComboBox<GRADE> fGrade;
    JButton addBtn,editBtn,saveBtn,deleteBtn;
    /************** Search *******************/
    JTextField fSearch;
    JButton searchBtn;

    public Professor(){initUI();}
    private void initUI(){
        /********* Init Table *****/
        initTable();
        initProfResearch();
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
                prof = new Prof();
                prof.setProfMatricule(fNum.getText());
                prof.setProfNom(fNom.getText());
                prof.setProfPrenom(fPrenom.getText());
                prof.setProfGrade((GRADE) fGrade.getSelectedItem());

                if(fNum == null | fNum.getText().isEmpty()){
                    ProfServiceUI.saveProf(prof);
                    model.addRow(new Object[]{prof.getProfMatricule(),prof.getProfNom(),prof.getProfPrenom(),prof.getProfGrade()});
                }
                else {
                    ProfServiceUI.updateProf(prof);
                    int count = model.getRowCount();
                    for(int i=0; i<count; i++){
                        if(model.getValueAt(i,0).toString().contains(prof.getProfMatricule())){
                            model.removeRow(i);
                            model.insertRow(i,
                                    new Object[]{prof.getProfMatricule(),prof.getProfNom(),prof.getProfPrenom(),prof.getProfGrade()});
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
                int i = profTable.getSelectedRow();
                prof = new Prof();
                prof.setProfMatricule(fNum.getText());
                ProfServiceUI.deleteProf(prof);
                model.removeRow(i);
                disabledDeleteBtn();
                emptyField();
            }
        });

        profTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTable table = (JTable) e.getSource();
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                fNum.setText(model.getValueAt(table.getSelectedRow(),0).toString());
                fNom.setText(model.getValueAt(table.getSelectedRow(),1).toString());
                fPrenom.setText(model.getValueAt(table.getSelectedRow(),2).toString());

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
                    fGrade.setSelectedIndex(4);
                disabledSelectRow();
            }
        });

        for (Object[] prof : ProfServiceUI.getAllProfessors(null)) model.addRow(prof);
        disabledOnStart();

    }
    private void emptyField(){
        fNum.setText("");
        fNom.setText("");
        fPrenom.setText("");
        fGrade.setSelectedIndex(0);
    }

    private void initTable(){
        profTable = new JTable();
        model = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column)
            {
                return false;//This causes all cells to be not editable
            }
        };
        pane = new JScrollPane();
        tablePanel = new JPanel();
        model.setColumnIdentifiers(new Object[]{"Numero","Nom","Prénom","Grade"});
        profTable.setModel(model);
        pane.setViewportView(profTable);
        profTable.setFont(new Font("serif",700,12));
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
        fNom.setEnabled(false);
        fPrenom.setEnabled(false);
        fGrade.setEnabled(false);
        editBtn.setEnabled(false);
        saveBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
    }
    private void disabledAddBtn(){
        addBtn.setEnabled(false);
        fNom.setEnabled(true);
        fPrenom.setEnabled(true);
        fGrade.setEnabled(true);
        editBtn.setEnabled(false);
        saveBtn.setEnabled(true);
        deleteBtn.setEnabled(false);
    }
    private void disabledEditBtn(){
        addBtn.setEnabled(true);
        fNom.setEnabled(true);
        fPrenom.setEnabled(true);
        fGrade.setEnabled(true);
        editBtn.setEnabled(false);
        saveBtn.setEnabled(true);
        deleteBtn.setEnabled(false);
    }
    private void disabledSaveBtn(){
        addBtn.setEnabled(true);
        fNom.setEnabled(false);
        fPrenom.setEnabled(false);
        fGrade.setEnabled(false);
        editBtn.setEnabled(false);
        saveBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
    }
    private void disabledDeleteBtn(){
        addBtn.setEnabled(true);
        fNom.setEnabled(false);
        fPrenom.setEnabled(false);
        fGrade.setEnabled(false);
        editBtn.setEnabled(false);
        saveBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
    }
    private void disabledSelectRow(){
        addBtn.setEnabled(true);
        fNom.setEnabled(false);
        fPrenom.setEnabled(false);
        fGrade.setEnabled(false);
        editBtn.setEnabled(true);
        saveBtn.setEnabled(false);
        deleteBtn.setEnabled(true);
    }
    private void initProfResearch(){
        fSearch = new JTextField("a");
        searchBtn = new JButton(new ImageIcon("src/main/resources/ui/icon/search.png"));
        DefaultTableModel model = (DefaultTableModel) profTable.getModel();
        DefaultTableModel model2 = (DefaultTableModel) profTable.getModel();
        int count = model.getRowCount();
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fSearch.getText().length() == 0) {
                    for (Object[] prof : ProfServiceUI.getAllProfessors("")) model.addRow(prof);
                } else {

                }
            }
        });
    }
    private void initProfessorForm(){
        nom = new JLabel("Nom");
        prenom = new JLabel("Prénom");
        grade = new JLabel("Grade");
        fNum = new JTextField();
        fNom = new JTextField();
        fPrenom = new JTextField();
        fNom.setPreferredSize(new Dimension(50,35));
        fGrade = new JComboBox();
        fGrade.addItem(GRADE.LICENCE);
        fGrade.addItem(GRADE.MASTER);
        fGrade.addItem(GRADE.DOCTORAT);
        fGrade.addItem(GRADE.HDR);
        fGrade.addItem(GRADE.PROF_TITULAIRE);
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

        hFormGroup.addGroup(formGroup.createSequentialGroup()
                        .addGroup(formGroup.createParallelGroup()
                                .addComponent(nom).addComponent(prenom).addComponent(grade))
                        .addGroup(formGroup.createParallelGroup()
                                .addComponent(fNom).addComponent(fPrenom).addComponent(fGrade)))
                 .addGroup(formGroup.createParallelGroup()
                         .addComponent(addBtn).addComponent(editBtn).addComponent(saveBtn).addComponent(deleteBtn));

        vFormGroup.addGroup(formGroup.createSequentialGroup()
                        .addGroup(formGroup.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(nom).addComponent(fNom))
                        .addGroup(formGroup.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(prenom).addComponent(fPrenom))
                        .addGroup(formGroup.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(grade).addComponent(fGrade)))
                .addGroup(formGroup.createSequentialGroup()
                        .addComponent(addBtn).addComponent(editBtn).addComponent(saveBtn).addComponent(deleteBtn));

        formGroup.setHorizontalGroup(hFormGroup);
        formGroup.setVerticalGroup(vFormGroup);
        formGroup.linkSize(SwingConstants.HORIZONTAL,addBtn,editBtn,saveBtn,deleteBtn);
        formGroup.linkSize(SwingConstants.VERTICAL,fNom,fPrenom,fGrade);

    }
    public void createGroup(){
        group = new GroupLayout(this);
        setLayout(group);

        group.setAutoCreateGaps(true);
        group.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup hGroup = group.createSequentialGroup();
        GroupLayout.ParallelGroup vGroup = group.createParallelGroup();

        hGroup.addGroup(group.createParallelGroup().addGroup(group.createSequentialGroup()
                        .addComponent(fSearch).addComponent(searchBtn))
                .addComponent(tablePanel).addComponent(formPanel));
        vGroup.addGroup(group.createSequentialGroup().addGroup(group.createParallelGroup()
                        .addComponent(fSearch).addComponent(searchBtn))
                .addComponent(tablePanel).addComponent(formPanel));

        group.setHorizontalGroup(hGroup);
        group.setVerticalGroup(vGroup);

    }

}
