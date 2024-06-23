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

    /**
 * Gère les requêtes HTTP GET pour le servlet offres.
 * Il prend en charge deux actions : "pub" et "depub".
 * Si l'action est "pub", il publie une offre pour une unité spécifique en appelant la méthode {@link OffreMetier#publierOffreByIdUnite(int)}.
 * Si l'action est "depub", il dépublie une offre pour une unité spécifique en appelant la méthode {@link OffreMetier#depublierOffreByIdUnite(int)}.
 * Après avoir traité l'action, il transfère la requête vers la page JSP appropriée.
 *
 * @param req  L'objet {@link HttpServletRequest} qui contient les paramètres de requête du client
 * @param resp L'objet {@link HttpServletResponse} qui contient la réponse que le servlet envoie au client
 * @throws ServletException Si la requête pour la méthode GET n'est pas gérée par ce servlet
 * @throws IOException      Si une erreur d'entrée ou de sortie se produit pendant que le servlet gère la requête GET
 */
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String action = req.getParameter("action");
    if (action != null && action.equals("pub")) {
        int idUnite = Integer.parseInt(req.getParameter("idUnite"));
        metier.publierOffreByIdUnite(idUnite);
        req.getRequestDispatcher("WEB-INF/jsp/immeuble/list.jsp").forward(req, resp);
    } else if (action != null && action.equals("depub")) {
        int idUnite = Integer.parseInt(req.getParameter("idUnite"));
        metier.depublierOffreByIdUnite(idUnite);
        req.getRequestDispatcher("WEB-INF/jsp/immeuble/list.jsp").forward(req, resp);
    }
    req.getRequestDispatcher("WEB-INF/jsp/offres/list.jsp").forward(req, resp);
}
}
