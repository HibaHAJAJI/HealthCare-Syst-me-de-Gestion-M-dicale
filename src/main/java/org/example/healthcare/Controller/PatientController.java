package org.example.healthcare.Controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.healthcare.Dto.PatientDto;
import org.example.healthcare.Entity.Patient;
import org.example.healthcare.Service.PatientService;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Créer un nouveau Patient")
    public ResponseEntity<PatientDto>  savePatient(@Valid @RequestBody PatientDto dto){
        return new ResponseEntity<>( patientService.addPatient(dto), HttpStatus.CREATED);
    }

    @GetMapping
    //
    //@PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Lister tous les patients avec pagination")
    public ResponseEntity<Page<PatientDto>> findAllPatients(){
        System.out.println(patientService.getAllPatients(Pageable.unpaged())+"*****************");
        return ResponseEntity.ok(patientService.getAllPatients(Pageable.unpaged()));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Supprimer un patient par ID")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','PATIENT')")
    @Operation(summary = "Modifier un patient existant")
    public ResponseEntity<PatientDto>  update(@PathVariable Long id,@Valid @RequestBody PatientDto dto){
        return new ResponseEntity<>(patientService.updatePatient(id, dto),HttpStatus.CREATED) ;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MEDECIN','PATIENT')")
    @Operation(summary = "Récupérer un patient par ID")
    public ResponseEntity<PatientDto>  findPatientById(@PathVariable Long id){
        return ResponseEntity.ok(patientService.getPatientById(id)) ;
    }


    @GetMapping("/triParNom")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Rechercher des patients par nom (username) avec pagination")
    public ResponseEntity<Page<PatientDto>> getRendezVousParNom(@RequestParam String username,Pageable pageable){
        Page<PatientDto> patientDtos=patientService.chercherPatients(username,pageable);
        return ResponseEntity.ok(patientDtos);
    }
}
