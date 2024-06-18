package sn.dev.gestion_location_immeubles.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sn.dev.gestion_location_immeubles.DAO.Unitesdelocations;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

@WebServlet(name = "uniteloc",urlPatterns = {"/uniteloc","*.od"})
public class UniteLocServlet extends HttpServlet {
    private int idImmeuble;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/unitesloc/ajout.jsp").forward(req, resp);
        idImmeuble = Integer.parseInt(req.getParameter("idImmeuble"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = managerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();
            String nomUnite=req.getParameter("nomUnite");
            int nombrePieces= Integer.parseInt(req.getParameter("nbrePieces"));
            float superficie= Float.parseFloat(req.getParameter("superficie"));
            double prixLoyer= Float.parseFloat(req.getParameter("prixLoyer"));
            LocalDate localDate = LocalDate.now();
            Date sqlDate = Date.valueOf(localDate);
            Unitesdelocations unite=new Unitesdelocations();
            unite.setNomUnite(nomUnite);
            unite.setNombrePieces(nombrePieces);
            unite.setSuperficie(superficie);
            unite.setPrixLoyer(prixLoyer);
            unite.setIdImmeuble(idImmeuble);
            unite.setDatedeCreation(sqlDate);
            entityManager.persist(unite);
            //Valider la transaction
            transaction.commit();
            System.out.println("Transaction successful");
            req.getRequestDispatcher("WEB-INF/jsp/immeuble/list.jsp").forward(req, resp);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
