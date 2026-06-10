package org.example.healthcare.Pdf;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;
import org.example.healthcare.Entity.DossierMedical;
import org.example.healthcare.Entity.RendezVous;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class PdfGeneratorService {

    public ByteArrayInputStream generateDossierMedicalPdf(DossierMedical dossier){
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD,20, Color.DARK_GRAY);
            Paragraph title =new Paragraph("DOSSIER MEDICAL #"  + dossier.getId(),titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20);
            document.add(title);

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10);

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD,12,Color.white);
            PdfPCell h1 = new PdfPCell(new Phrase("Champs", headFont));
            h1.setBackgroundColor(new Color(0,102,204));
            h1.setPadding(8);

            PdfPCell h2 = new PdfPCell(new Phrase("Détails", headFont));
            h2.setBackgroundColor(new Color(0,102,204));
            h2.setPadding(8);

            table.addCell(h1);
            table.addCell(h2);

            table.addCell("Patient(Username");
            table.addCell(dossier.getPatient() != null ? dossier.getPatient().getUsername() : "N/A");

            table.addCell("Diagnostic");
            table.addCell(dossier.getDiagnostic());

            table.addCell("Observation");
            table.addCell(dossier.getObservation());

            table.addCell("Date de Création");
            table.addCell(dossier.getDateCreation().toString());

            document.add(table);
            document.close();
        }catch (DocumentException ex){
            ex.printStackTrace();
        }
        return new ByteArrayInputStream(out.toByteArray());
    }

    public ByteArrayInputStream generateRendezVousListPdf(List<RendezVous> rendezVous, String patientName){
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document,out);
            document.open();

            Font titleFont =FontFactory.getFont(FontFactory.HELVETICA_BOLD,18,Color.DARK_GRAY);
            Paragraph title = new Paragraph("Liste des Rendez-vous  -" +patientName,titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20);
            document.add(title);

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);

            String[] headers = {"ID","Date & Heure","Médecin","Statut"};
            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD,11,Color.white);

            for(String header : headers){
                PdfPCell cell = new PdfPCell(new Phrase(header,headFont));
                cell.setBackgroundColor(new Color(44,62,80));
                cell.setPadding(6);
                table.addCell(cell);
            }

            for(RendezVous rv : rendezVous){
                table.addCell(rv.getId().toString());
                table.addCell(rv.getDateRendezVous().toString());
                table.addCell(rv.getMedecin() !=null ? "Dr. " + rv.getMedecin().getUsername() : "N/A");
                table.addCell(rv.getStatut().toString());
            }

            document.add(table);
            document.close();
        }catch (DocumentException ex){
            ex.printStackTrace();
        }
        return new ByteArrayInputStream(out.toByteArray());
    }



    public ByteArrayInputStream generateRapportPdf(String titre) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            document.add(new Paragraph(titre));
            document.add(new Paragraph("Rapport généré par HealthCare+"));

            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
