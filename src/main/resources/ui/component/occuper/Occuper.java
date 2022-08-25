package main.resources.ui.component.occuper;

import com.toedter.calendar.JCalendar;
import main.resources.ui.service.OccuperServiceUI;
import main.resources.ui.service.ProfServiceUI;
import main.resources.ui.service.SalleServiceUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Occuper extends JPanel {
    JTable salleTable;
    DefaultTableModel model;
    JPanel tablePanel;
    GroupLayout tableGroup;

    JScrollPane pane;
    GroupLayout group;

    JPanel formPanel ;
    GroupLayout formGroup;

    /******Date ********/
    JCalendar calendar = new JCalendar();
    /**************Professor Form******************/
    JLabel salleLabel,profLabel,dateLabel;
    JTextField fNum,fDate;
    JComboBox<String> fProf,fSalle;
    JButton busyBtn,freeBtn;
    public Occuper(){
        initUI();
    }
    private void initUI(){
        Date date = new Date();
        /********* Init Table *****/
        initTable();

        calendar.getDayChooser().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                String pattern = "EEEEE dd MMMMM yyyy ";
                SimpleDateFormat simpleDateFormat =new SimpleDateFormat(pattern, new Locale("fr", "FR"));
                String date = simpleDateFormat.format(calendar.getDate());
                fDate.setText(date);
            }
        });
        /******* Init form *********/
        initProfessorForm();

        /*************Create main layout****************/
        createGroup();
        /***************** Btn event *****/
        //BUSY
        busyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OccuperServiceUI.occuperSalle(
                        new Object[]{fProf.getSelectedItem(), fSalle.getSelectedItem(), fDate.getText()});
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
                disabledSelectRow();
            }
        });

        /**** POPULATE DATA BASE ON START*******/
        for (Object[] busy : OccuperServiceUI.getAllSalle(null)){
            model.addRow(busy);
        }
        disabledOnStart();
        salleTable.removeColumn(salleTable.getColumnModel().getColumn(0));

    }
    private void emptyField(){
        fNum.setText("");
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
        busyBtn.setEnabled(true);
        freeBtn.setEnabled(false);
    }


    private void disabledSelectRow(){
        busyBtn.setEnabled(true);
        freeBtn.setEnabled(true);
    }
    public void initProfessorForm(){

        profLabel = new JLabel("Professeur");
        salleLabel = new JLabel("Salle");
        dateLabel = new JLabel("Date");
        fNum = new JTextField();
        fDate = new JTextField();


        // listen for prof and salle change
        addComponentListener(new ComponentAdapter() {

            @Override
            public void componentShown(ComponentEvent e) {
                fProf.removeAllItems();
                fSalle.removeAllItems();
                for(Object[] p : ProfServiceUI.getAllProfessors(null)){
                    fProf.addItem( p[0] + "- " + p[1] + " " +p[2]);
                }
                for(Object[] salle : SalleServiceUI.getAllSalle(null)){
                    fSalle.addItem(salle[1] +"- "+salle[2]);
                }
            }
        });
        fProf = new JComboBox<String>();
        fSalle = new JComboBox<String>();

        fDate = new JTextField();
        fDate.setDisabledTextColor(new Color(0x000000));
        fDate.setBackground(new Color(0xFFFFFF));
        fDate.setEnabled(false);


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
                .addComponent(tablePanel).addComponent(formPanel).addComponent(calendar));
        vGroup.addGroup(group.createSequentialGroup()
                .addComponent(tablePanel).addComponent(formPanel).addComponent(calendar));

        group.setHorizontalGroup(hGroup);
        group.setVerticalGroup(vGroup);

    }

}
