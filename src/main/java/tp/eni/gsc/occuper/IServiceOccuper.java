package main.java.tp.eni.gsc.occuper;

import main.java.tp.eni.gsc.occuper.bean.Occuper;

import java.util.List;

public interface IServiceOccuper {
   public List<Occuper> getSalles(String key);
    public Occuper occuperSalle(Object[] salle) ;
    public Occuper libererSalle(String salle) ;
}
