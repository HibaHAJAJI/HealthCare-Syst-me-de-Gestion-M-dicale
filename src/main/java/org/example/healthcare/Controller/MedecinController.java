package org.example.healthcare.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.healthcare.Dto.MedecinDto;
import org.example.healthcare.Service.MedecinService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<MedecinDto> findAllMedecins(Pageable pageable){
        return medecinService.getAllMedecins(pageable);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public MedecinDto saveMedecin(@Valid @RequestBody MedecinDto dto){
        return medecinService.saveMedecin(dto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')or #id == authentication.principal.id")
    public MedecinDto updateMedecin(@PathVariable Long id,@Valid @RequestBody MedecinDto dto){
        return medecinService.updateMedecinById(id,dto);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteMedecin(@PathVariable Long id){
        medecinService.DeleteMedecinById(id);
    }

    @GetMapping("/specialite")
    @PreAuthorize("hasRole('ADMIN')")
    public Page<MedecinDto> findMedecinBySpecialite(@RequestParam String specialite, Pageable pageable){
        return medecinService.getMedecinBySpecialite(specialite, pageable);
    }

}
