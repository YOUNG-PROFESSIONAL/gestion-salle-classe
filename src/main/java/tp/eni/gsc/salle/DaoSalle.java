package main.java.tp.eni.gsc.salle;

import main.java.tp.eni.gsc.config.HibernateUtil;
import main.java.tp.eni.gsc.prof.bean.Prof;
import main.java.tp.eni.gsc.salle.bean.Salle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;

import java.util.List;

public class DaoSalle {
    SessionFactory sessionFactory;

    public DaoSalle(){
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public Salle saveSalle(Salle salle){
        Session session = this.sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.save(salle);
        } catch (Throwable e){
            e.printStackTrace();
        }
        t.commit();
        session.close();

         return getSalle(salle);
    }
    public Salle getSalle(Salle salle){
        Session session = this.sessionFactory.openSession();
        String hql = "FROM Salle WHERE salleCode = :code";
        System.out.println(session.createQuery(hql).setParameter("code",salle.getSalleCode()).getSingleResult());
        return (Salle) session.createQuery(hql).setParameter("code",salle.getSalleCode()).getSingleResult();
    }
    public Salle updateSalle(Salle salle){
        String hql = "UPDATE Salle Set salleCode = :code, salleDesignation = :designation " +
                "WHERE salleId = :id ";
        Session session = this.sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        Query query = session.createQuery(hql);
        query
                .setParameter("code",salle.getSalleCode())
                .setParameter("designation",salle.getSalleDesignation())
                .setParameter("id",salle.getSalleId()).executeUpdate();
        t.commit();
        session.close();
        return getSalle(salle);
    }
    public Salle deleteSalle(Salle salle){
        System.out.println( " ----> " + salle.getSalleId());
        String hql = "DELETE FROM Salle as s WHERE s.salleId = :salleId";
        Session session = this.sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        Query query = session.createQuery(hql);
        query.setParameter("salleId",salle.getSalleId());
        System.out.println(query.executeUpdate() + " " + salle.getSalleId());
        t.commit();
        session.close();
        return null;
    }
    public List<Salle> getAllSalle(){
        Session session = this.sessionFactory.openSession();
        return session.createQuery("Select a from Salle a ORDER BY salleCode ASC").getResultList();
    }

}
