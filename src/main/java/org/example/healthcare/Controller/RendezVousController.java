package org.example.healthcare.Controller;

import lombok.AllArgsConstructor;
import org.example.healthcare.Dto.RendezVousDto;
import org.example.healthcare.Service.RendezVousService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/rendez-vous")
public class RendezVousController {

    private final RendezVousService rendezVousService;

    @GetMapping
    public List<RendezVousDto> findAllRendezVous(){
        return rendezVousService.getAllRendezVous();
    }

    @PostMapping
    public RendezVousDto saveRendezVous(@RequestBody RendezVousDto dto){
        return rendezVousService.addRendezVous(dto);
    }

    @PutMapping("/update/{id}")
    public RendezVousDto update(@PathVariable Long id,@RequestBody RendezVousDto dto){
        return rendezVousService.updateRendezVous(id,dto) ;
    }

    @PatchMapping("/{id}")
    public RendezVousDto annulerRendezVousById(@PathVariable Long id){
        return rendezVousService.annulerRendezVous(id);
    }

    @GetMapping("/patient/{id}")
    public List<RendezVousDto> getRendezVousPatient(@PathVariable Long id){
        return rendezVousService.getRendezVousByPatientById(id);
    }

    @GetMapping("/medecin/{id}")
    public List<RendezVousDto>getRendezVousMedecin(@PathVariable Long id){
        return rendezVousService.getRendezVousByMedecinById(id);
    }
}
