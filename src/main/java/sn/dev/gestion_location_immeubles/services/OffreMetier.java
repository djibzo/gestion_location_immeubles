package sn.dev.gestion_location_immeubles.services;

import sn.dev.gestion_location_immeubles.DAO.Offres;
import sn.dev.gestion_location_immeubles.DAO.Unitesdelocations;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
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
   public void publierOffreByIdUnite(int id){
       try{
           transaction.begin();
           LocalDate localDate = LocalDate.now();
           Date sqlDate = Date.valueOf(localDate);
           Offres offre=new Offres();
           offre.setDatedeCreation(sqlDate);
           offre.setStatut(1);
           offre.setIdUnite(id);
           entityManager.persist(offre);
           //Valider la transaction
           transaction.commit();
           System.out.println("Transaction successful");
       }catch (Exception e){
           e.printStackTrace();
       }
   }
   public boolean verifOffreExist(int id){
        try {
            transaction.begin();
            String sql = "SELECT ofr FROM Offres ofr WHERE ofr.idUnite=:idU";
            javax.persistence.Query query = entityManager.createQuery(sql, Offres.class);
            query.setParameter("idU", id);
            Offres offre = (Offres) query.getSingleResult();
            if (offre!= null) {
                transaction.commit();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            if (transaction.isActive())
                transaction.rollback();
            System.out.println(e.getMessage());
        }
        return false;
   }
}
