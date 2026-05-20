package org.example.healthcare.Controller;

import lombok.AllArgsConstructor;
import org.example.healthcare.Dto.RendezVousDto;
import org.example.healthcare.Service.RendezVousService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/rendez-vous")
public class RendezVousController {

    private final RendezVousService rendezVousService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','MEDECIN')")
    public List<RendezVousDto> findAllRendezVous(){
        return rendezVousService.getAllRendezVous();
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
    public List<RendezVousDto> getRendezVousPatient(@PathVariable Long id){
        return rendezVousService.getRendezVousByPatientById(id);
    }

    @GetMapping("/medecin/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<RendezVousDto>getRendezVousMedecin(@PathVariable Long id){
        return rendezVousService.getRendezVousByMedecinById(id);
    }
}
