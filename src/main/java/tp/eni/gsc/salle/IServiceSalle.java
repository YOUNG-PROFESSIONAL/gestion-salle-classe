package main.java.tp.eni.gsc.salle;

import main.java.tp.eni.gsc.salle.bean.Salle;

import java.util.List;

public interface IServiceSalle {
   public List<Salle> getSalles(String key);
    public Salle addSalle(Salle salle) ;
    public Salle getSalle(Salle salle);
    public Salle updateSalle(Salle salle) ;
    public Salle deleteSalle(Salle salle) ;
}
