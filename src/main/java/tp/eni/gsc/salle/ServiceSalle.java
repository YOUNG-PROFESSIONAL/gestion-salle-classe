package main.java.tp.eni.gsc.salle;

import main.java.tp.eni.gsc.salle.bean.Salle;

import java.util.ArrayList;
import java.util.List;

public class ServiceSalle implements IServiceSalle {
    private DaoSalle daoSalle = new DaoSalle();

    @Override
    public List<Salle> getSalles() {
        return daoSalle.getAllSalle();
    }
    @Override
    public long addSalle(Salle salle) {
        return 0;
    }

    @Override
    public Salle getSalle() {
        return null;
    }

    @Override
    public int update(Salle salle) {
        return 0;
    }

    @Override
    public int delete(Salle salle) {
        return 0;
    }
}
