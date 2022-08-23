package main.java.tp.eni.gsc.salle;

import main.java.tp.eni.gsc.config.HibernateUtil;
import main.java.tp.eni.gsc.prof.bean.Prof;
import main.java.tp.eni.gsc.salle.bean.Salle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class DaoSalle {
    SessionFactory sessionFactory;

    public DaoSalle(){
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public Prof saveSalle(Salle salle){
        Session session = this.sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        session.save(salle);
        t.commit();
        session.close();
         return null;
    }
    public List<Salle> getAllSalle(){
        Session session = this.sessionFactory.openSession();
        return session.createQuery("Select a from Salle a").getResultList();
    }

}
