package sn.dev.gestion_location_immeubles.controllers;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sn.dev.gestion_location_immeubles.DAO.Immeubles;
import sn.dev.gestion_location_immeubles.DAO.Utilisateurs;
import sn.dev.gestion_location_immeubles.services.ImmeubleMetier;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

@WebServlet(name = "immeubles",urlPatterns = {"*.do","/immeubles"})
public class ImmeubleServlet extends HttpServlet {
    private ImmeubleMetier metier;
    @Override
    public void init(ServletConfig config) throws ServletException {
         metier=new ImmeubleMetier();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action!=null && action.equals("add")){
            req.getRequestDispatcher("WEB-INF/jsp/immeuble/ajout.jsp").forward(req, resp);
            System.out.println("create");
            return;
        }else if(action!=null && action.equals("details")){
            int idImmeuble=Integer.parseInt(req.getParameter("id"));
            Immeubles immeuble=metier.getImmeublesById(idImmeuble);
            req.setAttribute("immeuble",immeuble);
            req.getRequestDispatcher("WEB-INF/jsp/unitesloc/detailsImmeuble.jsp").forward(req, resp);
        }else if(action!=null && action.equals("update")){
            int idImmeuble=Integer.parseInt(req.getParameter("id"));
            Immeubles immeuble=metier.getImmeublesById(idImmeuble);
            req.setAttribute("immeuble",immeuble);
            req.getRequestDispatcher("WEB-INF/jsp/immeuble/modification.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("WEB-INF/jsp/immeuble/list.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = managerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        if (action==null) {
            try {
                transaction.begin();
                String nomImmeuble = req.getParameter("nomImmeuble");
                String adresseImmeuble = req.getParameter("adresseImmeuble");
                String description = req.getParameter("description");
                int idProprietaire = Integer.parseInt(req.getParameter("idProprietaire"));
                Immeubles immeuble = new Immeubles();
                immeuble.setAdresseImmeuble(adresseImmeuble);
                immeuble.setDescription(description);
                immeuble.setNomImmeuble(nomImmeuble);
                immeuble.setIdProprietaire(idProprietaire);
                LocalDate localDate = LocalDate.now();
                Date sqlDate = Date.valueOf(localDate);
                immeuble.setDatedeCreation(sqlDate);
                entityManager.persist(immeuble);
                //Valider la transaction
                transaction.commit();
                System.out.println("Transaction successful");
                req.getRequestDispatcher("WEB-INF/jsp/immeuble/list.jsp").forward(req, resp);
            } catch (Exception e) {
                if (transaction.isActive())
                    transaction.rollback();
            }
        }else if(action.equals("supdate")){
            try {
                transaction.begin();
                String nomImmeuble = req.getParameter("nomImmeuble");
                String adresseImmeuble = req.getParameter("adresseImmeuble");
                String description = req.getParameter("description");
                int idProprietaire = Integer.parseInt(req.getParameter("idProprietaire"));
                int idImmeuble = Integer.parseInt(req.getParameter("idImmeuble"));
                Immeubles immeuble = entityManager.find(Immeubles.class, idImmeuble);
                immeuble.setAdresseImmeuble(adresseImmeuble);
                immeuble.setDescription(description);
                immeuble.setNomImmeuble(nomImmeuble);
                immeuble.setIdProprietaire(idProprietaire);
                // Assuming you might want to update the creation date as well
                LocalDate localDate = LocalDate.now();
                Date sqlDate = Date.valueOf(localDate);
                immeuble.setDatedeCreation(sqlDate);
                // Persist the changes to the database
                entityManager.merge(immeuble);
                // Commit the transaction
                transaction.commit();
                resp.sendRedirect("immeubles");
            }catch (Exception e) {
                if (transaction.isActive())
                    transaction.rollback();
                e.printStackTrace();
            }
        }
        entityManager.close();
        managerFactory.close();
    }
}
