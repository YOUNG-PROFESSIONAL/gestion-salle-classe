package main.java.tp.eni.gsc.occuper;

import main.java.tp.eni.gsc.config.HibernateUtil;
import main.java.tp.eni.gsc.occuper.bean.Occuper;
import main.java.tp.eni.gsc.prof.bean.Prof;
import main.java.tp.eni.gsc.salle.bean.Salle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class DaoOccuper {
    SessionFactory sessionFactory;

    public DaoOccuper(){
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public Occuper saveOccuper(Occuper salle){
        Session session = this.sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        session.save(salle);
        t.commit();
        session.close();

         return getOccuper(salle);
    }
    public Occuper getOccuper(Occuper salle){
        Session session = this.sessionFactory.openSession();
        String hql = "FROM Occuper as o WHERE o.occuperSalle.salleId = :salle";
        return (Occuper) session.createQuery(hql).setParameter("salle",salle.getOccuperSalle().getSalleId()).getSingleResult();
    }
    public Occuper updateSalle(Occuper salle){
        String hql = "UPDATE Salle Set salleCode = :code, salleDesignation = :designation " +
                "WHERE salleId = :id ";
        Session session = this.sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        Query query = session.createQuery(hql);

        t.commit();
        session.close();
        return getOccuper(salle);
    }
    public Occuper deleteSalle(Occuper salle){

        String hql = "DELETE FROM Salle as s WHERE s.salleId = :salleId";
        Session session = this.sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        Query query = session.createQuery(hql);
        t.commit();
        session.close();
        return null;
    }
    public List<Occuper> getAllSalle(){
        Session session = this.sessionFactory.openSession();
        return session.createQuery("FROM Occuper as o ORDER BY o.occuperSalle.salleCode ASC").getResultList();
    }

    public Prof getProfToOccuper(String key){
        Session session = this.sessionFactory.openSession();
        String hql = "FROM Prof";
        List<Prof> profs = session.createQuery(hql).getResultList();
        Prof prof1 = null;
        for (Prof prof: profs){
            if (key.contains(prof.getProfMatricule())) {
                prof1 = prof; break;
            }
        }
        return prof1;
    }
    public Salle getSalleToOccuper(String key){
        Session session = this.sessionFactory.openSession();
        String hql = "FROM Salle";
        List<Salle> salles = session.createQuery(hql).getResultList();
        Salle salle1 = null;
        for (Salle salle: salles){
            if (key.contains(salle.getSalleCode())) {
                salle1 = salle; break;
            }
        }
        return salle1;
    }

}
