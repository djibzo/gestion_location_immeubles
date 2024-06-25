package sn.dev.gestion_location_immeubles.services;

import sn.dev.gestion_location_immeubles.DAO.Paiements;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.Date;
import java.time.LocalDate;

public class PaiementMetier {
    EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager entityManager = managerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    public void createPaiement(int idLocataire, int idContrat) {
        try {
            transaction.begin();

            // Create a new Paiements object
            Paiements paiement = new Paiements();
            paiement.setIdLocataire(idLocataire);
            paiement.setIdContratLocation(idContrat);

            // Set the date of creation to the current date
            LocalDate localDate = LocalDate.now();
            Date dateCreation = Date.valueOf(localDate);
            paiement.setDatedeCreation(dateCreation);

            // Persist the Paiements object
            entityManager.persist(paiement);

            // Commit the transaction
            transaction.commit();
            System.out.println("Paiement created successfully");
        } catch (Exception e) {
            // Rollback the transaction if it is still active
            if (transaction.isActive())
                transaction.rollback();

            // Print the error message
            System.out.println(e.getMessage());
        }
    }
}
