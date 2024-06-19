package sn.dev.gestion_location_immeubles.services;

import sn.dev.gestion_location_immeubles.DAO.Offres;
import sn.dev.gestion_location_immeubles.DAO.Unitesdelocations;

import javax.persistence.*;
import java.util.List;

public class OffreMetier {
    EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager entityManager = managerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    public List<Offres> getOffresActives() {
        try {
            transaction.begin();
            String sql = "SELECT of FROM Offres of WHERE of.statut=1 ";
            javax.persistence.Query query = entityManager.createQuery(sql, Unitesdelocations.class);
            return (List<Offres>) query.getResultList();
        } catch (Exception e) {
            if (transaction.isActive())
                transaction.rollback();
            System.out.println(e.getMessage());
        }
        return null;
    }
   public List<Object[]> getUnitesdelocationsFromOffres() {
       List<Object[]> results = null;
       EntityTransaction transaction = entityManager.getTransaction();

       try {
           transaction.begin();
           String sql ="SELECT u, i, o FROM Unitesdelocations u " +
                   "JOIN u.offresByIdUnite o " +
                   "JOIN u.immeublesByIdImmeuble i " +
                   "WHERE o.statut = 1";
           Query query = entityManager.createQuery(sql, Object[].class);
           results = query.getResultList();
           transaction.commit();
       } catch (Exception e) {
           if (transaction.isActive())
               transaction.rollback();
           e.printStackTrace();
       }

       return results;
   }
}
