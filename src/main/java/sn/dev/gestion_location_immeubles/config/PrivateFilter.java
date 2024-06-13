package sn.dev.gestion_location_immeubles.config;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class PrivateFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialisation du filtre si nécessaire
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        String username = (String) session.getAttribute("username");
        String chemin = req.getServletPath();
        String method = req.getMethod();

        if (username != null
                || chemin.equals("/")
                || chemin.equals("/login")
                || chemin.equals("/logout")
                || chemin.equals("/index.jsp") && method.equalsIgnoreCase("POST")
                || chemin.startsWith("/public")) {
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect(req.getContextPath() + "/login"); // Redirige vers la page de login
        }
    }

    @Override
    public void destroy() {
        // Nettoyage du filtre si nécessaire
    }
}
