package sn.dev.gestion_location_immeubles.controllers;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sn.dev.gestion_location_immeubles.DAO.Contratsdelocations;
import sn.dev.gestion_location_immeubles.DAO.Immeubles;
import sn.dev.gestion_location_immeubles.DAO.Unitesdelocations;
import sn.dev.gestion_location_immeubles.DAO.Utilisateurs;
import sn.dev.gestion_location_immeubles.services.ContratLocationMetier;

import java.io.IOException;
import java.net.http.HttpClient;
import java.util.List;

@WebServlet(name = "imprimer",urlPatterns = {"*.im"})
public class ContratLocationServlet extends HttpServlet {
    private ContratLocationMetier contratLocationMetier;

    @Override
    public void init(ServletConfig config) throws ServletException {
        contratLocationMetier=new ContratLocationMetier();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
        int idUnite = 0;
        int idLocataire = 0;
        if (req.getParameter("idUnite") != null && req.getParameter("idLocataire")!=null) {
            idUnite = Integer.parseInt(req.getParameter("idUnite"));
            idLocataire = Integer.parseInt(req.getParameter("idLocataire"));
        }
        List<Object[]> contrat = contratLocationMetier.getContratLocationInfo(idUnite,idLocataire);
            resp.setContentType("application/pdf");
            resp.setHeader("Content-Disposition", "inline; filename=contrat_location_"+idLocataire*idUnite+".pdf");

            // Créer un PDF
            PdfWriter writer = new PdfWriter(resp.getOutputStream());
            com.itextpdf.kernel.pdf.PdfDocument pdf = new com.itextpdf.kernel.pdf.PdfDocument(writer);
            Document document = new Document(pdf);
            document.setMargins(20, 20, 20, 20);

            // Font and colors
         PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
PdfFont regular = PdfFontFactory.createFont(StandardFonts.HELVETICA);
            Color headerColor = new DeviceRgb(63, 169, 219);

            // Title
            Paragraph title = new Paragraph("Contrat de Location")
                    .setFont(bold)
                    .setFontSize(20)
                    .setFontColor(headerColor)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(20);
            document.add(title);

            for (Object[] row : contrat) {
                Contratsdelocations infoContrat = (Contratsdelocations) row[0];
                Unitesdelocations infoUn = (Unitesdelocations) row[1];
                Utilisateurs locataire = (Utilisateurs) row[2];
                Utilisateurs bailleurs = (Utilisateurs) row[3];
                Immeubles immeuble = (Immeubles) row[4];

                Table table = new Table(UnitValue.createPercentArray(new float[]{1, 2}))
                        .useAllAvailableWidth()
                        .setMarginBottom(20);

                table.addCell(new Paragraph("Numero du contrat :").setFont(bold));
                table.addCell(new Paragraph(String.valueOf(infoContrat.getIdContrat()*infoContrat.getIdLocataire())).setFont(regular));

                table.addCell(new Paragraph("Nom de l'unité :").setFont(bold));
                table.addCell(new Paragraph(infoUn.getNomUnite()).setFont(regular));

                table.addCell(new Paragraph("Locataire :").setFont(bold));
                table.addCell(new Paragraph(locataire.getPrenomUser() + " " + locataire.getNomUser() + " (" + locataire.getEmailUser() + ")").setFont(regular));

                table.addCell(new Paragraph("Bailleur :").setFont(bold));
                table.addCell(new Paragraph(bailleurs.getPrenomUser() + " " + bailleurs.getNomUser()).setFont(regular));

                table.addCell(new Paragraph("Immeuble :").setFont(bold));
                table.addCell(new Paragraph(immeuble.getNomImmeuble()).setFont(regular));

                table.addCell(new Paragraph("Adresse de l'immeuble :").setFont(bold));
                table.addCell(new Paragraph(immeuble.getAdresseImmeuble()).setFont(regular));

                table.addCell(new Paragraph("Nombre de pièces :").setFont(bold));
                table.addCell(new Paragraph(String.valueOf(infoUn.getNombrePieces())).setFont(regular));

                table.addCell(new Paragraph("Superficie :").setFont(bold));
                table.addCell(new Paragraph(String.valueOf(infoUn.getSuperficie())+" M2 ").setFont(regular));

                table.addCell(new Paragraph("Prix Location :").setFont(bold));
                table.addCell(new Paragraph(String.valueOf(infoUn.getPrixLoyer())+" FCFA ").setFont(regular));

                table.addCell(new Paragraph("Date de création du contrat :").setFont(bold));
                table.addCell(new Paragraph(String.valueOf(infoContrat.getDatedeCreation())).setFont(regular));

                document.add(table);

                // Adding some fictional rental conditions
                Paragraph conditionsHeader = new Paragraph("Conditions de Location")
                        .setFont(bold)
                        .setFontSize(16)
                        .setFontColor(headerColor)
                        .setTextAlignment(TextAlignment.LEFT)
                        .setMarginBottom(10);
                document.add(conditionsHeader);

                Paragraph conditions = new Paragraph()
                        .add("1. Le loyer est payable mensuellement à l'avance.\n")
                        .add("2. Aucune sous-location n'est autorisée sans l'accord écrit du bailleur.\n")
                        .add("3. Les animaux domestiques ne sont pas autorisés.\n")
                        .add("4. Les réparations nécessaires à la propriété seront à la charge du bailleur, sauf en cas de négligence de la part du locataire.\n")
                        .add("5. Le locataire est responsable de maintenir l'unité en bon état de propreté et de réparation.\n")
                        .setFont(regular)
                        .setMarginBottom(20);
                document.add(conditions);
            }

            // Fermer le document
            document.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
