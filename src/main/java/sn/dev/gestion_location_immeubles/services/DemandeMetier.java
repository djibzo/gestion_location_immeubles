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
            demande.setEtat(0);//0 pending
            demande.setDatedecreation(sqlDate);
            entityManager.persist(demande);
            //Valider la transaction
            transaction.commit();
            System.out.println("Transaction successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Cette méthode vérifie si un utilisateur a déjà postulé pour une offre spécifique.
     *
     * @param idOffre L'id de l'offre.
     * @param idUser L'id de l'utilisateur.
     * @return Une valeur booléenne indiquant si l'utilisateur a déjà postulé pour l'offre.
     *         Retourne true si l'utilisateur a postulé pour l'offre, false sinon.
     * @throws Exception Si une erreur se produit lors de l'opération de base de données.
     */
public boolean verifIfUserPostuled(int idOffre, int idUser) {
    try {
        // Begin a transaction
        transaction.begin();

        // Create a query to select demandes with the given idOffre and idUser
        String sql = "SELECT dm FROM Demandes dm WHERE dm.idOffre=:idOffre AND dm.idUser=:idUser";
        javax.persistence.Query query = entityManager.createQuery(sql, Demandes.class);
        query.setParameter("idOffre", idOffre);
        query.setParameter("idUser", idUser);

        // Get the single result of the query
        Demandes demande = (Demandes) query.getSingleResult();

        // Check if the demande exists
        if (demande != null) {
            // Commit the transaction
            transaction.commit();
            return true; // User has applied for the offer
        } else {
            return false; // User has not applied for the offer
        }
    } catch (Exception e) {
        // Rollback the transaction if it is still active
        if (transaction.isActive())
            transaction.rollback();

        // Print the error message
        System.out.println(e.getMessage());
    }

    // Return false by default in case of any error
    return false;
}
    public List<Object[]> getDemandesByidUser(int idU) {
    List<Object[]> results = null;
    try {
        transaction.begin();
        String sql = "SELECT dm,ofr,ul,imm" +
                       " FROM Demandes dm" +
                       " JOIN Offres ofr ON dm.idOffre = ofr.idOffre" +
                       " JOIN Unitesdelocations ul ON ofr.idUnite = ul.idUnite" +
                       " JOIN Immeubles imm ON ul.idImmeuble = imm.idImmeuble" +
                       " WHERE dm.idUser = :idU";
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
    public List<Object[]> getDemandesByProprio(int idU) {
    List<Object[]> results = null;
    try {
        transaction.begin();
        String sql = "SELECT dm, ofr, ul, imm, usr" +
                       " FROM Demandes dm" +
                       " JOIN Offres ofr ON dm.idOffre = ofr.idOffre" +
                       " JOIN Unitesdelocations ul ON ofr.idUnite = ul.idUnite" +
                       " JOIN Immeubles imm ON ul.idImmeuble = imm.idImmeuble" +
                       " JOIN Utilisateurs usr ON dm.idUser = usr.idUser" +
                       " WHERE imm.idProprietaire = :idU AND dm.etat="+0;
        Query query = entityManager.createQuery(sql, Object[].class);
        query.setParameter("idU", idU);
        results = query.getResultList();
        System.out.println(results);
    } catch (Exception e) {
        if (transaction.isActive())
            transaction.rollback();
        System.out.println(e.getMessage());
    }
    return results;
    }
    public void acceptDemande(int idDemande) {
        try {
            transaction.begin();
            // Find the demande with the given idDemande
            Demandes demande = entityManager.find(Demandes.class, idDemande);

            // Check if the demande exists
            if (demande != null) {
                // Update the etat of the demande
                demande.setEtat(1); // Set the etat to 1

                // Persist the changes
                entityManager.persist(demande);

                // Commit the transaction
                transaction.commit();
                System.out.println("Demande updated successfully");
            } else {
                System.out.println("Demande not found");
            }
        } catch (Exception e) {
            // Rollback the transaction if it is still active
            if (transaction.isActive())
                transaction.rollback();

            // Print the error message
            System.out.println(e.getMessage());
        }
    }
    public void rejectDemande(int idDemande) {
        try {
            transaction.begin();
            // Find the demande with the given idDemande
            Demandes demande = entityManager.find(Demandes.class, idDemande);

            // Check if the demande exists
            if (demande != null) {
                // Update the etat of the demande
                demande.setEtat(-1); // Set the etat to 1

                // Persist the changes
                entityManager.persist(demande);

                // Commit the transaction
                transaction.commit();
                System.out.println("Demande updated successfully");
            } else {
                System.out.println("Demande not found");
            }
        } catch (Exception e) {
            // Rollback the transaction if it is still active
            if (transaction.isActive())
                transaction.rollback();

            // Print the error message
            System.out.println(e.getMessage());
        }
    }
    public int getIdUniteByDemande(int idDemande) {
        int idUnite = 0; // Initialize with a default value

        try {
            transaction.begin();
            // Create a query to select the idUnite from the Offres table using the idOffre from the Demandes table
            String sql = "SELECT ul.idUnite" +
                    " FROM Demandes dm" +
                    " JOIN Offres ofr ON dm.idOffre = ofr.idOffre" +
                    " JOIN Unitesdelocations ul ON ofr.idUnite = ul.idUnite" +
                    " WHERE dm.idDemande = :idDemande";
            Query query = entityManager.createQuery(sql, Integer.class);
            query.setParameter("idDemande", idDemande);

            // Get the single result of the query
            idUnite = (int) query.getSingleResult();

            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            // Rollback the transaction if it is still active
            if (transaction.isActive())
                transaction.rollback();

            // Print the error message
            System.out.println(e.getMessage());
        }

        return idUnite;
    }
    public int getIdUserByDemande(int idDemande) {
        int idUser = 0; // Initialize with a default value

        try {
            transaction.begin();
            // Create a query to select the idUser from the Demandes table
            String sql = "SELECT dm.idUser" +
                    " FROM Demandes dm" +
                    " WHERE dm.idDemande = :idDemande";
            Query query = entityManager.createQuery(sql, Integer.class);
            query.setParameter("idDemande", idDemande);

            // Get the single result of the query
            idUser = (int) query.getSingleResult();

            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            // Rollback the transaction if it is still active
            if (transaction.isActive())
                transaction.rollback();

            // Print the error message
            System.out.println(e.getMessage());
        }

        return idUser;
    }
}