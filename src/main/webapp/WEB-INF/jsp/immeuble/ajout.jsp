<%@ page import="sn.dev.gestion_location_immeubles.services.UserMetier" %>
<%@ page import="java.util.List" %>
<%@ page import="sn.dev.gestion_location_immeubles.DAO.Utilisateurs" %>
<%@ page import="sn.dev.gestion_location_immeubles.services.ImmeubleMetier" %>
<%@ page import="sn.dev.gestion_location_immeubles.DAO.Immeubles" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ImmeubleMetier immeubleMetier = new ImmeubleMetier();
    List<Immeubles> immeubles=immeubleMetier.getImmeubles();
    Integer profil = (Integer) session.getAttribute("profil");
%>
<!DOCTYPE html>
<html lang="en" class="antialiased">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>GLI-PRO </title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <link rel="stylesheet" text="text/css" href="<%=request.getContextPath()%>/public/fonts/material-icon/css/material-design-iconic-font.min.css">
    <!-- Main css -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/public/css/style.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>

<body class="bg-gray-100 text-gray-900 tracking-wider leading-normal">
<nav class="navbar navbar-light navbar-expand-lg bg-body-tertiary mb-4 " style="background-color: #e3f2fd;">
    <div class="container-fluid">
        <a class="navbar-brand" href="welcome">GLI-PRO</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="admin">Liste utilisateurs</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="immeubles">Gestion Immeubles</a>
                </li>
            </ul>
            <ul class="navbar-nav ms-auto">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="fas fa-user-circle fa-2x"></i>
                        <% String prenomNomUser = session.getAttribute("prenomNomUser").toString(); %>
                        <%= prenomNomUser %>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end">
                        <li><a class="dropdown-item" href="profil.us?idUser=<%= (Integer) session.getAttribute("idProprio") %>">Profil</a></li>
                        <li><a class="dropdown-item" href="logout">Deconnexion</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>

<section class="signup" style="margin-top: 35px">
    <div class="container">
        <div class="signup-content">
            <div class="signup-form">
                <h2 class="form-title">Création immeuble</h2>
                <form method="POST" action="immeubles" class="register-form" id="register-form">
                    <div class="form-group">
                        <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                        <input type="text" name="nomImmeuble" id="name" placeholder="Nom"/>
                    </div>
                    <div class="form-group">
                        <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                        <input type="text" name="adresseImmeuble" id="" placeholder="Adresse"/>
                    </div>
                    <% if (profil==7){%>
                    <div class="form-group">
                        <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                        <select class="form-select" name="idProprietaire" >
                            <option value="0">Choisir un proprietaire</option>
                            <%
                            UserMetier userMetier = new UserMetier();
                            List<Utilisateurs> utilisateurs = userMetier.getProprietaire();
                            for (int i = 0; i < utilisateurs.size(); i++) {
                            %>
                            <option value="<%= utilisateurs.get(i).getIdUser() %>"><%= utilisateurs.get(i).getPrenomUser() +" "+ utilisateurs.get(i).getNomUser() %></option>
                            <%
                            }
                            %>
                        </select>
                    </div>
                    <% } else { %>
                    <input hidden="hidden" value="<%= (Integer) session.getAttribute("idProprio") %>" type="text" name="idProprietaire" id="name" placeholder=""/>
                    <% } %>
                    <div class="form-group">
                        <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                        <input type="text" name="description" id="email" placeholder="Description"/>
                    </div>
                    <div class="form-group form-button">
                        <input type="submit" name="enregistrer" class="form-submit" value="Enregistrer"/>
                    </div>
                </form>
            </div>
            <div class="signup-image">
                <figure><img src="<%=request.getContextPath()%>/public/images/signup-image.jpg" alt="immeuble image"></figure>
                <a href="immeubles" class="signup-image-link">Liste des immeubles</a>
            </div>
        </div>
    </div>
</section>

<!-- jQuery -->
<script src="<%=request.getContextPath()%>/public/vendor/jquery/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/public/js/main.js"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" ></script>
</body>

</html>