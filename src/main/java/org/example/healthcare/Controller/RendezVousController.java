package org.example.healthcare.Controller;

import lombok.AllArgsConstructor;
import org.example.healthcare.Dto.RendezVousDto;
import org.example.healthcare.Enum.Statut;
import org.example.healthcare.Service.RendezVousService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<RendezVousDto> findAllRendezVous(Pageable pageable){
        return rendezVousService.getAllRendezVous(pageable);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public RendezVousDto saveRendezVous(@RequestBody RendezVousDto dto){
        return rendezVousService.addRendezVous(dto);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public  RendezVousDto update(@PathVariable Long id,@RequestBody RendezVousDto dto){
        return rendezVousService.updateRendezVous(id,dto) ;
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','PATIENT')")
    public RendezVousDto annulerRendezVousById(@PathVariable Long id){
        return rendezVousService.annulerRendezVous(id);
    }

   @GetMapping("/patient/{id}")
   @PreAuthorize("hasAnyRole('ADMIN','PATIENT')")
   public Page<RendezVousDto> getRendezVousPatient(@PathVariable Long id,Pageable pageable){
        return rendezVousService.getRendezVousByPatientById(id,pageable);
    }

    @GetMapping("/medecin/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MEDECIN')")
    public Page<RendezVousDto>getRendezVousMedecin(@PathVariable Long id,Pageable pageable){
        return rendezVousService.getRendezVousByMedecinById(id,pageable);
    }
    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN','PATIENT')")
    public Page<RendezVousDto> search(@RequestParam Statut statut, Pageable pageable){
        return rendezVousService.chercherParStatut(statut,pageable);
    }

    @GetMapping("/searchByDate")
    @PreAuthorize("hasAnyRole('ADMIN','MEDECIN')")
    public ResponseEntity<Page<RendezVousDto>> getByDate(@RequestParam LocalDateTime dateRendezVous, Pageable pageable) {
        Page<RendezVousDto> result = rendezVousService.getByDate(dateRendezVous, pageable);
        return ResponseEntity.ok(result);
    }
}
