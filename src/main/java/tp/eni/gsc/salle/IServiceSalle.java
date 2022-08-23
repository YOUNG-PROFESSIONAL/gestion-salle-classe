package main.java.tp.eni.gsc.salle;

import main.java.tp.eni.gsc.salle.bean.Salle;

import java.util.List;

public interface IServiceSalle {
   public List<Salle> getSalles();
    public long addSalle(Salle salle) ;
    public Salle getSalle();
    public int update(Salle salle) ;
    public int delete(Salle salle) ;
}
