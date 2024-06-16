package sn.dev.gestion_location_immeubles.controllers;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sn.dev.gestion_location_immeubles.DAO.Utilisateurs;
import sn.dev.gestion_location_immeubles.services.UserMetier;


import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(name = "login",urlPatterns = {"/login","/"})
public class LoginServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("connexion.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String emailUser=req.getParameter("emailUser");
        String mdpUser=req.getParameter("mdpUser");
        Utilisateurs user=new UserMetier().findByEmail(emailUser);
        if(user!=null){
            String storedHashedPassword = user.getMdpUser();
            // Créer un encodeur BCrypt
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            // Vérifier si le mot de passe saisi correspond au mot de passe haché stocké
            if (passwordEncoder.matches(mdpUser, storedHashedPassword)) {
                req.getSession().setAttribute("username",emailUser);
                req.getSession().setAttribute("profil",user.getProfilUser());
                resp.sendRedirect("welcome");
            } else {
                System.out.println("Login ou mot de passe incorrect");
                resp.sendRedirect("login");
            }
        } else {
            System.out.println("User not found");
            resp.sendRedirect("login");
        }
    }
}