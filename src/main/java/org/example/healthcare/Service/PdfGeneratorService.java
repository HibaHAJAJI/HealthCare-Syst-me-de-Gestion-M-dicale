package org.example.healthcare.Service;


import org.example.healthcare.Entity.DossierMedical;
import org.example.healthcare.Entity.RendezVous;

import java.io.ByteArrayInputStream;
import java.util.List;


public interface PdfGeneratorService {

    ByteArrayInputStream generateDossierMedicalPdf(DossierMedical dossier);
    ByteArrayInputStream generateRendezVousListPdf(List<RendezVous> rendezVous, String patientName);
    ByteArrayInputStream generateRapportPdf(String titre);
}
