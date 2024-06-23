<%@ page import="sn.dev.gestion_location_immeubles.DAO.Immeubles" %>
<%@ page import="sn.dev.gestion_location_immeubles.services.UserMetier" %>
<%@ page import="sn.dev.gestion_location_immeubles.DAO.Utilisateurs" %>
<%@ page import="java.util.List" %>
<%@ page import="sn.dev.gestion_location_immeubles.controllers.UniteLocServlet" %>
<%@ page import="sn.dev.gestion_location_immeubles.services.UniteLocMetier" %>
<%@ page import="sn.dev.gestion_location_immeubles.DAO.Unitesdelocations" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="sn.dev.gestion_location_immeubles.services.OffreMetier" %><%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 18/06/2024
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
//    Immeubles immeuble = (Immeubles) session.getAttribute("immeuble");
    Integer profil = (Integer) session.getAttribute("profil");
    int idImmeuble= (int) session.getAttribute("idImmeuble");
    UniteLocMetier metierUniteLoc = new UniteLocMetier();
    List<Unitesdelocations> unitesloc = metierUniteLoc.getUnitesdelocationsByIdImmeuble(idImmeuble);
    OffreMetier offreMetier=new OffreMetier();
    %>
<html>
<head>
    <title>Parametrage</title>
    <link href="https://unpkg.com/tailwindcss@2.2.19/dist/tailwind.min.css" rel=" stylesheet">
    <!--Regular Datatables CSS-->
    <link href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css" rel="stylesheet">
    <!--Responsive Extension Datatables CSS-->
    <link href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.dataTables.min.css" rel="stylesheet">
    <style>
        /*Overrides for Tailwind CSS */

        /*Form fields*/
        .dataTables_wrapper select,
        .dataTables_wrapper .dataTables_filter input {
            color: #4a5568;
            /*text-gray-700*/
            padding-left: 1rem;
            /*pl-4*/
            padding-right: 1rem;
            /*pl-4*/
            padding-top: .5rem;
            /*pl-2*/
            padding-bottom: .5rem;
            /*pl-2*/
            line-height: 1.25;
            /*leading-tight*/
            border-width: 2px;
            /*border-2*/
            border-radius: .25rem;
            border-color: #edf2f7;
            /*border-gray-200*/
            background-color: #edf2f7;
            /*bg-gray-200*/
        }

        /*Row Hover*/
        table.dataTable.hover tbody tr:hover,
        table.dataTable.display tbody tr:hover {
            background-color: #ebf4ff;
            /*bg-indigo-100*/
        }

        /*Pagination Buttons*/
        .dataTables_wrapper .dataTables_paginate .paginate_button {
            font-weight: 700;
            /*font-bold*/
            border-radius: .25rem;
            /*rounded*/
            border: 1px solid transparent;
            /*border border-transparent*/
        }

        /*Pagination Buttons - Current selected */
        .dataTables_wrapper .dataTables_paginate .paginate_button.current {
            color: #fff !important;
            /*text-white*/
            box-shadow: 0 1px 3px 0 rgba(0, 0, 0, .1), 0 1px 2px 0 rgba(0, 0, 0, .06);
            /*shadow*/
            font-weight: 700;
            /*font-bold*/
            border-radius: .25rem;
            /*rounded*/
            background: #667eea !important;
            /*bg-indigo-500*/
            border: 1px solid transparent;
            /*border border-transparent*/
        }

        /*Pagination Buttons - Hover */
        .dataTables_wrapper .dataTables_paginate .paginate_button:hover {
            color: #fff !important;
            /*text-white*/
            box-shadow: 0 1px 3px 0 rgba(0, 0, 0, .1), 0 1px 2px 0 rgba(0, 0, 0, .06);
            /*shadow*/
            font-weight: 700;
            /*font-bold*/
            border-radius: .25rem;
            /*rounded*/
            background: #667eea !important;
            /*bg-indigo-500*/
            border: 1px solid transparent;
            /*border border-transparent*/
        }

        /*Add padding to bottom border */
        table.dataTable.no-footer {
            border-bottom: 1px solid #e2e8f0;
            /*border-b-1 border-gray-300*/
            margin-top: 0.75em;
            margin-bottom: 0.75em;
        }

        /*Change colour of responsive icon*/
        table.dataTable.dtr-inline.collapsed>tbody>tr>td:first-child:before,
        table.dataTable.dtr-inline.collapsed>tbody>tr>th:first-child:before {
            background-color: #667eea !important;
            /*bg-indigo-500*/
        }
    </style>
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
<!--Container-->
<div class="container w-full md:w-4/5 xl:w-3/5  mx-auto px-2 mt-3">
    <!--Card-->
    <div id='recipients' class="p-8 mt-6 lg:mt-0 rounded shadow bg-white">
    <h2 class="font-bold text-2xl text-gray-800 mb-4">Liste des unités de locations</h2>
        <table id="example" class="stripe hover" style="width:100%; padding-top: 1em;  padding-bottom: 1em;">
            <thead>
            <tr>
                <th data-priority="1">Nom Unite</th>
                <th data-priority="2">Nombre de pieces</th>
                <th data-priority="3">Superficie</th>
                <th data-priority="4">Prix Loyer</th>
                <th data-priority="5">Date de création</th>
                <th data-priority="6">Actions</th>
            </tr>
            </thead>
            <tbody>
            <%
                if (!unitesloc.isEmpty()) {
                for (Unitesdelocations ul : unitesloc)
                {
            %>
            <tr>
                <td><%= ul.getNomUnite() %></td>
                <td><%= ul.getNombrePieces() %></td>
                <td><%= ul.getSuperficie() %></td>
                <td><%= ul.getPrixLoyer() %></td>
                <td><%= ul.getDatedeCreation() %></td>
                <td>
                    <a class="btn btn-primary" href="uniteloc.od?action=update&id=<%= ul.getIdUnite() %>">Modifier</a>
                    <a class="btn btn-danger" href="uniteloc.od?action=delete&id=<%= ul.getIdUnite() %>">Supprimer</a>
                    <a class="btn btn-info" href="">Infos Locataire</a>
                    <input hidden="hidden" value=" <%  boolean ok = offreMetier.verifOffreActive(ul.getIdUnite()); %>">
                    <a class="btn <%= ok?"btn-warning":"btn-success" %>" href="<%= ok?"puboffre.fa?action=depub&idUnite="+ul.getIdUnite():"puboffre.fa?action=pub&idUnite="+ul.getIdUnite() %>"><%= ok?"Depublier":"Publier" %></a>

                </td>
            </tr>
            <%
                }
                }else{
                    %>
            <tr>
                <td colspan="6" class="text-center">Aucune donnée disponible</td>
            </tr>
            <%
                }
            %>
            </tbody>

        </table>
        <a href="uniteloc.od?idImmeuble=<%= idImmeuble %>" class="btn btn-success" >Ajouter une nouvelle unité</a>
    </div>
    <!--/Card-->
</div>
<!--/container-->

</body>
<!--Datatables -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<!--Datatables -->
<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.2.3/js/dataTables.responsive.min.js"></script>
<script>
    $(document).ready(function() {

        var table = $('#example').DataTable({
            responsive: true
        })
            .columns.adjust()
            .responsive.recalc();
    });
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" ></script>
</html>
