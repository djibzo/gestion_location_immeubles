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
    /**
 * Cette méthode récupère une liste de tableaux contenant des objets Unitesdelocations, Immeubles et Offres
 * où les Offres ont un statut actif (1).
 *
 * @return Une liste de tableaux, où chaque tableau contient un objet Unitesdelocations, Immeubles et Offres.
 *         Retourne null en cas d'erreur lors de l'opération de base de données.
 */
public List<Object[]> getUnitesdelocationsFromOffres() {
    // Initialiser la liste de résultats à null
    List<Object[]> results = null;

    // Récupérer l'objet de transaction à partir de l'entity manager
    EntityTransaction transaction = entityManager.getTransaction();

    try {
        // Débuter une transaction
        transaction.begin();

        // Préparer la requête SQL pour récupérer les données
        String sql = "SELECT u, i, o FROM Unitesdelocations u " +
                       "JOIN u.offresByIdUnite o " +
                       "JOIN u.immeublesByIdImmeuble i " +
                       "WHERE o.statut = 1";

        // Créer un objet de requête à partir de l'entity manager
        Query query = entityManager.createQuery(sql, Object[].class);

        // Exécuter la requête et récupérer la liste de résultats
        results = query.getResultList();

        // Valider la transaction
        transaction.commit();
    } catch (Exception e) {
        // Si la transaction est toujours active, annuler les modifications
        if (transaction.isActive()) {
            transaction.rollback();
        }

        // Afficher la trace de la pile de l'exception
        e.printStackTrace();
    }

    // Retourner la liste de résultats
    return results;
}
    /**
 * Cette méthode est utilisée pour vérifier si une offre est active en vérifiant son statut.
 *
 * @param id L'identifiant unique de l'unité associée à l'offre.
 * @return Vrai si l'offre est active, faux sinon.
 * @throws Exception Si une erreur se produit lors de l'opération de base de données.
 */
public boolean verifOffreActive(int id){
    try {
        // Débuter une transaction
        transaction.begin();

        // Préparer la requête SQL pour récupérer l'offre
        String sql = "SELECT ofr FROM Offres ofr WHERE ofr.idUnite=:idU";

        // Créer un objet de requête à partir de l'entity manager
        javax.persistence.Query query = entityManager.createQuery(sql, Offres.class);

        // Définir le paramètre de la requête
        query.setParameter("idU", id);

        // Exécuter la requête et récupérer le résultat
        Offres offre = (Offres) query.getSingleResult();

        // Vérifier le statut de l'offre
        if (offre!= null && offre.getStatut()==1) {
            // Valider la transaction
            transaction.commit();

            // Retourner vrai
            return true;
        } else {
            // Retourner faux
            return false;
        }
    } catch (Exception e) {
        // Si la transaction est toujours active, annuler les modifications
        if (transaction.isActive()) {
            transaction.rollback();
        }

        // Afficher le message d'erreur
        System.out.println(e.getMessage());
    }

    // Retourner faux par défaut
    return false;
}
    /**
 * Cette méthode est utilisée pour désactiver une offre en mettant son statut à inactif.
 *
 * @param idU L'identifiant unique de l'unité associée à l'offre à désactiver.
 * @throws Exception Si une erreur se produit lors de l'opération de base de données.
 */
public void depublierOffreByIdUnite(int idU) {
    // Récupérer l'objet de transaction à partir de l'entity manager
    EntityTransaction transaction = entityManager.getTransaction();

    try {
        // Débuter une transaction
        transaction.begin();

        // Préparer la requête SQL pour mettre à jour le statut de l'offre
        String sql = "UPDATE Offres o SET o.statut = 0 WHERE o.idUnite ="+idU;

        // Créer un objet de requête à partir de l'entity manager
        javax.persistence.Query query = entityManager.createQuery(sql);

        // Exécuter la requête d'update
        query.executeUpdate();

        // Valider la transaction
        transaction.commit();
    } catch (Exception e) {
        // Si la transaction est toujours active, annuler les modifications
        if (transaction.isActive()) {
            transaction.rollback();
        }

        // Afficher la trace de la pile de l'exception
        e.printStackTrace();
    }
}
    /**
 * Cette méthode est utilisée pour publier une offre en mettant son statut à actif.
 *
 * @param idO L'identifiant unique de l'offre à publier.
 * @throws Exception Si une erreur se produit lors de l'opération de base de données.
 */
public void publierOffreByIdUnite(int idO){
    EntityTransaction transaction = entityManager.getTransaction();
    try {
        // Débuter une transaction
        transaction.begin();

        // Préparer la requête SQL pour mettre à jour le statut de l'offre
        String sql = "UPDATE Offres o SET o.statut = 1 WHERE o.idUnite ="+idO;

        // Créer un objet de requête à partir de l'entity manager
        javax.persistence.Query query = entityManager.createQuery(sql);

        // Exécuter la requête d'update
        query.executeUpdate();

        // Valider la transaction
        transaction.commit();
    } catch (Exception e) {
        // Si la transaction est toujours active, annuler les modifications
        if (transaction.isActive()) {
            transaction.rollback();
        }

        // Afficher la trace de la pile de l'exception
        e.printStackTrace();
    }
}
}
