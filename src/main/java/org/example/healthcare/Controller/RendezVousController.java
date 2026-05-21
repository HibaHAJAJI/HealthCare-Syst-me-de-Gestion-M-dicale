package org.example.healthcare.Controller;

import lombok.AllArgsConstructor;
import org.example.healthcare.Dto.RendezVousDto;
import org.example.healthcare.Service.RendezVousService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/api/rendez-vous")
public class RendezVousController {

    private final RendezVousService rendezVousService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('ADMIN')")
    public RendezVousDto annulerRendezVousById(@PathVariable Long id){
        return rendezVousService.annulerRendezVous(id);
    }

   @GetMapping("/patient/{id}")
   @PreAuthorize("hasRole('ADMIN')")
   public Page<RendezVousDto> getRendezVousPatient(@PathVariable Long id,Pageable pageable){
        return rendezVousService.getRendezVousByPatientById(id,pageable);
    }

    @GetMapping("/medecin/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Page<RendezVousDto>getRendezVousMedecin(@PathVariable Long id,Pageable pageable){
        return rendezVousService.getRendezVousByMedecinById(id,pageable);
    }
}
