<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body>
<%
    Integer profil = (Integer) session.getAttribute("profil");
    System.out.println(profil);
%>

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
                <% if (profil==7) {%>
                <li class="nav-item">
                    <a class="nav-link" href="immeubles">Gestion Immeubles</a>
                </li>
                <% } %>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Options
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="logout">Deconnexion</a></li>

                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" ></script>
</html>
