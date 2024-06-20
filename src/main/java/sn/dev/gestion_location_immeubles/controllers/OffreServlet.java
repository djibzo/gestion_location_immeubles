package sn.dev.gestion_location_immeubles.controllers;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sn.dev.gestion_location_immeubles.DAO.Offres;
import sn.dev.gestion_location_immeubles.services.OffreMetier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "offres",urlPatterns = {"/offre","*.fa"})
public class OffreServlet extends HttpServlet {
    private OffreMetier metier;

    @Override
    public void init(ServletConfig config) throws ServletException {
        metier=new OffreMetier();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action=req.getParameter("action");
        if (action!=null && action.equals("pub")){
            int idUnite=Integer.parseInt(req.getParameter("idUnite"));
            metier.publierOffreByIdUnite(idUnite);
            req.getRequestDispatcher("WEB-INF/jsp/immeuble/list.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("WEB-INF/jsp/offres/list.jsp").forward(req, resp);
    }
}
