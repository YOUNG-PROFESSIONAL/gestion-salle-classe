package main.java.tp.eni.gsc.salle;

import main.java.tp.eni.gsc.occuper.DaoOccuper;
import main.java.tp.eni.gsc.occuper.ServiceOccuper;
import main.java.tp.eni.gsc.occuper.bean.Occuper;
import main.java.tp.eni.gsc.prof.bean.Prof;
import main.java.tp.eni.gsc.salle.bean.Salle;

import java.util.List;
import java.util.UUID;

public class ServiceSalle implements IServiceSalle {
    private DaoSalle daoSalle = new DaoSalle();
    private ServiceOccuper serviceOccuper = new ServiceOccuper();

    @Override
    public List<Salle> getSalles(String key) {
        return daoSalle.getAllSalle();
    }
    @Override
    public Salle addSalle(Salle salle) {
        Salle salle1 = new Salle();
        salle.setSalleId(UUID.randomUUID().toString());
       try {
           salle1 = daoSalle.saveSalle(salle);
       } catch (Exception e){
            System.out.println(e.getCause());
           System.out.println(e.getMessage());
       }
       System.out.println(salle.getSalleCode());
       System.out.println(salle1.getSalleId());
        return salle1;
    }

    @Override
    public Salle getSalle(Salle salle) {

        return daoSalle.getSalle(salle);
    }

    @Override
    public Salle updateSalle(Salle salle) {

        return daoSalle.updateSalle(salle);
    }

    @Override
    public Salle deleteSalle(Salle salle) {
        return daoSalle.deleteSalle(salle);
    }
}
