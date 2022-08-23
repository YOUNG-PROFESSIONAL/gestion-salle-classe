package main.resources.ui.event;

import main.resources.ui.component.Menu;
import main.resources.ui.component.header.Title;
import main.resources.ui.component.professor.Professor;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MenuEvent implements ChangeListener {
    Menu count;
    public MenuEvent(Menu index) {
        count = index;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        
        // get the currently selected index for this tabbedpane
        int selectedIndex = count.getSelectedIndex();
        if(selectedIndex == 1){
            Title.setMainTitle("Professeurs");
        }
        else if (selectedIndex == 2){
            Title.setMainTitle("Salles");
        }
        else {
            Title.setMainTitle("Tableau de bord");
        }

    }
}
