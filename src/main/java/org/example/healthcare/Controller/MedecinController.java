package org.example.healthcare.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.healthcare.Dto.MedecinDto;
import org.example.healthcare.Service.MedecinService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/medecins")
public class MedecinController {

    private final MedecinService medecinService;


    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<MedecinDto> findAllMedecins(){
        return medecinService.getAllMedecins();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public MedecinDto saveMedecin(@Valid @RequestBody MedecinDto dto){
        return medecinService.saveMedecin(dto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public MedecinDto updateMedecin(@PathVariable Long id,@Valid @RequestBody MedecinDto dto){
        return medecinService.updateMedecinById(id,dto);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteMedecin(@PathVariable Long id){
        medecinService.DeleteMedecinById(id);
    }
}
