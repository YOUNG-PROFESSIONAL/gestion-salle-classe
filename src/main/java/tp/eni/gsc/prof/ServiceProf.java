package main.java.tp.eni.gsc.prof;

import main.java.tp.eni.gsc.prof.bean.Prof;

import java.util.*;

public class ServiceProf implements IServiceProf{
    private  DaoProf daoProf = new DaoProf();
    @Override
    public List<Prof> getPersonnes(String key) {
        return daoProf.getALLProf(key);
    }

    @Override
    public Prof add(Prof personne) {
        Prof prof = new Prof();
        personne.setProfId(UUID.randomUUID().toString());
        personne.setProfMatricule("PROF" + daoProf.getALLProf("").size());
        return daoProf.saveProf(personne);
    }

    @Override
    public Prof getPersonne() {
        return null;
    }

    @Override
    public Prof update(Prof personne) {
        return daoProf.updateProf(personne);
    }

    @Override
    public boolean delete(Prof personne) {
        daoProf.deleteProf(personne);
        return true;
    }
}
