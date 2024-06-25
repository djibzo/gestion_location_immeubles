package sn.dev.gestion_location_immeubles.services;

import sn.dev.gestion_location_immeubles.DAO.Contratsdelocations;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

public class ContratLocationMetier {
    EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager entityManager = managerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    public void addContratLocation(int idUnite, int idLocataire) {
        try {
            transaction.begin();

            // Create a new ContratLocation object
            Contratsdelocations contratLocation = new Contratsdelocations();
            contratLocation.setIdUnite(idUnite);
            contratLocation.setIdLocataire(idLocataire);

            // Set the date of creation to the current date
            LocalDate localDate = LocalDate.now();
            Date dateCreation = Date.valueOf(localDate);
            contratLocation.setDatedeCreation(dateCreation);

            // Persist the ContratLocation object
            entityManager.persist(contratLocation);

            // Commit the transaction
            transaction.commit();
            System.out.println("Contrat de location added successfully");
        } catch (Exception e) {
            // Rollback the transaction if it is still active
            if (transaction.isActive())
                transaction.rollback();

            // Print the error message
            System.out.println(e.getMessage());
        }
    }
    public Contratsdelocations getContratLocation(int idUnite, int idLocataire) {
        Contratsdelocations contratLocation = null;
        try {
            transaction.begin();

            // Create a query to retrieve the contrat de location with the given idUnite and idLocataire
            String sql = "SELECT c FROM Contratsdelocations c WHERE c.idUnite=:idUnite AND c.idLocataire=:idLocataire";
            Query query = entityManager.createQuery(sql, Contratsdelocations.class);
            query.setParameter("idUnite", idUnite);
            query.setParameter("idLocataire", idLocataire);

            // Get the single result of the query
            contratLocation = (Contratsdelocations) query.getSingleResult();

            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            // Rollback the transaction if it is still active
            if (transaction.isActive())
                transaction.rollback();

            // Print the error message
            System.out.println(e.getMessage());
        }
        return contratLocation;
    }
}
