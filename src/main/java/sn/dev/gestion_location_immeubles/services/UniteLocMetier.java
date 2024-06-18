package sn.dev.gestion_location_immeubles.services;

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
    public List<Unitesdelocations> getUnitesdelocationsByIdImmeuble(int idImme) {
        try {
            transaction.begin();
            String sql="SELECT ul FROM Unitesdelocations ul WHERE ul.idImmeuble=:idImmeuble";
            javax.persistence.Query query=entityManager.createQuery(sql,Unitesdelocations.class);
            query.setParameter("idImmeuble",idImme);
            return (List<Unitesdelocations>) query.getResultList();
        }catch (Exception e) {
            if(transaction.isActive())
                transaction.rollback();
            System.out.println(e.getMessage());
        }
        return null;
    }
}
