package main.java.tp.eni.gsc.prof;

import main.java.tp.eni.gsc.config.HibernateUtil;
import main.java.tp.eni.gsc.prof.bean.Prof;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class DaoProf {
    SessionFactory sessionFactory;

    public DaoProf(){
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public Prof saveProf(Prof prof){
        Session session = this.sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        session.save(prof);
        t.commit();
        session.close();
         return null;
    }
    public Prof updateProf(Prof prof){
        String hql = "UPDATE Prof Set profNom = :profNom, profPrenom = :profPrenom, profGrade = :profGrade " +
                "WHERE profMatricule = :profMatricule";
        Session session = this.sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        Query query = session.createQuery(hql);
        query.setParameter("profNom",prof.getProfNom());
        query.setParameter("profPrenom",prof.getProfPrenom());
        query.setParameter("profGrade",prof.getProfGrade());
        query.setParameter("profMatricule",prof.getProfMatricule());
        query.executeUpdate();
        t.commit();
        session.close();
        return null;
    }
    public Prof deleteProf(Prof prof){
        String hql = "DELETE FROM Prof " +
                "WHERE profMatricule = :profMatricule";
        Session session = this.sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        Query query = session.createQuery(hql);
        query.setParameter("profMatricule",prof.getProfMatricule());
        query.executeUpdate();
        t.commit();
        session.close();
        return null;
    }
    public List<Prof> getALLProf(){
        String hql = "FROM Prof ORDER BY profMatricule ASC";
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery(hql);
        return query.getResultList();
    }

}
