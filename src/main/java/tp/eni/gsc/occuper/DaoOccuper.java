package main.java.tp.eni.gsc.occuper;

import main.java.tp.eni.gsc.config.HibernateUtil;
import main.java.tp.eni.gsc.occuper.bean.Occuper;
import main.java.tp.eni.gsc.prof.bean.Prof;
import main.java.tp.eni.gsc.salle.bean.Salle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.swing.*;
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

         return null;
    }

    public Occuper deleteSalle(String id){
        String hql = "DELETE FROM Occuper  WHERE occuperfId = :id";
        Session session = this.sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        session.createQuery(hql).setParameter("id",id).executeUpdate();
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
    public boolean findIfOccuper(Occuper occuper){
        Session session = this.sessionFactory.openSession();
        String hqlProf = "FROM Occuper WHERE occuperDate = :date";
        List<Occuper> lists = session.createQuery(hqlProf)
                .setParameter("date",occuper.getOccuperDate()).getResultList();
      System.out.println(" Taille" + lists.size());
      for (Occuper occuper1: lists){
         if(occuper1.getOccuperProf().getProfId().contentEquals(occuper.getOccuperProf().getProfId())){
             JOptionPane.showMessageDialog(null,
                     occuper.getOccuperProf().getProfNom()+ " déja occuper le " + occuper.getOccuperDate());
             return true;
         }
          if(occuper1.getOccuperSalle().getSalleId().equals(occuper.getOccuperSalle().getSalleId())){
              JOptionPane.showMessageDialog(null,
                      occuper.getOccuperSalle().getSalleDesignation()+ " déja occuper le " + occuper.getOccuperDate());
              return true;
          }
      }



       /* if (session.createQuery(hqlProf)
                .setParameter("prof",occuper.getOccuperProf())
                .setParameter("date",occuper.getOccuperDate()).getResultList().isEmpty())
        {
            JOptionPane.showMessageDialog(null,
                    occuper.getOccuperProf().getProfNom()+ " déja occuper le " + occuper.getOccuperDate());
            return true;
        }
        String hqlSalle = "FROM Occuper as o WHERE occuperSalle = :salle and occuperDate = :date";
        if (!session.createQuery(hqlSalle)
                .setParameter("salle",occuper.getOccuperSalle())
                .setParameter("date",occuper.getOccuperDate()).getResultList().isEmpty())
        {
            JOptionPane.showMessageDialog(null,
                    occuper.getOccuperProf().getProfNom()+ " déja occuper le " + occuper.getOccuperDate());
            return true;
        }*/

        return false;
    }

}
