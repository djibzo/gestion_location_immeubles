package sn.dev.gestion_location_immeubles.controllers;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sn.dev.gestion_location_immeubles.services.ContratLocationMetier;
import sn.dev.gestion_location_immeubles.services.DemandeMetier;

import java.io.IOException;

@WebServlet(name ="demande",urlPatterns = {"/demande","*.op"})
public class DemandeServlet extends HttpServlet {
    private DemandeMetier metier;
    private ContratLocationMetier contratMetier;
    @Override
    public void init(ServletConfig config) throws ServletException {
        metier=new DemandeMetier();
        contratMetier=new ContratLocationMetier();
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
        String action = req.getParameter("action");
        int idDemande = 0;
        if (req.getParameter("idDemande") != null) {
            idDemande = Integer.parseInt(req.getParameter("idDemande"));
        }
        if(action!=null && action.equals("dmsbypro")){
            req.getRequestDispatcher("WEB-INF/jsp/demandes/demandePostulesforPro.jsp").forward(req, resp);
        } else if (action!=null  && action.equals("accepter") ) {
            metier.acceptDemande(idDemande);
            contratMetier.addContratLocation(metier.getIdUniteByDemande(idDemande), metier.getIdUserByDemande(idDemande));
            req.getRequestDispatcher("WEB-INF/jsp/demandes/demandePostulesforPro.jsp").forward(req, resp);
        } else if (action!=null  && action.equals("rejeter") ) {
            metier.rejectDemande(idDemande);
            req.getRequestDispatcher("WEB-INF/jsp/demandes/demandePostulesforPro.jsp").forward(req, resp);
        } else if (action!=null && action.equals("cancel")) {
            int idDemandeII=Integer.parseInt(req.getParameter("idDemandeII"));
            metier.annulerDemande(idDemandeII);
            req.getRequestDispatcher("WEB-INF/jsp/demandes/list.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("WEB-INF/jsp/demandes/list.jsp").forward(req, resp);
    }
}
