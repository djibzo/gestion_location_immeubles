package sn.dev.gestion_location_immeubles.controllers;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sn.dev.gestion_location_immeubles.DAO.Utilisateurs;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sn.dev.gestion_location_immeubles.services.UserMetier;

@WebServlet(name = "signup",urlPatterns = {"/signup","*.su"})
public class SignUpServlet extends HttpServlet {
    private UserMetier metier;

    @Override
    public void init(ServletConfig config) throws ServletException {
        metier =new UserMetier();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("inscription.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = managerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        if (action == null) {
            try {
                transaction.begin();
                String prenomUser = req.getParameter("prenomUser");
                String nomUser = req.getParameter("nomUser");
                String emailUser = req.getParameter("emailUser");
                String mdpUser = req.getParameter("mdpUser");
                if (metier.verifIfEmailExists(emailUser)) {
                    req.setAttribute("errorMessage", "Cet email existe déjà!");
                    req.getRequestDispatcher("inscription.jsp").forward(req, resp);
                    return;
                } else {

                    Utilisateurs user = new Utilisateurs();
                    user.setPrenomUser(prenomUser);
                    user.setNomUser(nomUser);
                    user.setEmailUser(emailUser);
                    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                    // Hacher le mot de passe
                    String hashedPassword = passwordEncoder.encode(mdpUser);
                    user.setMdpUser(hashedPassword);
                    LocalDate localDate = LocalDate.now();
                    Date sqlDate = Date.valueOf(localDate);
                    user.setDatedeCreation(sqlDate);
                    user.setProfilUser(3);//Profil locataire (3)
                    entityManager.persist(user);
                    //Valider la transaction
                    transaction.commit();
                    req.setAttribute("successMessage", "Inscription effectuée avec success !");
                    req.getRequestDispatcher("inscription.jsp").forward(req, resp);
                    System.out.println("Transaction successful");
                }
            } catch (Exception e) {
                if (transaction.isActive())
                    transaction.rollback();
            }
            entityManager.close();
            managerFactory.close();
        } else if (action!=null && action.equals("add")) {
            try{
                transaction.begin();
                String prenomUser = req.getParameter("prenom");
                String nomUser = req.getParameter("nom");
                String emailUser = req.getParameter("email");
                String mdpUser = req.getParameter("mdp");
                Utilisateurs user = new Utilisateurs();
                user.setPrenomUser(prenomUser);
                user.setNomUser(nomUser);
                user.setEmailUser(emailUser);
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                // Hacher le mot de passe
                String hashedPassword = passwordEncoder.encode(mdpUser);
                user.setMdpUser(hashedPassword);
                LocalDate localDate = LocalDate.now();
                Date sqlDate = Date.valueOf(localDate);
                user.setDatedeCreation(sqlDate);
                user.setProfilUser(2);//Profil proprio (2)
                entityManager.persist(user);
                //Valider la transaction
                transaction.commit();
                resp.sendRedirect("admin");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
