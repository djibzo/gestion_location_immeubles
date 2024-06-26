package sn.dev.gestion_location_immeubles.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.security.core.userdetails.User;
import sn.dev.gestion_location_immeubles.DAO.Utilisateurs;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.util.List;

public class UserMetier {
    EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager entityManager = managerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    public Utilisateurs findByEmail(String email) {
        try {
            transaction.begin();
            String sql="SELECT u FROM Utilisateurs u WHERE u.emailUser=:emailUser";
            TypedQuery<Utilisateurs> query=entityManager.createQuery(sql,Utilisateurs.class);
            query.setParameter("emailUser",email);
            return query.getSingleResult();
        }catch (Exception e) {
            if(transaction.isActive())
                transaction.rollback();
            System.out.println(e.getMessage());
            return null;
        }
    }
    public List<Utilisateurs> getUsers(){
        try {
            transaction.begin();
            String sql="SELECT u FROM Utilisateurs u";
            Query query=entityManager.createQuery(sql,Utilisateurs.class);
            return (List<Utilisateurs>) query.getResultList();
        }catch (Exception e) {
            if(transaction.isActive())
                transaction.rollback();
            System.out.println(e.getMessage());
            return null;
        }
    }
    public List<Utilisateurs> getProprietaire(){
        try {
            transaction.begin();
            String sql="SELECT u FROM Utilisateurs u WHERE u.profilUser = 2";
            Query query=entityManager.createQuery(sql,Utilisateurs.class);
            return (List<Utilisateurs>) query.getResultList();
        }catch (Exception e) {
            if(transaction.isActive())
                transaction.rollback();
            System.out.println(e.getMessage());
            return null;
        }
    }
    /**
 * This method verifies if a given email already exists in the database.
 *
 * @param email The email to be checked.
 * @return true if the email exists in the database, false otherwise.
 * @throws Exception If any error occurs during the database operation.
 */
public boolean verifIfEmailExists(String email) {
    try {
        // Begin a transaction
        transaction.begin();

        // Create a query to select users with the given email
        String sql="SELECT u FROM Utilisateurs u WHERE u.emailUser=:emailUser";
        TypedQuery<Utilisateurs> query=entityManager.createQuery(sql,Utilisateurs.class);
        query.setParameter("emailUser",email);

        // Check if the query returns any result
        if(query.getResultList().isEmpty())
            return false; // Email does not exist
        else
            return true; // Email exists
    }catch (Exception e) {
        // Rollback the transaction if it is still active
        if(transaction.isActive())
            transaction.rollback();

        // Print the error message
        System.out.println(e.getMessage());

        // Return false in case of any error
        return false;
    }
}
    public Utilisateurs getUserById(int userId) {
        try {
            transaction.begin();
            String sql = "SELECT u FROM Utilisateurs u WHERE u.idUser = :userId";
            TypedQuery<Utilisateurs> query = entityManager.createQuery(sql, Utilisateurs.class);
            query.setParameter("userId", userId);
            return query.getSingleResult();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void updateUser(Utilisateurs user) {
        try {
            transaction.begin();
            entityManager.merge(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }
    }
}
