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
}
