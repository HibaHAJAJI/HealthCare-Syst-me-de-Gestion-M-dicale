package org.example.healthcare.Controller;

import lombok.AllArgsConstructor;
import org.example.healthcare.Dto.DossierMedicalDto;
import org.example.healthcare.Service.DossierMedicalService;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/dossier-medicaux")
public class DossierMedicalController {

    private final DossierMedicalService service;

    @GetMapping("/{id}")
    public DossierMedicalDto findDossierMedicalById(@PathVariable Long id){
        return service.getDossierMedical(id);
    }
    @PostMapping
    public DossierMedicalDto saveDossierMedical(@RequestBody DossierMedicalDto dto){
        return service.addDossierMedical(dto);
    }

    @PatchMapping("/{id}/diagnostic")
    public DossierMedicalDto saveDiagnostic(@PathVariable Long id, @RequestParam String diagnostic){
     return service.addDiagnostic(id,diagnostic);
    }

    @PatchMapping("/{id}/observation")
    public DossierMedicalDto saveObservation(@PathVariable Long id,@RequestParam String observation){
        return service.addObservation(id,observation);
    }
}
