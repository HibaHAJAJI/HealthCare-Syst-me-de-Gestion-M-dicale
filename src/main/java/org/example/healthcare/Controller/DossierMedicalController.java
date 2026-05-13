package org.example.healthcare.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.healthcare.Dto.DossierMedicalDto;
import org.example.healthcare.Service.DossierMedicalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/dossier-medicaux")
public class DossierMedicalController {

    private final DossierMedicalService service;

    @GetMapping("/{id}")
    public ResponseEntity<DossierMedicalDto>  findDossierMedicalById(@PathVariable Long id){
        return new ResponseEntity<>(service.getDossierMedical(id),HttpStatus.OK) ;
    }

    @PostMapping
    public ResponseEntity<DossierMedicalDto>  saveDossierMedical(@Valid @RequestBody DossierMedicalDto dto){
        return new ResponseEntity<>(service.addDossierMedical(dto), HttpStatus.CREATED) ;
    }

    @PatchMapping("/{id}/diagnostic")
    public ResponseEntity<DossierMedicalDto>  saveDiagnostic(@PathVariable Long id,@Valid @RequestParam String diagnostic){
     return new  ResponseEntity<>(service.addDiagnostic(id,diagnostic),HttpStatus.CREATED) ;
    }

    @PatchMapping("/{id}/observation")
    public ResponseEntity<DossierMedicalDto>  saveObservation(@PathVariable Long id,@RequestParam String observation){
        return new ResponseEntity<>(service.addObservation(id,observation),HttpStatus.CREATED) ;
    }
}
