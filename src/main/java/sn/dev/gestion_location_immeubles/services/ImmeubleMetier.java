package sn.dev.gestion_location_immeubles.services;

import sn.dev.gestion_location_immeubles.DAO.Immeubles;
import sn.dev.gestion_location_immeubles.DAO.Utilisateurs;

import javax.persistence.*;
import java.util.List;

public class ImmeubleMetier {
    EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager entityManager = managerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    public List<Immeubles> getImmeubles(){
        try {
            transaction.begin();
            String sql="SELECT im FROM Immeubles im";
            Query query=entityManager.createQuery(sql,Immeubles.class);
            return (List<Immeubles>) query.getResultList();
        }catch (Exception e) {
            if(transaction.isActive())
                transaction.rollback();
            System.out.println(e.getMessage());
            return null;
        }
    }
    public Immeubles getImmeublesById(int idIm){
        try {
            transaction.begin();
            String sql="SELECT im FROM Immeubles im WHERE im.idImmeuble=:idIm";
            TypedQuery<Immeubles> query=entityManager.createQuery(sql,Immeubles.class);
            query.setParameter("idIm",idIm);
            return query.getSingleResult();
        }catch (Exception e) {
            if(transaction.isActive())
                transaction.rollback();
            System.out.println(e.getMessage());
            return null;
        }
    }
}
