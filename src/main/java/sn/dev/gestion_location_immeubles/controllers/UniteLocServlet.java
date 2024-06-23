package sn.dev.gestion_location_immeubles.controllers;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sn.dev.gestion_location_immeubles.DAO.Immeubles;
import sn.dev.gestion_location_immeubles.DAO.Offres;
import sn.dev.gestion_location_immeubles.DAO.Unitesdelocations;
import sn.dev.gestion_location_immeubles.services.UniteLocMetier;

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
    private UniteLocMetier metier;
    @Override
    public void init(ServletConfig config) throws ServletException {
        metier=new UniteLocMetier();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        if(action!=null && action.equals("delete")){
            int idUL=Integer.parseInt(req.getParameter("id"));
            metier.deleteUL(idUL);
            req.getRequestDispatcher("WEB-INF/jsp/immeuble/list.jsp").forward(req, resp);
        }else if(action!=null && action.equals("update")){
            int idUL=Integer.parseInt(req.getParameter("id"));
            Unitesdelocations ul=metier.getULByID(idUL);
            req.setAttribute("ul",ul);
            req.getRequestDispatcher("WEB-INF/jsp/unitesloc/modification.jsp").forward(req, resp);
        }
        idImmeuble = Integer.parseInt(req.getParameter("idImmeuble"));
        req.getRequestDispatcher("WEB-INF/jsp/unitesloc/ajout.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = managerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        if (action==null){
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
                //Creation offre
                Offres offre=new Offres();
                offre.setDatedeCreation(sqlDate);
                offre.setStatut(0);
                offre.setIdUnite(unite.getIdUnite());
                entityManager.persist(offre);
                transaction.commit();
                System.out.println("Transaction successful");
                req.getRequestDispatcher("WEB-INF/jsp/immeuble/list.jsp").forward(req, resp);
            }catch (Exception e){
                e.printStackTrace();
            }
        } else if (action!=null && action.equals("supdate")) {
            try {
                transaction.begin();
                String nomUnite = req.getParameter("nomUnite");
                int nbrePieces = Integer.parseInt(req.getParameter("nbrePieces"));
                float superficie = Float.parseFloat(req.getParameter("superficie"));
                double prixLoyer = Float.parseFloat(req.getParameter("prixLoyer"));
                int idUl=Integer.parseInt(req.getParameter("idUnite"));
                Unitesdelocations ul=entityManager.find(Unitesdelocations.class,idUl);
                ul.setNomUnite(nomUnite);
                ul.setNombrePieces(nbrePieces);
                ul.setSuperficie(superficie);
                ul.setPrixLoyer(prixLoyer);
                entityManager.merge(ul);
                transaction.commit();
                resp.sendRedirect("immeuble.do?action=details&id="+ul.getIdImmeuble());
            }catch (Exception e){
                e.printStackTrace();
                transaction.rollback();
            }
        }

    }
}
