package sn.dev.gestion_location_immeubles.services;

import sn.dev.gestion_location_immeubles.DAO.Immeubles;
import sn.dev.gestion_location_immeubles.DAO.Unitesdelocations;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class UniteLocMetier {
    EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager entityManager = managerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    /**
 * Cette méthode récupère une liste d'Unités de location de la base de données en fonction de l'identifiant de l'immeuble.
 *
 * @param idImme L'identifiant de l'immeuble pour lequel récupérer les Unités de location.
 * @return Une liste d'Unités de location associées à l'immeuble spécifié par idImme, ou null si aucune Unité de location n'est associée à cet immeuble.
 * @throws Exception Si une erreur se produit lors de l'opération de base de données.
 */
public List<Unitesdelocations> getUnitesdelocationsByIdImmeuble(int idImme) {
    try {
        // Débuter une transaction de base de données
        transaction.begin();

        // Créer une requête pour sélectionner une liste d'Unités de location en fonction de l'identifiant de l'immeuble
        String sql = "SELECT ul FROM Unitesdelocations ul WHERE ul.idImmeuble=:idImmeuble";
        javax.persistence.Query query = entityManager.createQuery(sql, Unitesdelocations.class);

        // Définir le paramètre de la requête
        query.setParameter("idImmeuble", idImme);

        // Exécuter la requête et récupérer la liste de résultats
        return (List<Unitesdelocations>) query.getResultList();
    } catch (Exception e) {
        // Si une transaction est toujours active, annuler les modifications
        if (transaction.isActive()) {
            transaction.rollback();
        }

        // Afficher le message d'erreur
        System.out.println(e.getMessage());
    }

    // Retourner null en cas d'erreur
    return null;
}
    /**
 * Cette méthode supprime une Unité de location de la base de données en fonction de son identifiant unique.
 *
 * @param id L'identifiant unique de l'Unité de location à supprimer.
 * @throws Exception Si une erreur se produit lors de l'opération de base de données.
 */
public void deleteUL(int id) {
    try {
        // Débuter une transaction de base de données
        transaction.begin();

        // Rechercher l'Unité de location dans la base de données en fonction de son identifiant unique
        Unitesdelocations ul = entityManager.find(Unitesdelocations.class, id);

        // Supprimer l'Unité de location de la base de données
        entityManager.remove(ul);

        // Valider la transaction
        transaction.commit();
    } catch (Exception e) {
        // Si une transaction est toujours active, annuler les modifications
        if (transaction.isActive()) {
            transaction.rollback();
        }

        // Afficher le message d'erreur
        System.out.println(e.getMessage());

        // Afficher la trace de la pile de l'exception
        e.printStackTrace();
    }
}
    /**
 * Cette méthode récupère une seule Unité de location de la base de données en fonction de son identifiant unique.
 *
 * @param idUL L'identifiant unique de l'Unité de location à récupérer.
 * @return L'Unité de location avec l'identifiant spécifié idUL, ou null si aucune telle Unité de location n'existe.
 * @throws Exception Si une erreur se produit lors de l'opération de base de données.
 */
public Unitesdelocations getULByID(int idUL) {
    try {
        // Débuter une transaction de base de données
        transaction.begin();

        // Créer une requête pour sélectionner une Unité de location en fonction de son identifiant
        String sql = "SELECT ul FROM Unitesdelocations ul WHERE ul.idUnite=:idUnite";
        javax.persistence.Query query = entityManager.createQuery(sql, Unitesdelocations.class);

        // Définir le paramètre de la requête
        query.setParameter("idUnite", idUL);

        // Exécuter la requête et récupérer le résultat unique
        return (Unitesdelocations) query.getSingleResult();
    } catch (Exception e) {
        // Si une transaction est toujours active, annuler les modifications
        if (transaction.isActive()) {
            transaction.rollback();
        }

        // Afficher le message d'erreur
        System.out.println(e.getMessage());

        // Afficher la trace de la pile de l'exception
        e.printStackTrace();
    }

    // Retourner null en cas d'erreur
    return null;
}
}
