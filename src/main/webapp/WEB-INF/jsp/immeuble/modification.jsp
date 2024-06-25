<%@ page import="sn.dev.gestion_location_immeubles.DAO.Immeubles" %>
<%@ page import="sn.dev.gestion_location_immeubles.services.UserMetier" %>
<%@ page import="sn.dev.gestion_location_immeubles.DAO.Utilisateurs" %>
<%@ page import="java.util.List" %>
<%@ page import="sn.dev.gestion_location_immeubles.services.UniteLocMetier" %>
<%@ page import="sn.dev.gestion_location_immeubles.services.ImmeubleMetier" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
//    Immeubles immeuble = (Immeubles) request.getAttribute("immeuble");
    int idImmeuble=(int)session.getAttribute("idImmeuble");
    Integer profil = (Integer) session.getAttribute("profil");
    ImmeubleMetier metierIm=new ImmeubleMetier();
    Immeubles immeuble=metierIm.getImmeublesById(idImmeuble);

%>
<html>
<head>
    <title>Modification immeuble</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body>
<nav class="navbar navbar-light navbar-expand-lg bg-body-tertiary " style="background-color: #e3f2fd;">
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
                <% if (profil==7) {%>
                <li class="nav-item">
                    <a class="nav-link" href="admin">Liste utilisateurs</a>
                </li>
                <% } %>
                <% if (profil==7 || profil==2) {%>
                <li class="nav-item">
                    <a class="nav-link" href="immeubles">Gestion Immeubles</a>
                </li>
                <% } %>
                <% if (profil==2) {%>
                <li class="nav-item">
                    <a class="nav-link" href="demande.op?action=dmsbypro">Demandes postulées</a>
                </li>
                <% } %>
            </ul>
            <ul class="navbar-nav ms-auto">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="fas fa-user-circle fa-2x"></i>
                        <% String prenomNomUser = session.getAttribute("prenomNomUser").toString(); %>
                        <%= prenomNomUser %>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end">
                        <li><a class="dropdown-item" href="logout">Deconnexion</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container mt-5 col-md-5 col-xm-12 col-sm-6 col-md-offset-3">
    <div class="card">
        <div class="card-header center">
           Modifcation
        </div>
        <div class="card-body">
            <form action="immeubles.do?action=supdate" method="post">
                <input type="hidden" name="idImmeuble" value="<%= immeuble.getIdImmeuble() %>">
                <div class="form-group">
                    <label class="control-label">Nom Immeuble </label>
                    <input type="text" class="form-control" name="nomImmeuble" value="<%= immeuble.getNomImmeuble() %>">
                </div>
                <br>
                <div class="form-group">
                    <label class="control-label">Adresse </label>
                    <input type="text" class="form-control" name="adresseImmeuble" value="<%= immeuble.getAdresseImmeuble() %>">
                </div>
                <br>
                <div class="form-group">
                    <label ><i class="zmdi zmdi-account material-icons-name"></i></label>
                    <select class="form-select" name="idProprietaire" >
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
                <div class="form-group">
                    <label class="control-label">Description </label>
                    <input type="text" class="form-control" name="description" value="<%= immeuble.getDescription() %>">
                </div>
                <br>
                <div>
                    <button type="submit" class="btn btn-outline-success">Modifier</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<!--Datatables -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" ></script>
</html>
