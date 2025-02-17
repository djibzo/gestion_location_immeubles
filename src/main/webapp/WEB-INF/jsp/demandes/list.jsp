<%@ page import="sn.dev.gestion_location_immeubles.services.UserMetier" %>
<%@ page import="java.util.List" %>
<%@ page import="sn.dev.gestion_location_immeubles.services.ImmeubleMetier" %>
<%@ page import="sn.dev.gestion_location_immeubles.services.OffreMetier" %>
<%@ page import="sn.dev.gestion_location_immeubles.services.DemandeMetier" %>
<%@ page import="sn.dev.gestion_location_immeubles.DAO.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Integer profil = (Integer) session.getAttribute("profil");
    DemandeMetier demandeMetier=new DemandeMetier();
    List<Object[]> demandes=demandeMetier.getDemandesByidUser((Integer) session.getAttribute("idProprio"));
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
    <link href="https://unpkg.com/tailwindcss@2.2.19/dist/tailwind.min.css" rel=" stylesheet">
    <!--Replace with your tailwind.css once created-->


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
                <% if (profil==7 || profil==3) {%>
                <li class="nav-item">
                    <a class="nav-link" href="offre">Offres disponibles</a>
                </li>
                <% } %>
                <% if (profil==3) {%>
                <li class="nav-item">
                    <a class="nav-link" href="demande">Mes demandes</a>
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
                        <li><a class="dropdown-item" href="profil.us?idUser=<%= (Integer) session.getAttribute("idProprio") %>">Profil</a></li>
                        <li><a class="dropdown-item" href="logout">Deconnexion</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!--Container-->
<div class="container w-full md:w-4/5 xl:w-3/5  mx-auto px-2">
    <!--Card-->
    <div id='recipients' class="p-8 mt-6 lg:mt-0 rounded shadow bg-white">

        <table id="example" class="stripe hover" style="width:100%; padding-top: 1em;  padding-bottom: 1em;">
            <thead>
            <tr>
                <th data-priority="1">Nom</th>
                <th data-priority="2">Nombre de pieces</th>
                <th data-priority="3">Superficie</th>
                <th data-priority="4">Prix Loyer</th>
                <th data-priority="5">Nom Immeuble</th>
                <th data-priority="6">Adresse Immeuble</th>
                <th data-priority="5">Statut demande</th>
                <th data-priority="6">Actions</th>
            </tr>
            </thead>
            <tbody>
            <%
                for (Object[] row : demandes)
                {
                    Unitesdelocations unite = (Unitesdelocations) row[2];
                    Demandes demande = (Demandes) row[0];
                    Offres offre = (Offres) row[1];
                    Immeubles immeuble =(Immeubles) row[3];
            %>
            <tr>
                <td><%= unite.getNomUnite() %></td>
                <td><%= unite.getNombrePieces() %></td>
                <td><%= unite.getSuperficie() %></td>
                <td><%= unite.getPrixLoyer()%></td>
                <td><%= immeuble.getNomImmeuble()%></td>
                <td><%= immeuble.getAdresseImmeuble()%></td>
                <% int etat=demande.getEtat(); %>
                <td>
                <%
                    if(etat==0)
                    {
                %>
                    <span class="badge bg-warning text-dark">En traitement</span>
                <%
                    }
                    else if(etat==1)
                    {
                %>
                    <span class="badge bg-success text-white">Acceptée</span>
                <%
                    }
                    else if(etat==-1)
                    {
                %>
                    <span class="badge bg-danger text-white">Rejetée</span>
                <%
                    }
                %>
            </td>
                <td>
                        <input hidden="hidden" name="idOffre" value="<%= offre.getIdOffre() %>">
                        <% if (etat==0 || etat==-1){%>
                        <a href="cancel.op?action=cancel&idDemandeII=<%= demande.getIdDemande() %>"  type="submit"  class="btn btn-sm btn-danger <%= (profil==7||etat!=0)?"btn disabled":"" %> " >Annuler la demande</a>
                        <% } %>
                        <a href="imprime.im?idUnite=<%= unite.getIdUnite() %>&idLocataire=<%= (Integer) session.getAttribute("idProprio") %>" type="submit" <%= demande.getEtat()==1?"":"hidden" %>  class="btn btn-sm btn-info" >Imprimer contrat</a>
                </td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
    <!--/Card-->
</div>
<!--/container-->

<!-- jQuery -->
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
</body>

</html>