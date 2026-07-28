package org.example.healthcare.Pdf;


import lombok.RequiredArgsConstructor;
import org.example.healthcare.Entity.DossierMedical;
import org.example.healthcare.Entity.RendezVous;
import org.example.healthcare.Repository.DossierMedicalRepository;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/api/pdf")
@RequiredArgsConstructor
public class PdfController {

    private final DossierMedicalRepository dossierMedicalRepository;
    private final PdfGeneratorService pdfGeneratorService;

    @GetMapping("/dossier/{id}")
    public ResponseEntity<InputStreamResource> downloadDossierMedicalPdf(@PathVariable Long id) {
        DossierMedical dossier =
                dossierMedicalRepository.findById(id).orElseThrow();
        return buildPdfResponse(
                () -> pdfGeneratorService.generateDossierMedicalPdf(dossier),
                "dossier_medical_" + id + ".pdf");
    }

    @GetMapping("/rendezvous-list")
    public ResponseEntity<InputStreamResource> downloadRendezVousListPdf(@RequestParam String patientName) {

        return buildPdfResponse(
                () -> pdfGeneratorService.generateRendezVousListPdf(patientName),
                "rendezvous_list_" + patientName + ".pdf"
        );
    }

    @GetMapping("/rapport")
    public ResponseEntity<InputStreamResource> downloadRapport(
            @RequestParam String titre) {

        return buildPdfResponse(
                () -> pdfGeneratorService.generateRapportPdf(titre),
                "rapport.pdf");
    }

    private ResponseEntity<InputStreamResource> buildPdfResponse(PdfGenerator generator, String filename) {
        ByteArrayInputStream bis = generator.generate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=" + filename);
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @FunctionalInterface
    private interface PdfGenerator {
        ByteArrayInputStream generate();
    }
}
