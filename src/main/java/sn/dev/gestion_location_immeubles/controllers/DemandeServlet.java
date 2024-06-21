package sn.dev.gestion_location_immeubles.controllers;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sn.dev.gestion_location_immeubles.services.DemandeMetier;

import java.io.IOException;

@WebServlet(name ="demande",urlPatterns = {"/demande"})
public class DemandeServlet extends HttpServlet {
    private DemandeMetier metier;

    @Override
    public void init(ServletConfig config) throws ServletException {
        metier=new DemandeMetier();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idOffre=Integer.parseInt(req.getParameter("idOffre"));
        int idUser= (int) req.getSession().getAttribute("idProprio");//idProprio peut etre un locataire
        metier.addDemande(idUser,idOffre);
        resp.sendRedirect("offre");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/demandes/list.jsp").forward(req, resp);
    }
}
