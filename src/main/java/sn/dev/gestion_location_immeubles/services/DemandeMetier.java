package sn.dev.gestion_location_immeubles.services;

import sn.dev.gestion_location_immeubles.DAO.Demandes;
import sn.dev.gestion_location_immeubles.DAO.Immeubles;
import sn.dev.gestion_location_immeubles.DAO.Offres;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class DemandeMetier {
    EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager entityManager = managerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();

    public void addDemande(int idUser, int idOffre) {
        try {
            transaction.begin();
            LocalDate localDate = LocalDate.now();
            Date sqlDate = Date.valueOf(localDate);
            Demandes demande = new Demandes();
            demande.setIdUser(idUser);
            demande.setIdOffre(idOffre);
            demande.setEtat(-1);
            demande.setDatedecreation(sqlDate);
            entityManager.persist(demande);
            //Valider la transaction
            transaction.commit();
            System.out.println("Transaction successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean verifIfUserPostuled(int idOffre, int idUser) {
        try {
            transaction.begin();
            String sql = "SELECT dm FROM Demandes dm WHERE dm.idOffre=:idOffre AND dm.idUser=:idUser";
            javax.persistence.Query query = entityManager.createQuery(sql, Demandes.class);
            query.setParameter("idOffre", idOffre);
            query.setParameter("idUser", idUser);
            Demandes demande = (Demandes) query.getSingleResult();
            if (demande != null) {
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

    public List<Object[]> getDemandesByidUser(int idU) {
        List<Object[]> results = null;
        try {
            transaction.begin();
            String sql = "SELECT dm,ofr,ul" +
                    " FROM Demandes dm,Offres ofr,Unitesdelocations ul" +
                    " WHERE dm.idOffre=ofr.idOffre AND ofr.idUnite=ul.idUnite AND dm.idUser=:idU";
            Query query = entityManager.createQuery(sql, Object[].class);
            query.setParameter("idU", idU);
            results = query.getResultList();
        } catch (Exception e) {
            if (transaction.isActive())
                transaction.rollback();
            System.out.println(e.getMessage());
        }
        return results;
    }

}
