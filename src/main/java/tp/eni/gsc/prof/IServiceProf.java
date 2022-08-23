package main.java.tp.eni.gsc.prof;

import main.java.tp.eni.gsc.prof.bean.Prof;

import java.util.ArrayList;
import java.util.List;

public interface IServiceProf {
    public List<Prof> getPersonnes(String key);
    public Prof add(Prof personne) ;
    public Prof getPersonne();
    public Prof update(Prof personne) ;
    public boolean delete(Prof personne) ;

}
