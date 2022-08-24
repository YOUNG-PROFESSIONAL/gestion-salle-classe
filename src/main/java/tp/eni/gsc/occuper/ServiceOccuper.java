package main.java.tp.eni.gsc.occuper;

import main.java.tp.eni.gsc.occuper.bean.Occuper;

import java.util.List;
import java.util.UUID;

public class ServiceOccuper implements IServiceOccuper {
    private DaoOccuper daoOccuper = new DaoOccuper();

    @Override
    public List<Occuper> getSalles(String key) {
        return daoOccuper.getAllSalle();
    }
    @Override
    public Occuper occuperSalle(Object[] salle) {
        Occuper occuper = new Occuper();
        occuper.setOccuperfId((UUID.randomUUID().toString()));
        occuper.setOccuperProf(daoOccuper.getProfToOccuper(salle[0].toString()));
        occuper.setOccuperSalle(daoOccuper.getSalleToOccuper(salle[1].toString()));
        occuper.setOccuperDate(salle[2].toString());
        return daoOccuper.saveOccuper(occuper);
    }
    @Override
    public Occuper libererSalle(Occuper salle) {
        return daoOccuper.deleteSalle(salle);
    }
}
