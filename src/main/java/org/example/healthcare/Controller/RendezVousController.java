package org.example.healthcare.Controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.healthcare.Dto.RendezVousDto;
import org.example.healthcare.Enum.Statut;
import org.example.healthcare.Service.RendezVousService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@AllArgsConstructor
@RestController
@RequestMapping("/api/rendez-vous")
public class RendezVousController {

    private final RendezVousService rendezVousService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','MEDECIN')")
    @Operation(summary = "Lister tous les rendez-vous avec pagination")
    public ResponseEntity<Page<RendezVousDto>>  findAllRendezVous(Pageable pageable){
        return  ResponseEntity.ok(rendezVousService.getAllRendezVous(pageable));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Créer un nouveau rendez-vous")
    public ResponseEntity<RendezVousDto> saveRendezVous(@Valid @RequestBody RendezVousDto dto){
        return new ResponseEntity<>(rendezVousService.addRendezVous(dto),HttpStatus.CREATED) ;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Modifier un rendez-vous existant")
    public  RendezVousDto update(@PathVariable Long id,@Valid @RequestBody RendezVousDto dto){
        return rendezVousService.updateRendezVous(id,dto) ;
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','PATIENT')")
    @Operation(summary = "Annuler un rendez-vous par ID")
    public ResponseEntity<RendezVousDto>  annulerRendezVousById(@PathVariable Long id){
        return ResponseEntity.ok(rendezVousService.annulerRendezVous(id)) ;
    }

   @GetMapping("/patient/{id}")
   @PreAuthorize("hasAnyRole('ADMIN','PATIENT')")
   @Operation(summary = "Lister les rendez-vous d'un patient par ID")
   public ResponseEntity<Page<RendezVousDto>>  getRendezVousPatient(@PathVariable Long id,Pageable pageable){
        return ResponseEntity.ok(rendezVousService.getRendezVousByPatientById(id,pageable)) ;
    }

    @GetMapping("/medecin/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MEDECIN')")
    @Operation(summary = "Lister les rendez-vous d'un médecin par ID")
    public ResponseEntity< Page<RendezVousDto>>getRendezVousMedecin(@PathVariable Long id,Pageable pageable){
        return ResponseEntity.ok(rendezVousService.getRendezVousByMedecinById(id,pageable));
    }

    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN','PATIENT')")
    @Operation(summary = "Rechercher des rendez-vous par statut")
    public ResponseEntity< Page<RendezVousDto>> search(@RequestParam Statut statut, Pageable pageable){
        return ResponseEntity.ok(rendezVousService.chercherParStatut(statut,pageable)) ;
    }

    @GetMapping("/searchByDate")
    @PreAuthorize("hasAnyRole('ADMIN','MEDECIN')")
    @Operation(summary = "Rechercher des rendez-vous par date")
    public ResponseEntity<Page<RendezVousDto>> getByDate(@RequestParam LocalDateTime dateRendezVous, Pageable pageable) {
        Page<RendezVousDto> result = rendezVousService.getByDate(dateRendezVous, pageable);
        return ResponseEntity.ok(result);
    }

}
