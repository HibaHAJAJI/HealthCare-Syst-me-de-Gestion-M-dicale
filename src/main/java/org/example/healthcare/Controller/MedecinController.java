package org.example.healthcare.Controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.healthcare.Dto.MedecinDto;
import org.example.healthcare.Service.MedecinService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/api/medecins")
public class MedecinController {

    private final MedecinService medecinService;


    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Lister tous les medecins avec pagination")
    public ResponseEntity<Page<MedecinDto>> findAllMedecins(Pageable pageable){
        return ResponseEntity.ok(medecinService.getAllMedecins(pageable)) ;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Créer un nouveau medecin")
    public ResponseEntity<MedecinDto>  saveMedecin(@Valid @RequestBody MedecinDto dto){
        return new  ResponseEntity<>(medecinService.saveMedecin(dto), HttpStatus.CREATED) ;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MEDECIN')")
    @Operation(summary = "Modifier un medecin existant")
    public ResponseEntity<MedecinDto>  updateMedecin(@PathVariable Long id,@Valid @RequestBody MedecinDto dto){
        return ResponseEntity.ok(medecinService.updateMedecinById(id,dto)) ;
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Supprimer un medecin par ID")
    public ResponseEntity<Void> deleteMedecin(@PathVariable Long id){
        medecinService.DeleteMedecinById(id);
       return ResponseEntity.noContent().build();
    }

    @GetMapping("/specialite")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Rechercher des médecins par spécialité avec pagination")
    public ResponseEntity< Page<MedecinDto>> findMedecinBySpecialite(@RequestParam String specialite, Pageable pageable){
        return ResponseEntity.ok(medecinService.getMedecinBySpecialite(specialite, pageable)) ;
    }

}
