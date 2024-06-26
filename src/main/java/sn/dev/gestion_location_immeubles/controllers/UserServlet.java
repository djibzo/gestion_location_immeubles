package sn.dev.gestion_location_immeubles.controllers;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sn.dev.gestion_location_immeubles.DAO.Unitesdelocations;
import sn.dev.gestion_location_immeubles.DAO.Utilisateurs;
import sn.dev.gestion_location_immeubles.services.UserMetier;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.IOException;

@WebServlet(name = "user",urlPatterns = {"*.us"})
public class UserServlet extends HttpServlet {
    private UserMetier metier;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        int idUser = Integer.parseInt(req.getParameter("idUser"));
        Utilisateurs user= metier.getUserById(idUser);
        if (action!=null && action.equals("add")){
            req.getRequestDispatcher("WEB-INF/jsp/user/ajout.jsp").forward(req, resp);
        }
        req.setAttribute("user",user);
        req.getRequestDispatcher("WEB-INF/jsp/users/user.jsp").forward(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        metier=new UserMetier();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = managerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        if (action==null) {
            int idUser = Integer.parseInt(req.getParameter("idUser"));

            // Retrieve the updated user information from the request parameters
            String prenomUser = req.getParameter("prenom");
            String nomUser = req.getParameter("nom");
            String emailUser = req.getParameter("email");
            String mdpUser = req.getParameter("mdp");

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(mdpUser);

            //Utilisateurs user = metier.getUserById(idUser);
            try {
                transaction.begin();
                Utilisateurs user = entityManager.find(Utilisateurs.class, idUser);
                user.setPrenomUser(prenomUser);
                user.setNomUser(nomUser);
                user.setEmailUser(emailUser);
                //user.setMdpUser(hashedPassword);
                entityManager.merge(user);
                transaction.commit();
                // Redirect to the user's profile page or any other appropriate page
                resp.sendRedirect("logout");
            } catch (Exception e) {
                if (transaction.isActive())
                    transaction.rollback();
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        } else if (action!=null && action.equals("add")) {

        }
    }
}
